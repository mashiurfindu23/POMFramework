package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	
	
		private WebDriver driver; 
		
		private ElementUtil eleUtil;
		
		
		
		
		private By emailId= By.id("input-email");
		private By password= By.id("input-password");
		private By loginBtn=By.xpath("//input[@value='Login']");
		private By logoImage = By.cssSelector("img[title='naveenopencart']");
		private By forgotPwdLink = By.linkText("Forgotten Password");
		private By registerLink = By.linkText("Register");              
		
		
		private static final Logger LOG = Logger.getLogger(LoginPage.class);    
		
		
		             
		
		
		public  LoginPage(WebDriver driver) {
			this.driver= driver;
			eleUtil = new ElementUtil(driver);
		}
		
		
		
		
		@Step("Waiting for login page title and fectching the title")
		public String getPageTitle() {
		
			
			String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);  
			
			System.out.println("login page title is title :"+ title);
			LOG.info("login page title: "+ title);
			return title;
		}
		@Step("Waiting for login page url and fectching the url")
		public boolean getLoginPageUrl() {
			
			String url= eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
			
			
			System.out.println("login page url :"+ url);
			if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
				return true;
			}
			else {
				return false;
			}
		}
		@Step("Checking  for forgot pwd link is displayed on login page")
		public boolean isForgotPwdLinkExist() {
		
			return eleUtil.doEleIsDisplayed(forgotPwdLink);
		}
//		
		@Step("Login with username: {0} and password: {1}")
		public AccountsPage doLogin(String username, String pwd) {   
			System.out.println("user credentials are :"+ username+" : " + pwd);
			
			eleUtil.doSendkeysWithWait(emailId, AppConstants.DEFAULT_LARGE_TIME_OUT, username);
			eleUtil.doSendKeys(password, pwd);
			eleUtil.doClick(loginBtn);
			
			
			
			
			return new AccountsPage(driver); 
		}
		@Step("Navigating to the register page")
		public RegisterPage navigateToRegisterPage() {
			System.out.println("navigate to registration page.......");
			eleUtil.doClick(registerLink);
			return new RegisterPage(driver);
			
		}


	}



