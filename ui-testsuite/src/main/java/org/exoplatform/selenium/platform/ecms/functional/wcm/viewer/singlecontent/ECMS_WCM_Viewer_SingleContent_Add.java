package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.singlecontent;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
//import static org.exoplatform.selenium.platform.NavigationManagement.*;
import org.exoplatform.selenium.platform.ecms.PageEditor;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author VuNA
 * Date: 23/10/2012
 */
public class ECMS_WCM_Viewer_SingleContent_Add extends PageEditor{
	/*-- Data for these test cases --*/
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	public String ELEMENT_ARTICLE_TO_DELETE = "//a[@title='${titleOfFile} " + "']";
	public By ELEMENT_PUBLISHED_STATUS = By.xpath("//td/div[text()='Revision:1']/following::td[contains(text(),'Published')]");
	public By ELEMENT_LINK_PUBLICATION = By.xpath("//div[@id='DMSMenuItemContainer']//a[@title='Publications']");
	public By ELEMENT_PUBLISHED_ARTICLE = By.xpath(".//*[@id='UIPublicationPanel']/fieldset[1]/div/div[2]/a[5]");

	public String summaryArticle = "summary of article";
	public String contentArticle = "content of article";

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

	/*-- Case No 001 / ID 001
	 *-- Create new SCV page using existing document
	 * --*/
	@Test
	public void test01_CreateNewSCVPageUsingExistingDocument(){
		info("-- Step 1 & 2: Create SCV page and Add content for SCV page--");
		
		String pageName = "testSCV01";
		
		String contentPath = "General Drives/Sites Management/acme/documents/offices.jpg";

		addSCVPageAndContentFolderPaths(pageName, contentPath);

		waitForTextPresent("offices.jpg");

		info("-- Restore original data--");

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, true,"acme", false, "");

		info("-- Sign Out --");

