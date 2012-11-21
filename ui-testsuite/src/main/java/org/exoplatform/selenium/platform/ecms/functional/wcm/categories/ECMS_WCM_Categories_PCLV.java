package org.exoplatform.selenium.platform.ecms.functional.wcm.categories;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: Lientm
 * @date: 23/10/2012
 */

public class ECMS_WCM_Categories_PCLV extends EcmsBase {

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";

	public static final By ELEMENT_DROP_TARGET = By.xpath("//*[@id='ACMEParameterized']/div");
	public static final By ELEMENT_URL_PORTLET = By.xpath("//div[text()='Content by URL']/../../../../../../../div[contains(@id,'UIPortlet')][1]");
	public static final By ELEMENT_EDIT_URL_PORTLET = By.xpath("//div[text()='Content by URL']/../a[@class='EditIcon']");
	public static final By ELEMENT_DELETE_URL_PORTLET = By.xpath("//div[text()='Content by URL']/../a[@class='DeleteIcon']");

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
		//delete Content By URL if it is not deleted
		goToNews();
		goToEditPageEditor();
		removePortlet(ELEMENT_URL_PORTLET, ELEMENT_URL_PORTLET, ELEMENT_DELETE_URL_PORTLET);

		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}

	//Function to to create a new article in acme drive/defence -> this Function to is used only in this class
	public void createNewArticleInAcme(String title){		
		By ELEMENT_ARTICLE = By.linkText(title);
		By ELEMENT_DEFENCE = By.linkText("Defense");

		chooseDrive(ELEMENT_ACME_DRIVER);
		click(ELEMENT_DEFENCE);
		pause(1000);
		if (waitForAndGetElement(ELEMENT_ARTICLE,5000,0) != null){
			click(ELEMENT_ARTICLE);
			deleteData(ELEMENT_ARTICLE);
			click(ELEMENT_DEFENCE);
		}
		info("Add new document in acme drive");
		goToAddNewContent();
		createNewArticle(title, title, "", "");
		while (getElement(ELEMENT_ARTICLE) == null){
			click(ELEMENT_DEFENCE);
		}
		waitForElementPresent(ELEMENT_ARTICLE);
		assert isElementPresent(ELEMENT_ARTICLE):"Can not create new article";
		info("Create new document (Article) successfully");
	}

	//Function to configure PCLV portlet with show in page = News -> this Function to use only this class
	public static void configurePCLVShowInPage(){
		info("Configure PCLV portlet");
		waitForElementPresent(ELEMENT_NEWS_PORTLET);
		mouseOver(ELEMENT_NEWS_PORTLET, true);
		click(ELEMENT_NEW_EDIT_PORTLET);
		info("Select show in page = news");
		waitForElementPresent(ELEMENT_ADD_TARGET);
		click(ELEMENT_ADD_TARGET);
		click(ELEMENT_UPLEVEL);
		click(ELEMENT_NEW_TARGET_PATH);
		save();
		close();
	}

	//Function to add content by URL portlet and configure it with show in page = News -> Function to use only this class
	public static void configureContentByURLPortletShowInPage(){
		info("Add Content by URL portlet for News page");
		if (waitForAndGetElement(ELEMENT_URL_PORTLET,10000,0) == null){
			click(ELEMENT_MENU_CONTENT_LINK);
			waitForElementPresent(ELEMENT_CONTENT_BYURL_PORTLET);
			dragAndDropToObject(ELEMENT_CONTENT_BYURL_PORTLET, ELEMENT_DROP_TARGET);
			pause(2000);
			info("Add Content By URL portlet successfully");
		}else{
			info("Content By URL portlet has already exited");
		}	
		mouseOver(ELEMENT_URL_PORTLET, true);
		click(ELEMENT_EDIT_URL_PORTLET);
		info("Select show in page = news");
		waitForElementPresent(ELEMENT_ADD_TARGET);
		click(ELEMENT_ADD_TARGET);
		click(ELEMENT_UPLEVEL);
		click(ELEMENT_NEW_TARGET_PATH);
		save();
		close();
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH,50000);
	}


	/*case01+03: 
	 * case01: Check the displaying of document/web content in Parameterized Content List Viewer
	 * case03: Check content of document 
	 * configureure data
	 *  step 1: create new document in acme-category driver
	 *  step 2: public its
	 *  step 3: configureure PCLV portlet have show in page = news
	 *  step 4: add new content by URL portlet, configureure to show in page News
	 */
	@Test
	public void test01_03_CheckDisplayAndContentOfDocument_WebContentInContentListViewer(){
		String DATA_ARTICLE = "ECMS_WCM_Categories_PCLV_acticle_01";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		By ELEMENT_ARTICLE_DIV = By.xpath("//div[text()='" + DATA_ARTICLE + "']");
		By ELEMENT_DEFENCE = By.linkText("Defense");
		By ELEMENT_ARTICLE_DEFENSE = By.xpath("//a[text()='Defense']/../../../../*//a[text()='" + DATA_ARTICLE + "']");


		//---create new document in acme drive: create article document in Movement---
		goToSiteExplorer();
		createNewArticleInAcme(DATA_ARTICLE);

		//---public this document---
		click(ELEMENT_ARTICLE);
		click(ELEMENT_PUBLICATION_TAB_LINK);
		publicDocument();

		//---Go to Edit News page--- 
		goToNews();
		goToEditPageEditor();

		//---Configure Parameterized Content List Viewer (PCLV) portlet---
		configurePCLVShowInPage();

		//---Add content by URL portlet---
		configureContentByURLPortletShowInPage();

		//---Check the displaying and content of document/web content---
		waitForElementPresent(ELEMENT_DEFENCE);
		click(ELEMENT_DEFENCE);
		info("check user John can see document");
		waitForElementPresent(ELEMENT_ARTICLE);
		assert isElementPresent(ELEMENT_ARTICLE):"Document is not display";
		click(ELEMENT_ARTICLE);
		waitForElementPresent(ELEMENT_ARTICLE_DIV);
		assert isElementPresent(ELEMENT_ARTICLE_DIV):"Document content is not displayed";
		assert isElementPresent(By.xpath("//div[text()='Summary :']")):"Document content is not displayed";
		assert isElementPresent(By.xpath("//div[text()='Content :']")):"Document content is not displayed";

		goToSiteExplorer();
		//		chooseDrive(ELEMENT_ACME_DRIVER);
		//		while (getElement(ELEMENT_ARTICLE_DEFENSE) == null){
		//			click(ELEMENT_DEFENCE);
		//		}
		deleteData(ELEMENT_ARTICLE_DEFENSE);
	}

	//	case02: Check the displaying of document/web content when user does not have permission to view

	@Test
	public void test02_CheckDisplayOfDocument_WebcontentWhenUserNotHavePermission(){
		String DATA_ARTICLE = "ECMS_WCM_Categories_PCLV_acticle_02";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		By ELEMENT_ARTICLE_DEFENSE = By.xpath("//a[text()='Defense']/../../../../*//a[text()='" + DATA_ARTICLE + "']");
		By ELEMENT_DEFENCE = By.linkText("Defense");

		//---create new document in acme drive: create article document in Movement---
		goToSiteExplorer();
		createNewArticleInAcme(DATA_ARTICLE);

		info("Set for user mary does not have view permission this document");
		click(ELEMENT_ARTICLE);
		waitForElementPresent(ELEMENT_COLLABORATION_TAB);
		click(ELEMENT_COLLABORATION_TAB);
		if (waitForAndGetElement(ELEMENT_PERMISSION_LINK,5000,0) == null){
			addView("WCM Category View","Collaboration","viewPermissions");
			goToSiteExplorer();
			chooseDrive(ELEMENT_ACME_DRIVER);
			while (getElement(ELEMENT_ARTICLE_DEFENSE) == null){
				click(ELEMENT_DEFENCE);
			}
			click(ELEMENT_ARTICLE_DEFENSE);
			click(ELEMENT_COLLABORATION_TAB);
		}
		click(ELEMENT_PERMISSION_LINK);
		removeDefaultPermissionOfNode();
		close();
		click(ELEMENT_PUBLICATION_TAB_LINK);
		publicDocument();

		//---Configure News page--- 
		goToNews();
		goToEditPageEditor();

		//---Configure Parameterized Content List Viewer (PCLV) portlet---
		configurePCLVShowInPage();

		//---Add content by URL portlet---
		configureContentByURLPortletShowInPage();

		//---Check the displaying of document/web content with user John---
		waitForElementPresent(ELEMENT_DEFENCE);
		click(ELEMENT_DEFENCE);
		info("check user John can see document");
		waitForElementPresent(ELEMENT_ARTICLE);
		assert isElementPresent(ELEMENT_ARTICLE):"Document is not display";
		waitForElementPresent(ELEMENT_ACCOUNT_NAME_LINK);
		logoutEcms();

		//---Check the displaying of document/web content with user mary---
		loginEcms("mary", "gtn");
		goToNews();
		goToNode(ELEMENT_DEFENCE);
		waitForElementNotPresent(ELEMENT_ARTICLE);
		info("User does not read permission that can not see document");
		logoutEcms();

		//---delete data---
		loginEcms(DATA_USER, DATA_PASS);		
		goToSiteExplorer();
		chooseDrive(ELEMENT_ACME_DRIVER);
		while (getElement(ELEMENT_ARTICLE_DEFENSE) == null){
			click(ELEMENT_DEFENCE);
		}
		deleteData(ELEMENT_ARTICLE_DEFENSE);
	}

	/*Case08: Change the order of document in PCLV
	 * go to News page
	 * enable edit mode
	 * configure PCLV portlet to have Order by title Ascendant -> check Documents are listed in ascendant title
	 * configure PCLV portlet to have Order by title Descendant -> check Documents are listed in descendant title
	 */
	@Test
	public void test08_ChangeOrderOfDocumentInPCLV(){
		//go to edit News page
		goToNews();
		info("Enable edit mode");
		enableEditMode(true);

		// go to Preferences of PCLV
		waitForElementPresent(ELEMENT_PCLV_CONTAINER);
		pause(1000);
		mouseOver(ELEMENT_PCLV_CONTAINER, true);
		click(ELEMENT_PCLV_PREFER);

		//---setting order by title Ascendant---
		waitForElementPresent(ELEMENT_ORDER_BY);
		select(ELEMENT_ORDER_BY, "Title");
		click(ELEMENT_ASCE_RADIO);
		save();
		waitForElementNotPresent(ELEMENT_ORDER_BY);

		//check display of document in PCLV
		info("Check display of document in PCLV when order by title ascendant");
		//check "Fire" is displayed the first
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[1]/div[3]/div/a[text()='Fire']")):"Ordering by following title ascendant is not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[2]/div[2]/div/a[text()='Flight']")):"Ordering by following title ascendant is not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[3]/div[2]/div/a[text()='Ice']")):"Ordering by following title ascendant not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[4]/div[2]/div/a[text()='New: Invisibility']")):"Ordering by following title ascendant is not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[5]/div[2]/div/a[text()='New: Speed']")):"Ordering by following title ascendant is not true";
		info("Document title is ordered By title ascendantly");

		//---setting order by title Descendant---
		mouseOver(ELEMENT_PCLV_CONTAINER, true);
		click(ELEMENT_PCLV_PREFER);
		waitForElementPresent(ELEMENT_ORDER_BY);
		select(ELEMENT_ORDER_BY, "Title");
		click(ELEMENT_DESC_RADIO);
		save();
		waitForElementNotPresent(ELEMENT_ORDER_BY);

		//check display of document in PCLV
		info("Check display of document in PCLV when order by title descendant");
		//check "Fire" is displayed first
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[1]/div[3]/div/a[text()='New: Speed']")):"Order by follow title descendant is not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[2]/div[2]/div/a[text()='New: Invisibility']")):"Order by follow title descendant is not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[3]/div[2]/div/a[text()='Ice']")):"Order by follow titile title descendant not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[4]/div[2]/div/a[text()='Flight']")):"Order by follow title descendant is not true";
		assert isElementPresent(By.xpath("//div[text()='Top News']/../../../div[5]/div[2]/div/a[text()='Fire']")):"Order by follow title descendant is not true";
		info("Document title is ordered By title descendant");
	}
}