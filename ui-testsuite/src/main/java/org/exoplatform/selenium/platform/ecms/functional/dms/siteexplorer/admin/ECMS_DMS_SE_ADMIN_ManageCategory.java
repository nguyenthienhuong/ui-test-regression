package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.admin;

import static org.exoplatform.selenium.TestLogger.info;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.ecms.EcmsBase;

import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

/**
 * @author thaopth
 * Date: 28/09/2012
 */

public class ECMS_DMS_SE_ADMIN_ManageCategory extends EcmsBase {

	String DATA_USER = "john";
	String DATA_PASS = "gtn";
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
		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}

	@Test
	public void test02_AddCategoryForDocument () {
		By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");
		String DATA_ARTICLE_TITLE = "EMCS_DMS_ManageCat_01";
		By ELEMENT_ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		String DATA_CAT_TREE = "acme";
		String DATA_CAT_PATH = "Defense";
		String DATA_CAT_NAME = "Vision";

		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		goToAddNewContent();

		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		pause(500);

		goToNode(ELEMENT_ARTICLE_PATH);
		addCategoryForNode(DATA_CAT_TREE, false, DATA_CAT_PATH, DATA_CAT_NAME);
		pause(500);

		//Delete data
		deleteDocument(ELEMENT_ARTICLE_PATH);
		waitForElementNotPresent(ELEMENT_ARTICLE_PATH);
	}

	@Test
	public void test03_AddCategoryForUploadedFile()
	{
		String DATA_FILE_NAME = "Uploadfile02";
		String DATA_FILE_LINK = "TestData/Winter.jpg";
		By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");
		By ELEMENT_FILE_PATH = By.xpath("//a[@title='"+DATA_FILE_NAME+".jpg "+"']");
		String DATA_CAT_TREE = "System";
		String DATA_CAT_PATH = "cms/news";
		String DATA_CAT_NAME = "world";

		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		uploadFile(DATA_FILE_NAME, DATA_FILE_LINK);
		pause(1000);

		goToNode(ELEMENT_FILE_PATH);
		pause(500);

		addCategoryForNode(DATA_CAT_TREE, false, DATA_CAT_PATH, DATA_CAT_NAME);
		pause(500);

		//Delete data
		deleteDocument(ELEMENT_FILE_PATH);
		waitForElementNotPresent(ELEMENT_FILE_PATH);
	}
}
