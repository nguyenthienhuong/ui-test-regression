package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.listcontent;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author thaopth
 * Date: 31/10/12
 */

public class WCM_Viewers_ListContent_PublishIcon extends EcmsBase {

	public static By ELEMENT_PUBLICATION = By.linkText("Publications");
	public static By ELEMENT_PUBLIC_STATUS = By.xpath("//a[contains(text(), 'Published')]");
	public static By EMENET_CURRENT_STATUS = By.xpath("//a[@class='CurrentStatus']");
	public static By ELEMENT_CURRENT_PUBLIC_STATUS = By.xpath("//a[@class='CurrentStatus' and contains(text(), 'Published')]");
	public By ELEMENT_BACK_BUTTON = By.className("URLBackToButton");
	public By ELEMENT_PUBLISH_ICON = By.xpath("//div[@class='UICLVPortlet']//*[@title='Publish']");


	//function public a document
	public static void publicDocument(){
		info("Public this document");
		waitForElementPresent(ELEMENT_PUBLICATION);
		click(ELEMENT_PUBLICATION);
		WebElement current = waitForAndGetElement(EMENET_CURRENT_STATUS);
		if (current.getText().contains("Published") == false){
			click(ELEMENT_PUBLIC_STATUS);
		}
		waitForElementPresent(ELEMENT_CURRENT_PUBLIC_STATUS);
		save();
		info("Public document is successful");
	}

