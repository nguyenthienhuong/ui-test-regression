package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.singlecontent;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.PageManagement.*;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author VuNA
 * Date: 31/10/2012
 */
public class ECMS_WCM_Viewer_SingleContent_PublishIcon extends ActionBar{
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	public String ELEMENT_TITLE_OF_SCV = "//div[contains(@id,'Currentexo_title')]";
	public By ELEMENT_ID_OF_SCV = By.xpath("//*[@id='UIPage']/div/div/div/div/div[contains(@id, 'UIPresentationContainerp_')]");
	public String ELEMENT_ID_OF_SCV_TEST = "//*[@id='UIPage']/div/div/div/div/div[@id='${idSCV}']/div[1]/div/div/div/div/div/a[@class='${actionName}']";
	public String ELEMENT_TEST_SCV = "//div[@class='TopTitle' and @title='${nameOfSCV}']";
	public By ELEMENT_FAST_EDIT_TITLE_SCV = By.xpath("//input[@type='TEXT' and contains(@id, 'newexo_title_p')]");
	public By ELEMENT_ACCEPT_FAST_EDIT_BUTTON = By.xpath("//*[contains(@id, 'Editexo_titleForm')]/a[2]");
	public By ELEMENT_BACK_TO_SCV_PAGE_ICON = By.xpath("//*[@id='UIViewBarContainer']/a[@class='URLBackToButton']");

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

	/*-- Case No 031 / ID 001 
	 *-- Displaying of [Publish] Icon  
	 *--*/
	@Test
	public void test01_DisplayingOfPublishIcon (){
		String pageName = "testCase01";
		String titleArticle = "testArticle01";
		String nameArticle = "testArticle01";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		String contentPath = "General Drives/Sites Management/acme/documents" + "/" + titleArticle;

		//Add a SCV for testing 
		addSCVForTest(pageName, contentPath, titleArticle, nameArticle, summaryArticle, contentArticle, true);

		info("-- Step 1: Default of Publish icon --");

		changeEditMode();

		String idSCV = getIDOfSCV();

		By elementEditIcon = By.xpath(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV).replace("${actionName}", "EditContentIcon"));

