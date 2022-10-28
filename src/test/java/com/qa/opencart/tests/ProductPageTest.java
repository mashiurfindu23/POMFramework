package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductPageTest extends BaseTest {
	
	@BeforeClass
	public void prodInfoSetUp() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("pasword"));
	}
	@Test
	public void productHeaderTest() {
		searchResultsPage = accPage.performSearch("Macbook");   						// page chining to searchResultsPage
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro"); 				// page chining  searchResultsPage to productInfoPage
		String actProductHeader = productInfoPage.getProductHeader("MacBook Pro");
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}
	
	
	@DataProvider
	public Object[][]getProductInfoData() {
		return new Object[][]  {            
			{"Macbook", "MacBook Air",AppConstants.MACBOOK_AIR_IMAGES_COUNT},
			{"Macbook", "MacBook Pro",AppConstants.MACBOOK_PRO_IMAGES_COUNT},
			{"iMac", "iMac", AppConstants.IMAC_IMAGES_COUNT},
			};
			
		}
	
	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchKey, String mainProductName, int imagesCount) {
		searchResultsPage = accPage.performSearch(searchKey);   						// page chining to searchResultsPage
		productInfoPage=searchResultsPage.selectProduct(mainProductName); 				// page chining  searchResultsPage to productInfoPage
		int actProductImages = productInfoPage.getProductImagesCount();
		System.out.println(actProductImages);
		Assert.assertEquals(actProductImages, imagesCount );
		
	}
	

	@Test
	public void productMetaDataTest() {
		searchResultsPage = accPage.performSearch("Macbook"); 
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
		
		
		
	}
	

	
	

}