	//Data test
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";
	public By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout ECMS");
		driver.quit();
		actions = null;
	}
	/*
	 * Case FNC_ECMS_WCM_CLV_06_046: Displaying of [Publish] Icon 
	 */
	@Test
	public void test01_DisplayPublishIcon () {
		String DATA_ARTICLE_TITLE = "article1";
		String DATA_PAGE_NAME= "CLV";
		String DATA_FOLDER_NAME = "Folder1";
		By DATA_FOLDER_PATH = By.xpath("//a[@title='"+DATA_FOLDER_NAME+" "+"']");
		String DATA_CLV_PATH = "General Drives/Sites Management/acme";
		By DATA_CONTENT_TITLE = By.xpath("//a[contains(text(),'"+DATA_ARTICLE_TITLE+"')]");
		By EDIT_CONTENT_ICON = By.xpath("//div[@class='UICLVPortlet']//*[@title='Edit in Content Explorer']"); 
		String DATA_SUM = "Test publish icon";
		String DATA_CONT = "Test content";


		/*----Add new content-----*/

		info("Add new folder with article");
		addNewContentTest(DATA_FOLDER_NAME, DATA_FOLDER_PATH, DATA_ARTICLE_TITLE);

		info("Go to ACME home page");
		goToOverView();
		info("Create page with CLV");
		createPage_ContentList_CLVpath(DATA_PAGE_NAME, DATA_CLV_PATH, DATA_FOLDER_NAME);

		info("Change page to edit content mode");
		editContentInEditMode(DATA_CONTENT_TITLE, EDIT_CONTENT_ICON);

		info("Change content of document");

		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,DATA_SUM,true);
		switchToParentWindow();

		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,DATA_CONT,true);
		switchToParentWindow();

		click(ELEMENT_SAVE_CLOSE_BUTTON);

		info("Back to CLV page");
		waitForElementPresent(ELEMENT_BACK_BUTTON);
		click(ELEMENT_BACK_BUTTON);

		info("Verify publish icon is displayed");
		mouseOver(DATA_CONTENT_TITLE, true);
		waitForElementPresent(ELEMENT_PUBLISH_ICON);


		//Delete data
		info("Go to site explorer and delete document");
		goToSiteExplorer();
		deleteDocument(DATA_FOLDER_PATH);

		info("Go to manage page and delete page");
		goToManagePages();

		deletePage(PageType.PORTAL,DATA_PAGE_NAME,100000);
		info("Go to portal site and delete node");

		goToPortalSites();
		deleteNode("acme", "Overview", DATA_PAGE_NAME, true);


	}

	/*
	 * Case: FNC_ECMS_WCM_CLV_06_047 and 048: Activity of Publish icon
	 */

	@Test
	public void test02_ActivityOfPublishIcon()
	{
		String DATA_ARTICLE_TITLE = "article2";
		String DATA_PAGE_NAME= "CLV1";
		String DATA_FOLDER_NAME = "Folder2";
		By DATA_FOLDER_PATH = By.xpath("//a[@title='"+DATA_FOLDER_NAME+" "+"']");
		String DATA_CLV_PATH = "General Drives/Sites Management/acme";
		By DATA_CONTENT_TITLE = By.xpath("//a[text()='"+DATA_ARTICLE_TITLE+"']");
		By EDIT_CONTENT_ICON = By.xpath("//div[@class='UICLVPortlet']//*[@title='Edit in Content Explorer']"); 
		String DATA_SUM = "Test publish icon";
		String DATA_CONT = "Test content";
		By DATA_CLV_LINK = By.linkText("CLV1");

		/*----Add new content-----*/

		addNewContentTest(DATA_FOLDER_NAME, DATA_FOLDER_PATH, DATA_ARTICLE_TITLE);

		info("Go to ACME home page");
		goToOverView();

		info("Create page with CLV");
		createPage_ContentList_CLVpath(DATA_PAGE_NAME, DATA_CLV_PATH, DATA_FOLDER_NAME);

		info("Change page to edit content mode");
		editContentInEditMode(DATA_CONTENT_TITLE, EDIT_CONTENT_ICON);

		info("Change content of document");
		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,DATA_SUM,true);
		switchToParentWindow();

		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,DATA_CONT,true);
		switchToParentWindow();

		click(ELEMENT_SAVE_CLOSE_BUTTON);

		info("Back to CLV page");

		waitForElementPresent(ELEMENT_BACK_BUTTON);
		click(ELEMENT_BACK_BUTTON);

		pause(500);
		waitForElementPresent(DATA_CONTENT_TITLE);

		logoutEcms();

		//Case 02: Login as Mary and check not publish content is not shown
		loginEcms("mary", "gtn");

		mouseOver(ELEMENT_MY_SITE,true);

		mouseOver(ELEMENT_ACME, true);

		mouseOver(ELEMENT_OVERVIEW, true);

		click(DATA_CLV_LINK);
		clearCache();

		waitForTextPresent(DATA_ARTICLE_TITLE);

		waitForTextNotPresent(DATA_SUM);

		waitForTextNotPresent(DATA_CONT);

		logoutEcms();

		//Case 03: Login as John and publish edited content

		loginEcms(DATA_USER, DATA_PASS);

		mouseOver(ELEMENT_MY_SITE,true);

		mouseOver(ELEMENT_ACME, true);

		mouseOver(ELEMENT_OVERVIEW, true);

		click(DATA_CLV_LINK);

		waitForTextPresent(DATA_ARTICLE_TITLE);
		
		changeEditMode();
		
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");
		
		mouseOver(DATA_CONTENT_TITLE, true);

		waitForElementPresent(ELEMENT_PUBLISH_ICON);

		click(ELEMENT_PUBLISH_ICON);
		changeEditMode();
		clearCache();
		
		waitForTextPresent(DATA_SUM);

		logoutEcms();

		loginEcms("mary", "gtn");

		mouseOver(ELEMENT_MY_SITE,true);

		mouseOver(ELEMENT_ACME, true);

		mouseOver(ELEMENT_OVERVIEW, true);

		click(DATA_CLV_LINK);
		waitForTextPresent(DATA_ARTICLE_TITLE);
		for(int i=0;;i++)
		{    if (i>3)
			Assert.fail("Fail! Mary cannot see new public content");
			if(waitForElementPresent("//p[contains(text(),'"+DATA_SUM+"')]",30000,0) !=null) break;
			clearCache();
		}

		//Delete data
		logoutEcms();

		loginEcms(DATA_USER, DATA_PASS);

		info("Go to site explorer and delete document");

		goToSiteExplorer();

		goToNode(ELEMENT_ACME_NODE);

		waitForElementPresent(DATA_FOLDER_PATH);

		deleteDocument(DATA_FOLDER_PATH);

		info("Go to manage page and delete page");

		goToManagePages();

		deletePage(PageType.PORTAL,DATA_PAGE_NAME,100000);

		info("Go to portal site and delete node");

		goToPortalSites();

		deleteNode("acme", "Overview", DATA_PAGE_NAME, true);
	}
	/*
	 * Case FNC_ECMS_WCM_CLV_06_049: Displaying of [Publish] icon when the content has no life cycle 
	 */
	@Test
	public void test03_DisplayingOfPublishIconWhenTheContentHasNoLifeCycle ()
	{
		String DATA_ARTICLE_TITLE = "article3";
		String DATA_PAGE_NAME= "CLV2";
		String DATA_FOLDER_NAME = "Folder3";
		By DATA_FOLDER_PATH = By.xpath("//a[@title='"+DATA_FOLDER_NAME+" "+"']");
		String DATA_CLV_PATH = "General Drives/Sites Management/acme";
		By DATA_CONTENT_TITLE = By.xpath("//a[text()='"+DATA_ARTICLE_TITLE+"']");
		By EDIT_CONTENT_ICON = By.xpath("//div[@class='UICLVPortlet']//*[@title='Edit in Content Explorer']");

		/*----Add new content-----*/
		info("Add new folder with article");
		addNewContentTest(DATA_FOLDER_NAME, DATA_FOLDER_PATH, DATA_ARTICLE_TITLE);

		info("Go to ACME home page");
		goToOverView();

		info("Create page with CLV");
		createPage_ContentList_CLVpath(DATA_PAGE_NAME, DATA_CLV_PATH, DATA_FOLDER_NAME);

		info("Change page to edit content mode");
		editContentInEditMode(DATA_CONTENT_TITLE, EDIT_CONTENT_ICON);

		info("Back to CLV page");
		waitForElementPresent(ELEMENT_BACK_BUTTON);
		click(ELEMENT_BACK_BUTTON);

		info("Verify publish icon is displayed");
		mouseOver(DATA_CONTENT_TITLE, true);
		waitForElementNotPresent(ELEMENT_PUBLISH_ICON);


		//Delete data
		info("Go to site explorer and delete document");

		goToSiteExplorer();

		deleteDocument(DATA_FOLDER_PATH);

		info("Go to manage page and delete page");

		goToManagePages();

		deletePage(PageType.PORTAL,DATA_PAGE_NAME,100000);

		info("Go to portal site and delete node");

		goToPortalSites();

		deleteNode("acme", "Overview", DATA_PAGE_NAME, true);
	}
	//
	public void addNewContentTest(String DATA_FOLDER_NAME, By DATA_FOLDER_PATH, String DATA_ARTICLE_TITLE){
		info("-- Add/Publish a new content --");
		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		createNewContentFolder(DATA_FOLDER_NAME, DATA_FOLDER_NAME);
		goToNode(DATA_FOLDER_PATH);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		info("Publish document");
		publicDocument();
	}


	public void editContentInEditMode(By DATA_CONTENT_TITLE, By EDIT_CONTENT_ICON){
		info("Change page to edit content mode");
		changeEditMode();
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");
		//waitForElementPresent(DATA_CONTENT_TITLE);
		info("Edit content");
		mouseOver(DATA_CONTENT_TITLE, true);
		waitForElementPresent(EDIT_CONTENT_ICON);
		click(EDIT_CONTENT_ICON);
	}
}
