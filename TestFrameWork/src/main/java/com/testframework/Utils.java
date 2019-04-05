package com.testframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Utils 
{
	public static WebDriver driver;
	static Properties prop;
	public static Properties getProperty() {
		try {
			prop = new Properties();
			prop.load(new FileInputStream("./src/main/resource/Config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
    public static WebDriver webDriverUtil()
    {
    	prop = getProperty();
    	if(prop.getProperty("driver").equals("chrome")) {
    		WebDriverManager.chromedriver().setup();
    		driver= new ChromeDriver();
    		driver.manage().window().maximize();
    		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    	}else if(prop.getProperty("driver").equals("firefox")) {
    		WebDriverManager.firefoxdriver().setup();
    		driver= new FirefoxDriver();
    		driver.manage().window().maximize();
    	}else {
    		WebDriverManager.edgedriver().setup();
    		driver= new EdgeDriver();
    		driver.manage().window().maximize();
    	}    	
    	
        return driver;
    }
    
    /** To get the test data from the .xls file
	 * @param testcaseId
	 * @param sheetName
	 * @return
	 * @throws IOException 
	 */
	public static HashMap<String, HashMap<String, String>> getTestData(String sheetName, String file) throws IOException {
		String basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "resource" + File.separator +"data"+File.separator;
		String dataFilePath = basePath+file;
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(dataFilePath));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheetName=sheetName.split("\\.")[(sheetName.split("\\.").length) - 1];
		Sheet sheet = workbook.getSheet(sheetName);

		int lastRow = sheet.getRows();
		int lastcolumn = sheet.getColumns();

		HashMap<String, HashMap<String, String>> result = new HashMap<String, HashMap<String, String>>(lastRow - 1);

		for (int i = 1; i < lastRow; i++) {
			HashMap<String, String> testdata = new HashMap<String, String>();
			for (int j = 0; j < lastcolumn; j++)
				testdata.put(sheet.getCell(j, 0).getContents(), sheet.getCell(j, i).getContents());
			result.put(sheet.getCell(0, i).getContents(),testdata);
		}

		return result;
	}
}
