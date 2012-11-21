package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.basicaction;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author NhungVT
 *@date: 02/10/2012
 */

public class ECMS_DMS_SE_BasicAction_Delete extends EcmsBase 
{
	//Define data
	public String DATA_CONTENT_FOLDER = "FEX_ACTION_05_CONTENT";
	public String DATA_KOFAX_NAME = "FEX_ACTION_05_KOFAX";
	public String DATA_UPLOAD_FILE_NAME = "FEX_ACTION_05_UPLOAD";
	public String DATA_UPLOAD_FILE_PATH ="TestData/Winter.jpg";
	public String COLLABORATION_ICON_XPATH =  "//a[@title='collaboration']";
	public String CONTENT_FOLDER_XPATH = "//div[@title='"+DATA_CONTENT_FOLDER+"']";
	public String USERS_FOLDER_XPATH = "//div[@title='Users']";

	public By CONTENT_FOLDER = By.xpath(CONTENT_FOLDER_XPATH);
	public By KOFAX_DISPLAY = By.xpath("//td[text()='"+DATA_KOFAX_NAME+"']");
	public By KOFAX_DOCUMENT = By.xpath("//div[@title='"+DATA_KOFAX_NAME+"']");
	public By UPLOAD_FILE_NAME = By.xpath("//div[@title='"+DATA_UPLOAD_FILE_NAME+".jpg']");
	public By SHOW_DRIVES_ICON = By.xpath("//a[@title='Show Drives']");
	public By TRASH_DRIVER_ICON = By.xpath("//a[@title='Trash']");
	public By COLLABORATION_ICON =  By.xpath(COLLABORATION_ICON_XPATH);
	public By USERS_FOLDER = By.xpath(USERS_FOLDER_XPATH);
	public By ADDRESS_INPUT = By.xpath("//input[@id='address']");
	public By PUBLIC_FOLDER = By.xpath("//div[text()='Public']");
	public By PRIVATE_FOLDER = By.xpath("//div[text()='Private']");
	public By DELETE_LINK = By.xpath("//a[@style ='display: block;' and contains(text(),'Delete')]");
	public By SYSTEM_TAB_LINK = By.xpath("//a[@title='System']");
	public By PERMISSIONS_LINK = By.xpath("//a[@title='Permissions']");
	public By WEBCONTRIBUTOR_EDIT_PERMISSIONS = By.xpath("//td/div[@title='*:/platform/web-contributors']/following::td//img[@title='Edit']");
	public By REMOVE_RIGHT_CHECKBOX = By.xpath("//input[@id='remove']");
	public By CLOSE_WINDOW_ICON = By.xpath("//a[@title='Close Window']");

	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}

	//Delete node when user has right to remove node
	@Test()
	public void test01_DeleteNodeWhenUserHasRightToRemove()
	{
		//goto Site Explorer
		goToSiteExplorer();

		//Create a Content Folder
		createNewContentFolder(DATA_CONTENT_FOLDER, DATA_CONTENT_FOLDER);
		waitForElementPresent(CONTENT_FOLDER);

		//Create a Kofax Document
		goToAddNewContent();
		createNewKofax(DATA_KOFAX_NAME);
		waitForAndGetElement(KOFAX_DISPLAY);
		click(ELEMENT_SITES_MANAGEMENT_DRIVE);
		waitForElementPresent(KOFAX_DOCUMENT);

		//Upload a File
		uploadFile(DATA_UPLOAD_FILE_NAME, DATA_UPLOAD_FILE_PATH);
		waitForElementPresent(UPLOAD_FILE_NAME);

		//Delete Nodes
		waitForElementPresent(CONTENT_FOLDER);
		deleteDocument(CONTENT_FOLDER);
		waitForElementPresent(KOFAX_DOCUMENT);
		deleteDocument(KOFAX_DOCUMENT);
		waitForElementPresent(UPLOAD_FILE_NAME);
		deleteDocument(UPLOAD_FILE_NAME);

		//Goto Trash driver
		waitForElementPresent(SHOW_DRIVES_ICON);
		click(SHOW_DRIVES_ICON);
		waitForElementPresent(TRASH_DRIVER_ICON);
		click(TRASH_DRIVER_ICON);

		//Verify Delete Nodes: Search by Name
		simpleSearch(DATA_CONTENT_FOLDER);
		waitForTextPresent(DATA_CONTENT_FOLDER);
		simpleSearch(DATA_KOFAX_NAME);
		waitForTextPresent(DATA_KOFAX_NAME);
		simpleSearch(DATA_UPLOAD_FILE_NAME);
		waitForTextPresent(DATA_UPLOAD_FILE_NAME+".jpg");
	}

	//Delete node in 'check in' status
	@Test()
	public void test02_DeleteCheckInNode()
	{
		//goto Site Explorer
		goToSiteExplorer();

		//Upload a File
		uploadFile(DATA_UPLOAD_FILE_NAME, DATA_UPLOAD_FILE_PATH);
		waitForElementPresent(UPLOAD_FILE_NAME);

		//Right click and check-in node
		rightClickOnElement(UPLOAD_FILE_NAME);
		waitForElementPresent(ELEMENT_MENU_CHECKIN);
		click(ELEMENT_MENU_CHECKIN);

		//Verify deleted item not exist
		waitForElementPresent(UPLOAD_FILE_NAME);
		rightClickOnElement(UPLOAD_FILE_NAME);
		waitForElementPresent(ELEMENT_MENU_CHECKOUT);
		waitForElementNotPresent(DELETE_LINK);

		//Delete data
		click(ELEMENT_MENU_CHECKOUT);
		waitForElementPresent(UPLOAD_FILE_NAME);
		deleteDocument(UPLOAD_FILE_NAME);
	}

	//Delete node while user does not have remove right
	@Test()
	public void test10_DeleteNodeByUserNotHaveRemoveRight()
	{
		//goto Site Explorer
		goToSiteExplorer();

		//Create a Content Folder
		createNewContentFolder(DATA_CONTENT_FOLDER, DATA_CONTENT_FOLDER);
		waitForElementPresent(CONTENT_FOLDER);

		//Goto System > Permissions > Uncheck Remove Right for Group *:/platform/web-contributor
		doubleClickOnElement(CONTENT_FOLDER_XPATH);
		waitForElementPresent(SYSTEM_TAB_LINK);
		click(SYSTEM_TAB_LINK);
		waitForElementPresent(PERMISSIONS_LINK);
		click(PERMISSIONS_LINK);
		waitForElementPresent(WEBCONTRIBUTOR_EDIT_PERMISSIONS);
		click(WEBCONTRIBUTOR_EDIT_PERMISSIONS);
		waitForElementPresent(REMOVE_RIGHT_CHECKBOX);
		uncheck(REMOVE_RIGHT_CHECKBOX);
		save();

		waitForElementPresent(CLOSE_WINDOW_ICON);
		click(CLOSE_WINDOW_ICON);

		//Sign out and Sign in as mary
		signOut();
		driver.get(baseUrl);
		signIn("mary", "gtn");

		//goto Site Explorer
		goToSiteExplorer();

		//Verify Deleted item not existed
		waitForElementPresent(CONTENT_FOLDER);
		rightClickOnElement(CONTENT_FOLDER);
		waitForElementNotPresent(DELETE_LINK);

		//Delete data
		signOut();
		driver.get(baseUrl);
		signIn("john", "gtn");

		//go to Site Explorer
		goToSiteExplorer();

		//Verify Deleted item not existed
		waitForElementPresent(CONTENT_FOLDER);
		deleteDocument(CONTENT_FOLDER);
	}

	//	//Delete public folder
	//	@Test()
	//	public void test16_DeletePublicDriver()
	//	{
	//		//goto Site Explorer
	//		goToSiteExplorer();
	//		
	//		//Click Show Drives > collaboration > Users 
	//		waitForElementPresent(SHOW_DRIVES_ICON);
	//		click(SHOW_DRIVES_ICON);
	//		waitForElementPresent(COLLABORATION_ICON);
	//		doubleClickOnElement(COLLABORATION_ICON_XPATH);
	//		waitForElementPresent(USERS_FOLDER);
	//		doubleClickOnElement(USERS_FOLDER_XPATH);
	//		
	//		waitForElementPresent(ADDRESS_INPUT);
	//		type(ADDRESS_INPUT, "/Users/j___/jo___/joh___/john", true);
	//		WebElement element = waitForAndGetElement(ADDRESS_INPUT);
	//		//actions.sendKeys(element, Keys.ENTER).build().perform();
	//		element.sendKeys(Keys.ENTER);
	//		
	//	}
	//	
	//	//Delete private folder
	//	@Test()
	//	public void test17_DeletePrivateDriver()
	//	{
	//		
	//	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}