		signOut();
	}

	/*-- Case No 002 / ID 002
	 *-- Create new SCV page using new created document/web content is being in draft status
	 * --*/
	@Test
	public void test02_CreateNewSCVPageUsingNewCreatedDocumentDraftStatus(){
		info("-- Step 1: Create document/web content --");
		
		String pageName = "testSCV02";
		String titleArticle = "testArticle02";
		String nameArticle = "testArticle02";

		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		addNewElementTest(true, false, false, titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2 & 3: Create new SCV page and Add content for SCV page--");

		String contentPath = "General Drives/Sites Management/acme/documents/"+ titleArticle +"";
		addSCVPageAndContentFolderPaths(pageName, contentPath);

		changeEditMode();

		waitForTextPresent(titleArticle);

		info("-- Restore original data--");

		deleteDocumentInSETest("acme/documents", titleArticle);

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, false, "", true, "Content Management");

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 003 / ID 003
	 *-- Create new SCV page using new created document/web content is being in published status
	 * --*/
	@Test
	public void test03_CreateNewSCVPageUsingNewCreatedDocumentPublishedStatus(){
		info("-- Step 1: Create document/web content --");

		String pageName = "testSCV03";
		String titleArticle = "testArticle03";
		String nameArticle = "testArticle03";

		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		addNewElementTest(true, false, false, titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Publish document/web content --");

		click(ELEMENT_LINK_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		click(ELEMENT_PUBLISHED_ARTICLE);

		waitForElementPresent(ELEMENT_PUBLISHED_STATUS);

		save();

		waitForElementNotPresent(ELEMENT_PUBLISHED_STATUS);

		//Go to ACME site to verify that document is published
		mouseOver(By.linkText("My Sites"), true);

		click(By.linkText("acme"));

		waitForTextPresent(titleArticle);

		info("-- Step 3 & 4: Create new SCV page and Add content for SCV page --");

		String contentPath = "General Drives/Sites Management/acme/documents/"+ titleArticle +"";
		addSCVPageAndContentFolderPaths(pageName, contentPath);

		waitForTextPresent(titleArticle);

		info("-- Restore original data--");

		deleteDocumentInSETest("acme/documents", titleArticle);

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, true, "acme", false, "");

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 004 / ID 004
	 *-- Filter document/web content/media file to add content for SCV
	 * --*/
	@Test
	public void test04_FilterDocumentWebcontentMediaFileToAddContentForSCV(){
		info("-- Step 1: Create document/web content/media --");

		String pageName = "testSCV04";
		String titleArticle = "testArticle04";
		String nameArticle = "testArticle04";
		
		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		//create a new document
		addNewElementTest(true, false, false, titleArticle, nameArticle, summaryArticle, contentArticle);

		//create a new media
		addNewElementTest(false, true, false, "testMedia", "testMedia", "", "");

		//create a new web content
		addNewElementTest(false, false, true, "testWebContent", "testWebContent", "", "");

		info("-- Step 2: Create new SCV page --");

		goToPageEditor_EmptyLayout(pageName);

		//Drag and drop Content Detail portlet into this page
		addContentDetailEmptyLayout();

		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		waitForElementNotPresent(ELEMENT_NEWPAGE_SAVE_BUTTON);

		info("-- Step 3: Open form to filter document/web content/media file to add content for SCV --");

		goToEditPageEditor();

		actions.moveToElement(waitForAndGetElement(ELEMENT_FRAME_CONTAIN_PORTLET)).build().perform();

		click(ELEMENT_EDIT_PORTLET_LINK);

		click(ELEMENT_SELECT_CONTENT_PATH_LINK);

		info("-- Step 4: Filter by web content --");

		String  webContentPath = "General Drives/Sites Management/acme/web contents";
		selectContentPathInEditMode(webContentPath, true);
		waitForTextPresent("testWebContent");

		info("-- Step 5: Filter by document --");

		String  documentsPath = "documents";
		selectContentPathInEditMode(documentsPath, true);
		waitForTextPresent(titleArticle);

		info("-- Step 6: Filter by media --");

		String  mediasPath = "medias";
		selectContentPathInEditMode(mediasPath, true);
		waitForTextPresent("testMedia");

		click(ELEMENT_CLOSE_WINDOWS_BUTTON);

		close();

		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		click(ELEMENT_CONFIRM_YES_BUTTON);

		waitForElementNotPresent(ELEMENT_NEWPAGE_SAVE_BUTTON); 

		click(ELEMENT_CLOSE_WINDOWS_BUTTON);

		waitForElementNotPresent(ELEMENT_CLOSE_WINDOWS_BUTTON);

		info("-- Restore original data--");

		deleteDocumentInSETest("acme/documents", titleArticle);

		deleteDocumentInSETest("acme/medias", "testMedia");

		deleteDocumentInSETest("acme/web contents", "testWebContent");

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, false, "", true, "Content Management");

		info("-- Sign Out --");

		signOut();		
	}


	/*-- Case No 005 / ID 005
	 *-- Search by name of content to add content for SCV
	 * --*/
	@Test
	public void test05_SearchByNameOfContentToAddContentForSCV(){
		info("-- Step 1: Create document/web content --");

		String pageName = "testSCV05";
		String titleArticle = "testArticle05";
		String nameArticle = "testArticle05";
		
		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		//create a new document
		addNewElementTest(true, false, false, titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2 & 3: Create new SCV page and Open form to search--");

		addSCVPageAndOpenSelectContentFormTest(pageName);

		info("-- Step 4: Search by name of content --");
		//Select Content Search Form tab
		click(ELEMENT_CONTENT_SEARCH_FORM_TAB);

		type(ELEMENT_INPUT_NAME_SEARCH_FORM_EDIT_MODE, titleArticle, true);

		click(ELEMENT_SEARCH_BUTTON);

		waitForTextPresent(titleArticle);

		click(ELEMENT_CLOSE_WINDOWS_BUTTON);

		close();

		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		click(ELEMENT_CONFIRM_YES_BUTTON);

		waitForElementNotPresent(ELEMENT_NEWPAGE_SAVE_BUTTON); 

		click(ELEMENT_CLOSE_WINDOWS_BUTTON);

		waitForElementNotPresent(ELEMENT_CLOSE_WINDOWS_BUTTON);

		info("-- Restore original data--");

		deleteDocumentInSETest("acme/documents", titleArticle);

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, false, "", true, "Content Management");

		info("-- Sign Out --");

		signOut();		
	}


	/*-- Case No 007 / ID 007
	 *-- Search a word or phrase in content
	 * --*/
	@Test
	public void test07_SearchAWordOrPhraseInContent(){
		info("-- Step 1: Create document/web content --");
		
		String pageName = "testSCV07";
		String titleArticle = "testArticle07";
		String nameArticle = "testArticle07";

		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		//create a new document
		addNewElementTest(true, false, false, titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2 & 3: Create new SCV page and Open form to search--");

		addSCVPageAndOpenSelectContentFormTest(pageName);

		info("-- Step 4: Search a word or phrase in content --");
		
		//Select Content Search Form tab
		click(ELEMENT_CONTENT_SEARCH_FORM_TAB);
		
		//Select A word or phrase in context field		
		click(ELEMENT_CHECK_BOX_WORD_PHRASE_EDIT_MODE);
		
		type(ELEMENT_INPUT_NAME_SEARCH_WORD_PHRASE_EDIT_MODE, titleArticle, true);
		
		click(ELEMENT_SEARCH_BUTTON);

		waitForTextPresent(titleArticle);

		click(ELEMENT_CLOSE_WINDOWS_BUTTON);

		close();

		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		click(ELEMENT_CONFIRM_YES_BUTTON);

		waitForElementNotPresent(ELEMENT_NEWPAGE_SAVE_BUTTON); 

		click(ELEMENT_CLOSE_WINDOWS_BUTTON);

		waitForElementNotPresent(ELEMENT_CLOSE_WINDOWS_BUTTON);

		info("-- Restore original data--");

		deleteDocumentInSETest("acme/documents", titleArticle);

		//delete page at Manage page and Portal Navigation
		deletePageAtManagePageAndPortalNavigation(pageName, false, "", true, "Content Management");

		info("-- Sign Out --");

		signOut();		
	}

	//Common functions (only) for these test cases
	////
	public void addNewElementTest(boolean addDocument, boolean addMedia, boolean addWebContent, String titleElement, 
			String nameElement, String summaryElement, String contentElement){
		info("-- Add new content: "+ nameElement +"--");
		if (addDocument){
			goToNodeByPath("acme/documents");
			goToAddNewContent();
			createNewArticle(titleElement, nameElement, summaryElement, contentElement);
			waitForTextPresent(titleElement);
		}
		if(addMedia){
			goToNodeByPath("acme/medias");
			goToAddNewContent();
			click(By.xpath("//div[@title='Accessible Media']"));
			type(ELEMENT_ARTICLE_NAME_TEXTBOX, nameElement, true);
			click(ELEMENT_SAVE_CLOSE_BUTTON);
			waitForTextPresent(nameElement);
		}
		if(addWebContent){
			String pathFile = "TestData/Winter.jpg";
			goToNodeByPath("acme/web contents");
			goToAddNewContent();
			createNewPictureOnHeadLayout(nameElement, titleElement, pathFile);
			waitForTextPresent(titleElement);
		}
	}

	public void addSCVPageAndOpenSelectContentFormTest(String pageName){
		goToPageEditor_EmptyLayout(pageName);
		//Drag and drop Content Detail portlet into this page
		addContentDetailEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_NEWPAGE_SAVE_BUTTON);
		goToEditPageEditor();
		actions.moveToElement(waitForAndGetElement(ELEMENT_FRAME_CONTAIN_PORTLET)).build().perform();
		click(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
	}

	public void deleteDocumentInSETest(String pathToDocument, String documentName){
		info("-- Deleting: "+ documentName +" --");
		pause(1000);
		goToSiteExplorer();
		goToNodeByPath(pathToDocument);
		By elementArticleToDelete = By.xpath(ELEMENT_ARTICLE_TO_DELETE.replace("${titleOfFile}", documentName));
		deleteDocument(elementArticleToDelete);		
	}
}