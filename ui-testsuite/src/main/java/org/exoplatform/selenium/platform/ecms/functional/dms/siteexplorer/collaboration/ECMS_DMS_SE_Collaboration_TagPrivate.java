package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.collaboration;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;

public class ECMS_DMS_SE_Collaboration_TagPrivate  extends EcmsBase {

	/*
	 * @author: HangNTT
	 * @date: 26/09/2012
	 */
	By SITE_MANAGEMENT_ACME = By.xpath("//a[@title='acme ']");

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("john","gtn");
	}

	@Test
	public void test12_AddPrivateTagForDocument (){

		String ARTICLE_NAME_TITLE="Article_Document";
		String ARTICLE_SUM="Summary of article";
		String ARTICLE_CONTENT="Content of article";
		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create article ");
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT); 

		info("Verify Add Content form is closed");
		waitForElementNotPresent(ELEMENT_ARTICLE_SUMMARY_FRAME);
		waitForElementNotPresent(ELEMENT_ARTICLE_CONTENT_FRAME);

		info("Add tags for private");
		addTagForNode("collaboration_02_012", false);	

		info("Delete data");
		deleteTag("collaboration_02_012", false);
		
		waitForElementPresent(ELEMENT_FILE_EXPLORER);
		click(ELEMENT_FILE_EXPLORER);
		waitForElementPresent(ARTICLE_PATH);
		goToNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
	}

	@Test
	public void test13_AddPrivateTagForUploadedFile (){

		String DATA_UPLOAD_FILE_NAME = "AddOnePrivateTagForUPLoadFile";
		By ELEMENT_UPLOAD_FILE = By.xpath("//a[@title='"+DATA_UPLOAD_FILE_NAME+".doc "+"']");
		String DATA_UPLOAD_FILE_LINK = "TestData/ECMS_DMS_SE_Upload_docfile.doc";

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);
		uploadFile(DATA_UPLOAD_FILE_NAME, DATA_UPLOAD_FILE_LINK);
		waitForElementPresent(ELEMENT_UPLOAD_FILE);
		goToNode(ELEMENT_UPLOAD_FILE);
		
		//info("Add tags for private");
		addTagForNode("collaboration_02_013",false);	

		info("Delete data");
		deleteTag("collaboration_02_013",false);
		waitForElementPresent(ELEMENT_FILE_EXPLORER);
		click(ELEMENT_FILE_EXPLORER);
		goToNode(ELEMENT_UPLOAD_FILE);
		waitForElementPresent(ELEMENT_UPLOAD_FILE);
		deleteDocument(ELEMENT_UPLOAD_FILE);
	}

	@AfterMethod
	public void afterTest() throws Exception {
		logoutEcms ();
		driver.quit();
	}
}