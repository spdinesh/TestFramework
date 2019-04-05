package com.testframework;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.Page1;


/**
 * Unit test for simple App.
 */
public class TestSuite 
{
	static HashMap<String, HashMap<String, String>> testSheet;
	static WebDriver driver;
	static Properties prop;
	static String url;
    @BeforeTest
    public void init() throws Exception {
    	driver=Utils.webDriverUtil();
    	prop=Utils.getProperty();
    	url=prop.getProperty("url");
    	testSheet = Utils.getTestData(TestSuite.class.getName(), "testDataXLS.xls");
    }
    
    @Test (dataProvider = "login")
    public void test(String testCaseId) {
    	HashMap<String, String> testData  = testSheet.get(testCaseId);
    	driver.get(url);
    	Page1 pg = new Page1(testData);
    	pg.clickOnLogin();
    	System.out.println(testData);
    
    }
    @DataProvider
    public Object[][] login() {
		return new Object[][] { { "123"}
		};
    }
    }
