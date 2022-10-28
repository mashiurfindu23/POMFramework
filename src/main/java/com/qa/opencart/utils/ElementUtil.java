package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private Select select ;
	private Actions act;
	private JavaScriptUtil jsUtil;
	
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		jsUtil= new JavaScriptUtil(driver);
		
	}
	// If I don't want to flush 
	
//	public  WebElement getElement(By locator) {
//		 return driver.findElement(locator);
//	}
	
	// For flusing the element
	public  WebElement getElement(By locator) {
		 WebElement ele=  driver.findElement(locator);
		 if(Boolean.parseBoolean(DriverFactory.highlight)) {
			 jsUtil.flash(ele);
		 }
		 return ele;
	}
	
	
	
	// TotalImagesClass 
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public  void doSendKeys(By locator, String value) {
	//	getElement(locator).sendKeys(value);
	WebElement ele=	getElement(locator);
	ele.clear();
	ele.sendKeys(value);
		
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public  String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public  String doGetAttribute(By locator, String atriName) {
		return getElement(locator).getAttribute(atriName);
	}
	
	public  boolean doEleIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
		
	}
	public  boolean isSingleElementPresent(By locator) {
		List<WebElement> list = getElements(locator);
		System.out.println(list.size());
		if(list.size()==1) {
			System.out.println("Single search element is present in the page");
			return true;
		}
		
		else{
			System.out.println("No search or more search field present in the page");
			return false;
		
	}

}
	

