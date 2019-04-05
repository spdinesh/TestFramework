package com.testframework;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.testng.log4testng.Logger;


/**
 * EnvironmentPropertiesReader is to set the environment variable declaration
 * mapping for config properties in the UI test
 */
public class EnvironmentPropertiesReader {

	private static final Logger log = Logger.getLogger(EnvironmentPropertiesReader.class);
	private static EnvironmentPropertiesReader envProperties;

	private Properties properties;

	private EnvironmentPropertiesReader() {
		properties = loadProperties();
	}

	private Properties loadProperties() {

		//File file = new File("./src/main/resources/config.properties");
		//FileInputStream fileInput = null;
		Properties props = new Properties();
	
		try {
//			fileInput = new FileInputStream(file);			
//			props.load(fileInput);
//			fileInput.close();
			InputStream cpr = EnvironmentPropertiesReader.class.getResourceAsStream("/config.properties");		
			props.load(cpr);
			cpr.close();
			
		} catch (FileNotFoundException e) {
			log.error("config.properties is missing or corrupt : " + e.getMessage());
		} catch (IOException e) {
			log.error("read failed due to: " + e.getMessage());
		}

		return props;
	}

	public static EnvironmentPropertiesReader getInstance() {
		if (envProperties == null) {
			envProperties = new EnvironmentPropertiesReader();
		}
		return envProperties;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public boolean hasProperty(String key) {		
		return StringUtils.isNotBlank(properties.getProperty(key));
	}
}
