package org.exoplatform.selenium.platform.ecms.functional.wcm.categories;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: Lientm
 * @date: 24/10/2012
 */

public class ECMS_WCM_Categories_Tags extends EcmsBase {

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";

	public static final	By ELEMENT_PORTLET_TAG = By.xpath("//div[text()='Content List']/../../../../../../../div[contains(@id,'UIPortlet')][1]");
	public static final By ELEMENT_CAT_PORTLET = By.xpath("//div[text()='Content List']/../../../../../../../div[contains(@id,'UIPortlet')][2]");
	public static final By ELEMENT_DROP_TARGET = By.xpath("//*[@id='ACMECategories']/div");
	public static final By ELEMENT_EDIT_PORTLET_TAG = By.xpath("//*[contains(@id,'UIPortlet')][1]/div/div[2]/div/div[2]/*//a[@class='EditIcon']");
	public static final By ELEMENT_DELETE_PORTLET_TAG = By.xpath("//*[contains(@id,'UIPortlet')][1]/div/div[2]/div/div[2]/*//a[@class='DeleteIcon']");

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		//delete tag view portlet
		goToNews();
		goToEditPageEditor();
		removePortlet(ELEMENT_CAT_PORTLET,ELEMENT_PORTLET_TAG,ELEMENT_DELETE_PORTLET_TAG);
		pause(1000);

		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}


	//	Function to create new article document and public it ->Function to for only this class

	public void createNewArticleAndTags(String title, String tag, boolean isPublic){
		By ELEMENT_DOCUMENT = By.linkText(title);

		if(waitForAndGetElement(ELEMENT_DOCUMENT,5000,0) != null){
			click(ELEMENT_DOCUMENT);
			deleteData(ELEMENT_DOCUMENT);
		}
		goToAddNewContent();
		info("Create new article document");
		createNewArticle(title, title, "", "");
		waitForElementPresent(ELEMENT_DOCUMENT);
		assert isElementPresent(ELEMENT_DOCUMENT):"add new article is not successful";
		info("Create new article document is successful");
		addTagForNode(tag, isPublic);
	}

	/*Function to add content list viewer portlet for New page, Configure it -> Function for only this class
	 * add content list portlet for News page
	 * Configure this portlet with homepath, template, header, show in page 
	 */
	public void addContentListViewerTagForNewsPage(String homepath, String homenode, String header, String template ){
		info("Add Content List portlet for News page");
		if (waitForAndGetElement(ELEMENT_CAT_PORTLET,10000,0) == null){
			click(ELEMENT_MENU_CONTENT_LINK);
			waitForElementPresent(ELEMENT_ADD_CONTENT_LIST_PORTLET);
			dragAndDropToObject(ELEMENT_ADD_CONTENT_LIST_PORTLET, ELEMENT_DROP_TARGET);
			pause(2000);
		}
		info("Config Content List portlet");
		waitForElementPresent(ELEMENT_PORTLET_TAG);
		mouseOver(ELEMENT_PORTLET_TAG, true);
		click(ELEMENT_EDIT_PORTLET_TAG);
		waitForElementPresent(ELEMENT_SELECT_CONTENT_PATH_LINK);

		info("Select homepath = " + homepath + "/" + homenode);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		selectGroup(homepath);
		click(By.xpath("//td/a[text()='" + homenode + "']"));

		info("Input header and choose template");
		type(ELEMENT_HEADER_PORTLET, header, true);
		select(ELEMENT_TEMPLATE_PORTLET, template);
		click(ELEMENT_ADVANCE_PORTLET);

		info("Select show in page = news");
		waitForElementPresent(ELEMENT_ADD_TARGET);
		click(ELEMENT_ADD_TARGET);
		click(ELEMENT_UPLEVEL);
		click(ELEMENT_NEW_TARGET_PATH);
		pause(500);
		save();
		close();
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH,50000);
	}

	/*case01: Check the displaying of private tag
	 * Configure News page to display tags:
	 *   +, go to News page -> page layout
	 *   +, add new content list portlet
	 *   +, Configure portlet: homepath = collaboration/tags, template = TagsCloud.gtmpl, target = news
	 * create new document in site explorer, public its
	 * Add private tag for this document
	 * Go to News page: check can not see private tag
	 */
	@Test
	public void test01_CheckDisplayOfPrivateTag(){
		String DATA_ARTICLE = "ECMS_WCM_Categories_Tags_article_01";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		String DATA_TAG = "ECMS_WCM_Categories_Tags_01";

		//Configure News page to display tags
		goToNews();
		goToEditPageEditor();
		addContentListViewerTagForNewsPage("General Drives/collaboration","tags", "Tags", "TagsCloud.gtmpl");

		//create new article document in root and private tags
		goToSiteExplorer();
		createNewArticleAndTags(DATA_ARTICLE, DATA_TAG, false);

		//public this document
		click(ELEMENT_FILE_EXPLORER);
		goToNode(ELEMENT_ARTICLE);
		click(ELEMENT_PUBLICATION_TAB_LINK);
		publicDocument();

		//go to News page check private tags is not displayed
		goToNews();
		waitForElementNotPresent(By.linkText(DATA_TAG));
		info("Private tag is not display in News page");

		//delete data
		goToSiteExplorer();
		deleteData(ELEMENT_ARTICLE);
		click(ELEMENT_TAG_COULD);
		deleteTag(DATA_TAG, false);
	}

	/*case02: Check the displaying of public tag
	 * Configure News page to display tags:
	 *   +, go to News page -> page layout
	 *   +, add new content list portlet
	 *   +, Configure portlet: homepath = collaboration/tags, template = TagsCloud.gtmpl, target = news
	 * create new document in site explorer, public its
	 * add public tag for this document
	 * go to News page: check can see and view content of private tag
	 */
	@Test
	public void test02_03_CheckDisplayAndViewOfPublicTag(){
		String DATA_ARTICLE = "ECMS_WCM_Categories_Tags_article_02";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		String DATA_TAG = "ECMS_WCM_Categories_Tags_02";
		By ELEMENT_TAG = By.linkText(DATA_TAG);

		//Configure News page to display tags
		goToNews();
		goToEditPageEditor();
		addContentListViewerTagForNewsPage("General Drives/collaboration","tags", "Tags", "TagsCloud.gtmpl");

		//create new article document in root and public tags
		goToSiteExplorer();
		createNewArticleAndTags(DATA_ARTICLE, DATA_TAG, true);

		//public this document
		click(ELEMENT_FILE_EXPLORER);
		goToNode(ELEMENT_ARTICLE);
		click(ELEMENT_PUBLICATION_TAB_LINK);
		publicDocument();

		//go to News page check public tags is displayed
		goToNews();
		waitForElementPresent(ELEMENT_TAG);
		info("public tag is displayed in News page");
		click(ELEMENT_TAG);
		waitForElementPresent(ELEMENT_ARTICLE);
		assert isElementPresent(ELEMENT_ARTICLE):"Tags wrong";
		info("Check content of public tag is displayed");

		//delete data
		goToSiteExplorer();
		deleteData(ELEMENT_ARTICLE);
		click(ELEMENT_TAG_COULD);
		deleteTag(DATA_TAG, true);
	}

	/*case04: View document/web content while user doesn't have permission to view
	 * Configure News page to display tags:
	 *   +, go to News page -> page layout
	 *   +, add new content list portlet
	 *   +, Configure portlet: homepath = collaboration/tags, template = TagsCloud.gtmpl, target = news
	 * create new document in site explorer
	 * add public tag for this document
	 * set permission for document to user mary does not have view permission
	 * go to News page with user does not have permission to view
	 */
	@Test
	public void test04_ViewDocumentWhileUserNotHavePermission(){
		String DATA_ARTICLE = "ECMS_WCM_Categories_Tags_article_04";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		String DATA_TAG = "ECMS_WCM_Categories_Tags_04";
		By ELEMENT_TAG = By.linkText(DATA_TAG);

		//Configure News page to display tags
		goToNews();
		goToEditPageEditor();
		addContentListViewerTagForNewsPage("General Drives/collaboration","tags", "Tags", "TagsCloud.gtmpl");

		//create new article document in root and public tags
		goToSiteExplorer();
		createNewArticleAndTags(DATA_ARTICLE, DATA_TAG, true);

		//public this document
		click(ELEMENT_FILE_EXPLORER);
		goToNode(ELEMENT_ARTICLE);
		click(ELEMENT_PUBLICATION_TAB_LINK);
		publicDocument();

		//set permission for document
		goToNode(ELEMENT_ARTICLE);
		click(ELEMENT_SYSTEM_TAB);
		click(ELEMENT_PERMISSION_LINK);
		removeDefaultPermissionOfNode();
		close();
		logoutEcms();

		//go to News page to check public tags is displayed
		loginEcms("mary", "gtn");
		goToNews();
		waitForElementPresent(ELEMENT_TAG);
		info("public tag is displayed in News page");
		click(ELEMENT_TAG);
		waitForElementNotPresent(ELEMENT_ARTICLE);
		info("Check content of public tag is not displayed");
		logoutEcms();

		//delete data
		loginEcms(DATA_USER, DATA_PASS);
		waitForElementPresent(ELEMENT_LINK_SETUP, 50000);
		goToSiteExplorer();
		deleteData(ELEMENT_ARTICLE);
		click(ELEMENT_TAG_COULD);
		deleteTag(DATA_TAG, true);
	}
}


