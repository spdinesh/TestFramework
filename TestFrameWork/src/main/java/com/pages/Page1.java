package com.pages;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testframework.Utils;

public class Page1 extends Utils{

		static HashMap<String,String> testData;
		@FindBy(xpath = "//a[@class='aui-nav-link login-link']")
		WebElement login;
		
		@FindBy(id = "login-form-username")
		WebElement userName;
		
		@FindBy(id = "login-form-password")
		WebElement pwd;
		
		@FindBy(id = "login-form-submit")
		WebElement loginbtn;
		
		
		public Page1(HashMap<String,String> testDatas) {
			testData=testDatas;
			PageFactory.initElements(driver, this);
		}
		
		
		public String clickOnLogin() {
			login.click();
			return enterDetails();
			
		}
		
		public String enterDetails() {
			userName.sendKeys(testData.get("userName"));
			pwd.sendKeys(testData.get("password"));
			loginbtn.click();
			
			return null;
			
		}
		
		
		
		
}
