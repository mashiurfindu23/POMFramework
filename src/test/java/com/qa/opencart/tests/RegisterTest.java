package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest {
	
	
	
	@BeforeClass
	public void regSetup() {
	 registerPage = loginPage.navigateToRegisterPage();
		
		
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][]=	ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	// Ganarate randon rumber for email field 
	public String getRandomEmail() {
		Random random= new Random();
		String email="automationtest"+random.nextInt(1000)+"@gmail.com";
		return email;
		
		
		
	}
	
	@Test(dataProvider = "getRegTestData" )
	public void registerUserTest(String firstName, String lastName, String telephone, String password, String subscribe ) {
		String actSucMessage = registerPage.userRegister(firstName, lastName, getRandomEmail(),telephone, password, subscribe );
		Assert.assertEquals(actSucMessage, AppConstants.ACC_CREATE_SUCC_MSG);
		
		
	}
		
	}

		
	
	