		By elementPublishIcon = By.xpath(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV).replace("${actionName}", "FastPublishIcon"));

		mouseOver(elementEditIcon, true);

		waitForTextNotPresent("Draft");

		waitForElementNotPresent(elementPublishIcon);

		info("-- Step 2: Edit data --");

		editTitleOfSCVInEditMode(false, true, "newTitle");

		info("-- Step 3: Return to Home page --");

		waitForTextPresent("newTitle");

		mouseOver(ELEMENT_TITLE_OF_SCV, true);

		waitForTextPresent("Draft");

		waitForElementPresent(elementPublishIcon);

		info("-- Restore original data --");

		restoreOriginalData(pageName, "newTitle");

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 032 / ID 002 
	 *-- Activity of [Publish] Icon   
	 *--*/
	@Test
	public void test02_ActivityOfPublishIcon(){
		String pageName = "testCase02";
		String titleArticle = "testArticle02";
		String nameArticle = "testArticle02";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		String contentPath = "General Drives/Sites Management/acme/documents" + "/" + titleArticle;

		//Add a SCV for testing 
		addSCVForTest(pageName, contentPath, titleArticle, nameArticle, summaryArticle, contentArticle, false);

		info("-- Step 1 & 2: Click on Edit icon and Edit Data --");

		changeEditMode();

		editTitleOfSCVInEditMode(false, true, "newTitle");

		waitForTextPresent("newTitle");

		info("-- Step 3: Not publish data --");

		signOut();

		driver.get(baseUrl);

		loginEcms("mary", "gtn");

		goToSCVPage(pageName);

		waitForTextNotPresent("newTitle");

		signOut();

		driver.get(baseUrl);

		loginEcms(DATA_USER, DATA_PASS);

		info("-- Restore original data --");

		restoreOriginalData(pageName, "newTitle");

		info("-- Sign Out --");

		signOut();		
	}

	/*-- Case No 033 / ID 003
	 *-- Activity of [Publish] Icon   
	 *--*/
	@Test
	public void test03_ActivityOfPublishIconPart2(){
		String pageName = "testCase03";
		String titleArticle = "testArticle03";
		String nameArticle = "testArticle03";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		String contentPath = "General Drives/Sites Management/acme/documents" + "/" + titleArticle;

		//Add a SCV for testing 
		addSCVForTest(pageName, contentPath, titleArticle, nameArticle, summaryArticle, contentArticle, false);

		info("-- Step 1 & 2: Click on Edit icon and Edit Data --");

		changeEditMode();

		editTitleOfSCVInEditMode(false, true, "newTitle");

		waitForTextPresent("newTitle");

		info("-- Step 3: Publish data --");
		
		actionOnSCVInEditMode(false, true);
		
		String idSCV = getIDOfSCV();

		By elementPublishIcon = By.xpath(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV).replace("${actionName}", "FastPublishIcon"));
		
		mouseOver(ELEMENT_TITLE_OF_SCV, true);
		
		waitForElementNotPresent(elementPublishIcon);

		signOut();

		driver.get(baseUrl);

		//- Login as mary and Go to acme Homepage
		loginEcms("mary", "gtn");

		//- Mary can see the element: "newTitle"
		waitForTextPresent("newTitle");

		signOut();

		driver.get(baseUrl);

		loginEcms(DATA_USER, DATA_PASS);

		info("-- Restore original data --");

		restoreOriginalData(pageName, "newTitle");

		info("-- Sign Out --");

		signOut();		
	} 

	/*-- Case No 034 / ID 004
	 *-- Displaying of [Publish] icon when the content has no life cycle    
	 *--*/
	@Test
	public void test04_DisplayingOfPublishIconPart2(){
		String pageName = "testCase04";
		String titleArticle = "testArticle04";
		String nameArticle = "testArticle04";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		String contentPath = "General Drives/Sites Management/acme/documents" + "/" + titleArticle;

		//Add a SCV for testing 
		addSCVForTest(pageName, contentPath, titleArticle, nameArticle, summaryArticle, contentArticle, true);

		changeEditMode();

		info("-- Step 1: Click on Edit icon --");

		actionOnSCVInEditMode(true, false);
		
		info("-- Step 2: Do not edit data --");

		click(ELEMENT_BACK_TO_SCV_PAGE_ICON);

		waitForTextNotPresent("Publication");
		
		String idSCV = getIDOfSCV();

		By elementPublishIcon = By.xpath(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV).replace("${actionName}", "FastPublishIcon"));

		waitForElementNotPresent(elementPublishIcon);

		info("-- Restore original data --");

		restoreOriginalData(pageName, titleArticle);

		info("-- Sign Out --");

		signOut();			
	}


	///////////
	// Context: WCM/Viewer/SCV
	// Test cases: Publish Icon
	// Create some common (particularity) functions
	public void goToSCVPage(String pageName){
		mouseOver(By.linkText("My Sites"), true);
		mouseOver(By.linkText("acme"), true);
		mouseOver(By.linkText("Overview"), true);
		mouseOverAndClick(By.linkText(pageName));	
	}

	//Add new SCV
	public void addSCVForTest(String pageNameSCV, String contentPath, String titleArticle, 
			String nameArticle, String summaryArticle, String contentArticle, boolean publish){
		info("-- Create a new article --");
		goToSiteExplorer();
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToNodeByPath("acme/documents");
		goToAddNewContent();
		createNewArticle(titleArticle, nameArticle, summaryArticle, contentArticle);
		waitForTextPresent(titleArticle);
		if (publish){
			click(By.linkText("Publications"));
			waitForTextPresent("Manage Publication");
			click(By.linkText("Published"));
			save();
		}
		info("-- Create a new SCV --");	
		mouseOver(By.linkText("My Sites"), true);
		mouseOverAndClick(By.linkText("acme"));
		addSCVPageAndContentFolderPaths(pageNameSCV, contentPath);
	}

	//Edit a title of SCV in Edit Mode
	public void editTitleOfSCVInEditMode(boolean editInLine, boolean editInSiteExplorer, String newTitleOfFile){
		info("-- Edit the title of SCV --");
		//Click Edit icon in SCV
		//changeEditMode();
		if (editInLine){
			mouseOver(ELEMENT_TITLE_OF_SCV, true);	
			doubleClickOnElement(ELEMENT_TITLE_OF_SCV);
			type(ELEMENT_FAST_EDIT_TITLE_SCV, newTitleOfFile, true);
			click(ELEMENT_ACCEPT_FAST_EDIT_BUTTON);
			waitForTextPresent(newTitleOfFile);			
		}else if (editInSiteExplorer){
			actionOnSCVInEditMode(true, false);
			By ELEMENT_INPUT_EDIT_TITLE_SCV = By.xpath("//input[@id='title']");
			type(ELEMENT_INPUT_EDIT_TITLE_SCV, newTitleOfFile, true);
			save();
			click(ELEMENT_BACK_TO_SCV_PAGE_ICON);
			waitForTextNotPresent("Publication");
		}	
	}

	//Choose an action for SCV
	//Edit content -- Publish content
	public void actionOnSCVInEditMode(boolean editSCV, boolean publishSCV){
		String idSCV = getIDOfSCV();
		if(editSCV){
			mouseOverAndClick(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV).replace("${actionName}", "EditContentIcon"));
			waitForTextNotPresent("Overview");
		}else if (publishSCV){
			mouseOverAndClick(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV).replace("${actionName}", "FastPublishIcon"));
		}
	}
	
	//Get Element ID of SCV 
	public String getIDOfSCV(){
		mouseOver(ELEMENT_TITLE_OF_SCV, true);
		WebElement elementIDSCV = waitForAndGetElement(ELEMENT_ID_OF_SCV);
		String idOfSCV = elementIDSCV.getAttribute("id");
		return idOfSCV;	
	}

	//Delete data after testing
	public void restoreOriginalData(String pageSCVName, String titleOfArticle){
		goToSiteExplorer();
		goToNodeByPath("acme/documents");
		deleteDocument(By.linkText(titleOfArticle));
		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageSCVName, true,"acme", false, "");
	}
}