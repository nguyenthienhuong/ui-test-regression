package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.listcontent;

import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.selectGroup;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.TestLogger.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
/*
 * @author: Thuntn
 * @date: 30/10/2012
 */
public class ECMS_WCM_Viewer_ListContent_AddNew_SelectContent extends EcmsBase {
	public static String USER = "john";
	public static String PASS = "gtn";

	//Create new CLV page by selecting directly published contents
	@Test
	public void test01_CreateCLVPageWithDirectPublishedContent() {
		String namePage="ECMS_WCM_Viewer_SelectContent1";
		String pathContent = "General Drives/Sites Management/acme/web contents/News";
		By content2= By.xpath("//img[contains(@alt,'New: Speed')]");

		goToPageEditor_EmptyLayout(namePage);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		goToEditPageEditor();

		selectCLVPath(pathContent,"New: Speed","content");
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH);
		//verify in public mode
		waitForElementPresent(content2);

		//delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", namePage, true);
		goToManagePages();
		deletePage(PageType.PORTAL, namePage);

	}

	//Create new CLV page by selecting directly draft contents
	@Test
	public void test01_CreateCLVPageWithDirectDraftContent() {
		String namePage="ECMS_WCM_SelectContent1_1";
		String pathContent = "General Drives/Sites Management/acme/web contents/News";

		String pathNews="acme/web contents/News";
		String draftContent= "article";
		By bContent= By.xpath("//a[contains(text(),'"+draftContent+"')]");

		info("Create new CLV page by selecting directly draft contents");
		//go to site explorer, and create a document
		goToSiteExplorer();
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToNodeByPath(pathNews);
		goToAddNewContent();

		//create an article
		createNewArticle(draftContent, draftContent, draftContent, draftContent);
		goToOverView();

		// Create CLV page
		goToPageEditor_EmptyLayout(namePage);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		//Edit CLV page
		goToEditPageEditor();

		selectCLVPath(pathContent,"article","content");
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH);

		//verify in edit mode
		changeEditMode();
		waitForElementPresent(bContent);

		//verify in public mode
		changeEditMode();
		waitForElementNotPresent(bContent);

		//delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", namePage, true);
		goToManagePages();
		deletePage(PageType.PORTAL, namePage);

		//delete the article
		goToSiteExplorer();
		deleteDocument(bContent);
	}

	//Delete content after choosing directly document or web content to view
	@Test
	public void test02_DeleteContentAfterChoosing() {
		String namePage="ECMS_WCM_SelectContent2";
		String pathContent = "General Drives/Sites Management/acme/web contents/News";

		By contentToSelect = By.xpath("//td/a[text()='New: Speed']");
		By selectedContent=By.xpath("//a[contains(text(),'acme-news-1')]");

		info("Delete content after choosing directly document or web content to view");

		// Create CLV page
		goToPageEditor_EmptyLayout(namePage);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		//Edit CLV page
		goToEditPageEditor();

		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		click(ELEMENT_EDIT_PORTLET_LINK);

		click(ELEMENT_RADIO_MODE_CONTENT);

		//select content
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		selectGroup(pathContent);
		click(contentToSelect);

		//delete content
		click(ELEMENT_EDITPAGE_CONTENT_DELETE);
		acceptAlert();
		acceptAlert();

		//verify expected result: the content is deleted
		waitForElementNotPresent(selectedContent);
		click(ELEMENT_CANCEL_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
		click(ELEMENT_PAGE_EDIT_FINISH);

		//delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", namePage, true);
		goToManagePages();
		deletePage(PageType.PORTAL, namePage);

	}

	//Search by name of content when select existing content to create page 
	@Test
	public void test03_SearchByNameOfContent() {
		String namePage="ECMS_WCM_SelectContent3";
		By contentResult=By.xpath("//div[@class='Text' and contains(text(),'Fire')]");

		info("Search by name of content when select existing content to create page ");

		// Create CLV page
		goToPageEditor_EmptyLayout(namePage);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		//Edit CLV page
		goToEditPageEditor();

		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		click(ELEMENT_EDIT_PORTLET_LINK);

		click(ELEMENT_RADIO_MODE_CONTENT);

		//select content
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		click(ELEMENT_CONTENT_SEARCH_FORM_TAB);
		type(ELEMENT_INPUT_NAME_SEARCH_FORM_EDIT_MODE, "Fire", true);
		click(ELEMENT_SEARCH_BUTTON);

		//verify expected result: search result is listed on Search result tab
		waitForElementPresent(ELEMENT_TAB_SEARCH_RESULT);
		waitForElementPresent(contentResult);
		click(ELEMENT_CLOSE_POPUP_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
		click(ELEMENT_PAGE_EDIT_FINISH);

		//delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", namePage, true);
		goToManagePages();
		deletePage(PageType.PORTAL, namePage);

	}

	//Search by name of content when select existing content to create page 
	@Test
	public void test04_SearchByNameOfContentWithBlankValue() {
		String namePage="ECMS_WCM_SelectContent4";

		By message =By.xpath("//span[contains(text(),'Please input a valid keyword for search.')]");
		info("Search by name of content when select existing content to create page ");

		// Create CLV page
		goToPageEditor_EmptyLayout(namePage);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		//Edit CLV page
		goToEditPageEditor();

		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		click(ELEMENT_EDIT_PORTLET_LINK);

		click(ELEMENT_RADIO_MODE_CONTENT);

		//select content
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		click(ELEMENT_CONTENT_SEARCH_FORM_TAB);
		type(ELEMENT_INPUT_NAME_SEARCH_FORM_EDIT_MODE, "", true);
		click(ELEMENT_SEARCH_BUTTON);

		//verify expected result: search result is listed on Search result tab
		waitForElementPresent(message);
		click(ELEMENT_OK_BUTTON);
		click(ELEMENT_CLOSE_POPUP_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
		click(ELEMENT_PAGE_EDIT_FINISH);

		//delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", namePage, true);
		goToManagePages();
		deletePage(PageType.PORTAL, namePage);

	}

	//Search a word or phrase in content 
	@Test
	public void test05_SearchByNameOfContentWithBlankValue() {
		String namePage="ECMS_WCM_SelectContent2";
		By contentResult=By.xpath("//div[@class='Text' and contains(text(),'Night')]");
		info("Search a word or phrase in content");
		
		// Create CLV page
		goToPageEditor_EmptyLayout(namePage);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		//Edit CLV page
		goToEditPageEditor();

		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		click(ELEMENT_EDIT_PORTLET_LINK);

		click(ELEMENT_RADIO_MODE_CONTENT);

		//select content
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		click(ELEMENT_CONTENT_SEARCH_FORM_TAB);
		click(ELEMENT_SEARCH_FORM_CONTENT);
		type(ELEMENT_INPUT_NAME_SEARCH_WORD_PHRASE_EDIT_MODE, "Night", true);
		click(ELEMENT_SEARCH_BUTTON);

		//verify expected result: search result is listed on Search result tab
		waitForElementPresent(ELEMENT_TAB_SEARCH_RESULT);
		waitForElementPresent(contentResult);
		click(ELEMENT_CLOSE_POPUP_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
		click(ELEMENT_PAGE_EDIT_FINISH);

		//delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", namePage, true);
		goToManagePages();
		deletePage(PageType.PORTAL, namePage);

	}
	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms(USER, PASS);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}