package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;  
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By searcch = By.name("search");
	
	private By searchIcon = By.xpath("//div[@id='search']//button");
	
	private By accSecHeaders = By.xpath("//div[@id ='content']/h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver); 
	}
	@Step("getAccPageTitle.....")
	public String getAccPageTitle() {
		
		
			String title =eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_TITLE);
		
			System.out.println("Account page title is title :"+ title);
			return title;
	}
	@Step("getAccPageUrl.....")
	public boolean getAccPageUrl() {
		String url=eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_URL_PARAM);
		
		System.out.println("Account page url :"+ url);
		if(url.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		
	return false;

	}
	@Step("isLogoutLinkExist.....")
	public boolean isLogoutLinkExist() {
	
		return eleUtil.doEleIsDisplayed(logoutLink);
		
	}
	@Step("isSearchExist.....")
	public boolean isSearchExist() {
	
		return eleUtil.doEleIsDisplayed(searcch);
	}
	
	/**
	 * Search field is available in AccontsPage so according to page chining model it is accounts 
	 * page responsibilty to make available of all the object of SearchResult page, that's why we created
	 *  return new SearchResultPage(driver) in AccountsPage
	 * @param productName
	 * @return
	 */
	
	
	@Step("performSearch.....{0}")
	
		public SearchResultsPage performSearch(String producKey) { // for page chaining 
		System.out.println("product key is: "+ producKey);  // product key means product name which we search in search field
		if(isSearchExist()) {
			eleUtil.doSendKeys(searcch, producKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver); // page chining will retun all objects ot that page 
		}
		else {
			System.out.println("search field is not present on the page....");
			
			return null;
		}
	}
	
	@Step("getAccSecHeadersList.....")
	public ArrayList<String> getAccSecHeadersList() {
		
		List<WebElement> secList= eleUtil.waitForelementsToBeVisible(accSecHeaders, AppConstants.DEFAULT_LARGE_TIME_OUT);
		
		
	
		System.out.println("total section header: " +secList.size());
		ArrayList<String> accSecTextList = new ArrayList<String>(); // creating a blank arrayList to store all accout headers text
		for(WebElement e: secList) {
			String text = e.getText();
			accSecTextList.add(text); 
		}
		return accSecTextList;
	}
}



