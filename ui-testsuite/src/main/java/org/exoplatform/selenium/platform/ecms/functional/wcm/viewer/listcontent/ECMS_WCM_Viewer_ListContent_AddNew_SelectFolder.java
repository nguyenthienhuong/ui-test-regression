package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.listcontent;

import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;
import static org.exoplatform.selenium.TestLogger.*;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
/*
 * @author: Thuntn
 * @date: 30/10/2012
 */

public class ECMS_WCM_Viewer_ListContent_AddNew_SelectFolder extends EcmsBase {
	public static String USER = "john";
	public static String PASS = "gtn";

	//Create new CLV page by using folder mode in case the folder is empty
	@Test
	public void test01_CreateEmptyCLVPage() {
		String pageName="ECMS_WCM_Viewer_SelectFolder1";
		String pathContent = "General Drives/Sites Management/acme";
		By message= By.xpath("//span[contains(text(),'Sorry, no articles are available.')]");

		info("Create new CLV page by using folder mode in case the folder is empty");

		// Create CLV page
		goToPageEditor_EmptyLayout(pageName);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		//Edit CLV page
		goToEditPageEditor();
		selectCLVPath(pathContent,"links");
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementPresent(message);

		//Delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", pageName, true);
		goToManagePages();
		deletePage(PageType.PORTAL, pageName);
	}

	//Create new CLV page by using folder mode in case the folder is empty
	@Test
	public void test02_CreateCLVPage() {
		String pageName="ECMS_WCM_Viewer_SelectFolder3";
		String pathContent = "General Drives/Sites Management/acme/web contents";
		By content1= By.xpath("//a[contains(text(),'Power Pack 1')]");
		By content2= By.xpath("//img[contains(@alt,'New: Speed')]");
		By content3= By.xpath("//img[contains(@alt,'New: Invisibility')]");

		String pathNews="acme/web contents/News";
		String draftContent= "article";
		By draft = By.xpath("//a[contains(text(),'"+draftContent+"')]");

		info("Create new CLV page by using folder mode in case the folder is empty");
		//go to site explorer, and create a document
		goToSiteExplorer();
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToNodeByPath(pathNews);

		//create an article
		goToAddNewContent();
		createNewArticle(draftContent, draftContent, draftContent, draftContent);
		goToOverView();

		// Create CLV page
		goToPageEditor_EmptyLayout(pageName);
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);

		//Edit CLV page
		goToEditPageEditor();
		selectCLVPath(pathContent,"News");
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH);

		//Verify expected result in public mode
		waitForElementPresent(content1);
		waitForElementPresent(content2);
		waitForElementPresent(content3);
		waitForElementNotPresent(draft);

		//Verify in edit mode
		changeEditMode();
		waitForElementPresent(draft);

		//delete node, page
		goToPortalSites();
		deleteNode("acme", "Overview", pageName, true);
		goToManagePages();
		deletePage(PageType.PORTAL, pageName);

		//delete the article
		goToSiteExplorer();
		deleteDocument(draft);
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