//	-------###############################-----------------------
	// TotalImagesClass 
	public  int getElementsCount(By locator) { // will count how many elements are available in the page like images, links etc
		return getElements(locator).size();
		
	}
	// TotalImagesClass 
	public  ArrayList<String> getElementsTextList(By locator) { // we can validate any kinds of text like linktext, lebel, etc.
		List<WebElement> eleList= getElements(locator);
		ArrayList<String> eleTextList = new ArrayList<String>(); // putting all text in eleTextList ArrayList and it is empty
		for(WebElement e : eleList) {
			String text = e.getText();
			if(text.length()!=0) {
			eleTextList.add(text);     // adding text which are captured 
		}
		}
		return eleTextList;

	}
	
	
	
	//-------####### DROPDOWN UTILS------------------- Only Select tag is present then this Utility will work 
	public  void doSelectDropDownByIndex(By locator, int index) {
//		WebElement	ele = getElement(locator);
//		Select select = new Select(ele);
		 select = new Select(getElement(locator)); // OR directly passing getElement()
		 select.selectByIndex(index);
		
	}
	
	public  void doSelectDropDownByVisibleText(By locator, String text) {
//		WebElement	ele = getElement(locator);
//		Select select = new Select(ele);
		 select = new Select(getElement(locator)); // OR directly passing getElement()
		 select.selectByVisibleText(text);
		 
	}	 
		 // ------------*** Without Select class  when visibilityText will not work------------------
		 
		 public  void doSelectValueFromDropDown(By locator, String value) { // Creating ElementUtil methods
				
			List<WebElement>optionList= getElements(locator); // Calling getElements method
				
				System.out.println(optionList.size());
				
				for(WebElement e: optionList) {
					String text = e.getText();
					System.out.println(text); // this will print all countries
					
					// If I want to select specific country like Bangladesh
					
					if(text.equals(value)) {
					e.click();
					break;
					
						}
				
					}
				
				
			}
	
	
	public  void doSelectDropDownByValue(By locator, String value) {
	//	WebElement	ele = getElement(locator);
	//	Select select = new Select(ele);
		 select = new Select(getElement(locator)); // OR directly passing getElement()
		 select.selectByValue(value);
		
	}
	// THIS UTILITY METHOD WILL RETURN HOW MANY COUNTRY LISTED IN THE DROP DOWN. THE NUMBER !!!
		public  List<WebElement> getDropDownOptions(By locator) {
		//	WebElement	ele = getElement(locator);
			//	Select select = new Select(ele);  
			Select select = new Select(getElement(locator)); // OR directly passing getElement()
			return select.getOptions();
		}
		
		public  int getDropDownOptionsCount(By locator) {
			return getDropDownOptions(locator).size();         // this method call getDropDownOptions()
		}

		
		// ############ Google Search or any kind of Search #############
		
		public  void doSearch(By searchKeyLocator, String searchKey, By suggestinLocator, String value) throws InterruptedException {
			//driver.findElement(By.name("q")).sendKeys("selenium");
			getElement(searchKeyLocator).sendKeys(searchKey); // instead of above line we will call getElement(locator)
			Thread.sleep(3000);
			
			// List<WebElement> suggList= driver.findElements(By.xpath("//div[@class='wM6W7d']/span"));
			List<WebElement> suggList= getElements(suggestinLocator); // instead of above list we will call getElements(locator)
			System.out.println(suggList.size()-1);
			
			for(WebElement e: suggList) {
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value)) { // equals() or contains() will work here
				e.click();
				break;
			}
				
			}
			
		}
		
		//#### Utility method Clicking single Search Element from search box. "Automation Practice Search class " , "Video 11 Selenium" 
		
		public  void doSearch(String tagName, String text) {
			//By.xpath("//li[text()='Casual Dresses > Printed ']");
			//By suggestLocator= By.xpath("//li[text()='"+text+"']"); // this way we can diclare text as a variable string concanited '"+text"'
			By suggestLocator= By.xpath("//"+tagName+"[text()='"+text+"']"); // declare tagName as variable so we ca pass any tagname as variable like ul, li, div whatever it is 
			getElement(suggestLocator).click();
		
	}

		//############### FOOTERLINKS Video 11 #####################
		
		public  ArrayList<String> getFooterLinkList(By locator) { // generic function
			List<WebElement> fotterList= getElements(locator);
			System.out.println(fotterList.size());
			ArrayList<String>	eleTextList = new ArrayList<String>(); // creating empty ArrayList to store all the footer list 
			for(WebElement e:fotterList) {
				String text = e.getText();
			//	System.out.println(text);
				eleTextList.add(text);  // This will add list string in the ArrayList
				
			}
			
			return eleTextList;
			}
		
		
		// This utility method will work only for findout maddatory("*") field Selenium Video 18, class PesudoElementHandle.java
		public  boolean checkElementIsMandator(String JsScript) {  // Generic method
			JavascriptExecutor js= (JavascriptExecutor)driver; 
			
			String mandatory_text_field = 	js.executeScript(JsScript).toString();
			System.out.println(mandatory_text_field);
			
			if(mandatory_text_field.contains("*")) {
				System.out.println("FirstName is mandatory field");
				return true;
			}
			else {
				System.out.println("FirstName is not mandatory field");
				return false;
				
			}
			
		}
		
		// ******************************** Actions class Util ***********************
		
		// *************** Level 1 menu iteam*******************************
		
		public  void handleLevel1MenuItems(By parent1Menu, By childMenu) {
			
			Actions ac = new Actions(driver);  // Interview question: Actions is a class and we pass the driver
			
			ac.moveToElement(getElement(parent1Menu)).build().perform();
			//getElement(childMenu).click();
				doClick(childMenu);	
		}
		
		public  void doActionClick(By locator) {
			Actions act = new Actions(driver);
			act.click(getElement(locator)).build().perform();;
			
		}
		

		public  void doActionSendkeys(By locator, String value) {
			Actions act = new Actions(driver);
			act.sendKeys(getElement(locator), value).build().perform();
			
		}
		
	//	############ Explicitly Wait Utitlity ################################
		//An expectation for checking that an element is present on the DOM of a page.
		//This does notnecessarily mean that the element is visible.
		
		public  WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			
		}
		// can calling sendKeys() with wait
		public void doSendkeysWithWait(By locator, int timeOut, String value) {
			waitForElementPresence(locator, timeOut).sendKeys(value);
			
			// can calling doClick()  with wait
		}
		public void doClickWithWait(By locator, int timeOut) {
			waitForElementPresence(locator, timeOut).click();;
		}
		
		// can calling getText() with wait
		public String getTextWithWait(By locator, int timeOut) {
			return waitForElementPresence(locator, timeOut).getText();
		}
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
		 * Pulling/Interval Time with 500 millisec (default)
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		// Most of the time we will use this option
			public  WebElement waitForElementVisible(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
			}
			/**
			 * An expectation for checking that an element is present on the DOM of a page and visible.
			 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
			 * Pulling/Interval Time with 2000 millisec (customized)
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			
			public  WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
			}
			// ############## Wait List of WebElement ##################
			public List<WebElement> waitForelementsToBeVisible(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			}
			
			
			// can calling sendKeys() with element visible
			public void doSendkeyssWithWaitElementVisible(By locator, int timeOut, String value) {
				waitForElementVisible(locator, timeOut).sendKeys(value);
				
				// can calling doClick()  with element visible
			}
			public void doClickWithWaitElementVisible(By locator, int timeOut) {
				waitForElementVisible(locator, timeOut).click();;
			}
			
			// can calling getText() with element visible
			public String getTextWithElementVisible(By locator, int timeOut) {
				return waitForElementVisible(locator, timeOut).getText();
			}
			
			// calling when element is ready to be click
			
			public  void clickWhenReady(By locator, int timeOut) {  
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			}
			
		//	########## Alert Util non WebElement####################
			public  Alert waitForAlert(int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.alertIsPresent());// This will automatically switch to Alert don't need to write driver.switchTo().alert().
			
		}

			
			public  String getAlertText(int timeOut) {
				return waitForAlert(timeOut).getText();
		}
			public  void acceptAlert(int timeOut) {
				 waitForAlert(timeOut).accept();
		}
			public  void dismissAlert(int timeOut) {
				 waitForAlert(timeOut).dismiss();;
		}
			public  void sendKeysAlert(int timeOut, String value) {
				 waitForAlert(timeOut).sendKeys(value);
		}
			// ################# Utility for wait for Title##########################
			
			
			// Capture the Title value if providing the partial value of Title
			public  String waitForTitleContains(int timeOut, String titleFraction) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
				}
				else {
					return null;
				}
			}
			
			// Capture the Title value  providing the Full Value of Title
			
			public  String waitForTitleIs(int timeOut, String titleValue) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if(wait.until(ExpectedConditions.titleContains(titleValue))) {
				return driver.getTitle();
				}
				else {
					return null;
				}
			}
			
			// ################# Utility for wait for Url##########################
			
			
			// Capture the Url value if providing the partial value  of Url
			public  String  waitForUrlContains(int timeOut, String urlFraction) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
					return driver.getCurrentUrl();
				}
				else {
					return null;
				}
			}
			
			// Capture the Url value  providing the Full Value(String) of Url
			
			public  String  waitForUrlIs(int timeOut, String urlValue) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
					return driver.getCurrentUrl();
				}
				else {
					return null;
				}
			}
			
			// ################# Utility for wait for Frame ##########################
			
			// Using Index
			
			public  void waitForFrame(int timeOut, int frameIndex) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			}
			// Using Name or ID
			public  void waitForFrame(int timeOut, String nameOrId) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
			}
			// Using WebElement
			public  void waitForFrame(int timeOut, WebElement frameElement) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
			}
			
			// Using By locator 
			public void waitForFrame(int timeOut, By frameLocator) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
			}
			
			// ############ Utility for fluent Wait ###################
			
			public WebElement waitForElementToBeVisibleWithFluentWait(By locator, int timeOut, int pullingTime) {
				Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
						.withTimeout(Duration.ofSeconds(timeOut))
									.pollingEvery(Duration.ofSeconds(pullingTime))
										.ignoring(NoSuchElementException.class);
				
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
											 
			}
				
}





