package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {
	
	@Description("login page title test.....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getPageTitle(); 
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	@Description("login page url test.....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl()); 
	}
	
	@Description("login page is displaying forgot pwd Link test.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void isForgotPwdLinkExistTest() {
	Assert.assertEquals(loginPage.isForgotPwdLinkExist(), true);
	}
	@Description("login test with correct username and correct password.....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest() {
		
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("pasword")); 
		Assert.assertTrue(accPage.isLogoutLinkExist()); 
	}
	

}
