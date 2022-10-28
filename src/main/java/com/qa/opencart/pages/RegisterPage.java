package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By .id("input-password");
	private By cnfpassword = By.id("input-confirm");
	
	
	private By agreeChkBox = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By contBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@type ='radio' and @value=1]");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@type ='radio' and @value=0]");
	
	private By succMessage= By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
		
	}
	



public String userRegister(String FName, String LName, String emailID, String telphone, String pwd, String subcribe) {
	eleUtil.doSendkeyssWithWaitElementVisible(firstName, AppConstants.DEFAULT_TIME_OUT, FName);
	eleUtil.doSendKeys(lastName, LName);
	eleUtil.doSendKeys(email, emailID);
	eleUtil.doSendKeys(telephone, telphone);
	eleUtil.doSendKeys(password, pwd);
	eleUtil.doSendKeys(cnfpassword, pwd);
	
	if(subcribe.equalsIgnoreCase("yes")) {
		eleUtil.doClick(this.subscribeYes);
	}
	
	else {
		eleUtil.doClick(this.subscribeNo);
		
	}
	eleUtil.doClick(this.agreeChkBox);
	eleUtil.doClick(this.contBtn);
	
	String successMsg= eleUtil.waitForElementVisible(succMessage, AppConstants.DEFAULT_TIME_OUT).getText();
	System.out.println("Success message is: "+successMsg);
	
	eleUtil.doClick(logoutLink);
	eleUtil.doClick(registerLink);
	
	
	return successMsg;
	
	
	
	
	
	
	
}

}
