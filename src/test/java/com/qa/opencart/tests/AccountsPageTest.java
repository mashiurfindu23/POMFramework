package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass        
	public void setUp() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("pasword")); 
	}
	@Description("Account page title test-- Dev Name: @Mashiur")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void accPageTitleTest() {
		String accAccPageTitle = accPage.getAccPageTitle();
		Assert.assertEquals(accAccPageTitle, AppConstants.ACC_PAGE_TITLE);
		
	}

	@Description("Account page url test-- Dev Name: @Mashiur")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccPageUrl()); 
	}
	@Description("Account page search exist test-- Dev Name: @Mashiur")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	@Description("Account page log out link exist test-- Dev Name: @Mashiur")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Description("Account page header exist test-- Dev Name: @Mashiur")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority=5)
	public void accPageHeadersTest() {
	ArrayList<String> accHeadersList=	accPage.getAccSecHeadersList();
	System.out.println("actual accounts page headers: "+ accHeadersList);
	Assert.assertEquals(accHeadersList, AppConstants.ACC_PAGE_SECTIONS_HEADER); 
	}
	
	@DataProvider
	public Object[][]getProductKey() {
		return new Object[][]  {            
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
			};
			
		}
	@Description("Account page product search check test-- Dev Name: @Mashiur")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider= "getProductKey", priority=6)
	public void searchCheckTest(String productKey) {
		searchResultsPage= accPage.performSearch(productKey);  // page chinning 
		Assert.assertTrue(searchResultsPage.isSearchSuccessful());
	}
	
	 
	
	@DataProvider
	public Object[][]getProductData() {
		return new Object[][]  {            
			{"Macbook", "MacBook Air"},
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			};
			
		}
		
	
	
	@Description("Account page product search  test-- Dev Name: @Mashiur")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider= "getProductData", priority=7)	
	public void searcTest(String searchKey, String mainProductName) {
			searchResultsPage= accPage.performSearch(searchKey);           // page chining 
			if(searchResultsPage.isSearchSuccessful()) {
				productInfoPage = searchResultsPage.selectProduct(mainProductName); // page chining 
				String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
				Assert.assertEquals(actualProductHeader, mainProductName);
			}
	}
	


}
