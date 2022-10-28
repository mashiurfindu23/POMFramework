package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productSearchLayout = By.cssSelector("div.product-layout");

	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public boolean isSearchSuccessful() {
		List<WebElement> searchList= eleUtil.waitForelementsToBeVisible(productSearchLayout, AppConstants.DEFAULT_LARGE_TIME_OUT);
		if(searchList.size()>0) {
			System.out.println("search is successfully done .......");
			return true;
		}
			return false;
	}
	
	
	public ProductInfoPage selectProduct(String mainProductName) {
		By mainPrName = By.linkText(mainProductName);   // here we create runtime(dynamic) locator unless it will be hardcoded 
		eleUtil.doClick(mainPrName);
		return new ProductInfoPage(driver); // page chinning 
		
	}

}
