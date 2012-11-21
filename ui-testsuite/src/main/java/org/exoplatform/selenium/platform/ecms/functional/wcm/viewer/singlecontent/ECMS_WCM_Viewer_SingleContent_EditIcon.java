package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.singlecontent;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import org.exoplatform.selenium.platform.ecms.ContentTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: VuNA
 * @date: 30/10/2012
 */
public class ECMS_WCM_Viewer_SingleContent_EditIcon extends ContentTemplate{
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	//Data for these cases
	public By ELEMENT_ID_OF_SCV = By.xpath("//*[@id='UIPage']/div/div/div/div/div[contains(@id, 'UIPresentationContainerp_')]");
	public By ELEMENT_BACK_TO_SCV_PAGE_ICON = By.xpath("//*[@id='UIViewBarContainer']/a[@class='URLBackToButton']");
	public By ELEMENT_FAST_EDIT_TITLE_SCV = By.xpath("//input[@type='TEXT' and contains(@id, 'newexo_title_p')]");
	public By ELEMENT_ACCEPT_FAST_EDIT_BUTTON = By.xpath("//*[contains(@id, 'Editexo_titleForm')]/a[2]");
	public String ELEMENT_TEST_SCV = "//div[@class='TopTitle' and @title='${nameOfSCV}']";
	public String ELEMENT_TITLE_OF_SCV = "//div[contains(@id,'Currentexo_title')]";
	public String ELEMENT_ID_OF_SCV_TEST = "//*[@id='UIPage']/div/div/div/div/div[@id='${idSCV}']/div[1]/div/div/div/div/div[2]/a[@class='EditContentIcon']"; 

	String contentPath = "General Drives/Sites Management/acme/documents/offices.jpg";

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

	/*-- Case No 028 / ID 001
	 *-- Default of [Edit] icon when mode = View
	 * --*/
	@Test
	public void test01_DefaultOfEditIconWhenModeIsView(){
		String pageName = "testSCV01";

		info("-- Create a single content viewer --");

		addSCVPageAndContentFolderPaths(pageName, contentPath);

		waitForTextPresent("offices.jpg");

		info("-- Step 1: Change mode to View --");

		//[Edit] icon is not displayed in SCV 
		mouseOver(ELEMENT_TEST_SCV.replace("${nameOfSCV}", "offices.jpg"), true);

		waitForElementNotPresent(ELEMENT_ID_OF_SCV_TEST);

		info("-- Step 2: Change mode to Edit --");

		changeEditMode();

		mouseOver(ELEMENT_TITLE_OF_SCV, true);

		WebElement elementIDSCV = waitForAndGetElement(ELEMENT_ID_OF_SCV);

		String idSCV = elementIDSCV.getAttribute("id");

		waitForElementPresent(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV));

		info("-- Restore original data--");

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, true,"acme", false, "");

		info("-- Sign Out --");

		signOut();		
	}

	/*-- Case No 029 / ID 002 
	 *-- Activity of [Edit] icon   
	 *--*/
	@Test
	public void test02_ActivityOfEditIcon(){
		String pageName = "testSCV02";

		info("-- Step 1: Click on Edit icon --");		

		addSCVPageAndContentFolderPaths(pageName, contentPath);

		waitForTextPresent("offices.jpg");
		
		//Click Edit icon in SCV
		changeEditMode();

		mouseOver(ELEMENT_TEST_SCV.replace("${nameOfSCV}", "offices.jpg"), true);

		WebElement elementIDSCV = waitForAndGetElement(ELEMENT_ID_OF_SCV);

		String idSCV = elementIDSCV.getAttribute("id");

		mouseOverAndClick(ELEMENT_ID_OF_SCV_TEST.replace("${idSCV}", idSCV));

		waitForTextNotPresent("Overview");

		info("-- Step 2: Return to Home page --");

		click(ELEMENT_BACK_TO_SCV_PAGE_ICON);

		waitForTextNotPresent("Publication");

		info("-- Restore original data--");

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, true,"acme", false, "");

		info("-- Sign Out --");

		signOut();		
	} 

	/*-- Case No 030 / ID 003 
	 *-- Fast editing when double click 
	 *--*/
	@Test
	public void test03_FastEditingWhenDoubleClick (){
		String pageName = "testSCV03";
		String titleArticle = "testArticle";
		String nameArticle = "testArticle";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		String contentPathTest = "General Drives/Sites Management/acme/documents" + "/" + titleArticle;
		
		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		goToNodeByPath("acme/documents");

		info("-- Create a new article with draft status --");

		goToAddNewContent();

		createNewArticle(titleArticle, nameArticle, summaryArticle, contentArticle);
		
		waitForTextPresent(titleArticle);

		info("-- Create a new SCV --");	
		
		mouseOver(By.linkText("My Sites"), true);
		
		mouseOverAndClick(By.linkText("acme"));

		addSCVPageAndContentFolderPaths(pageName, contentPathTest);
					
		info("-- Step 1: Double click on data of SCV --");
		
		//Change mode to Edit
		changeEditMode();
		
		waitForTextPresent(titleArticle);

		mouseOver(ELEMENT_TITLE_OF_SCV, true);
		
		info("-- Step 2: Fast editing when double click --");
		
		doubleClickOnElement(ELEMENT_TITLE_OF_SCV);
		
		type(ELEMENT_FAST_EDIT_TITLE_SCV, "Fast_Edit_Data", true);
		
		click(ELEMENT_ACCEPT_FAST_EDIT_BUTTON);
		
		waitForTextPresent("Fast_Edit_Data");
		
		info("-- Restore original data --");
		
		goToSiteExplorer();
		
		goToNodeByPath("acme/documents");
		
		deleteDocument(By.linkText("Fast_Edit_Data"));

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, true,"acme", false, "");

		info("-- Sign Out --");

		signOut();		
	}
}