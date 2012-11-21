package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;

public class ECMS_DMS_SE_DocumentFolder extends EcmsBase {
	public static String DATA_CONTENT_FOLDER_NAME_01 = "FNC_ECMS_FEX_CREATE_02_01_con";
	public static String DATA_CONTENT_FOLDER_TITLE_01 = "FNC_ECMS_FEX_CREATE_02_01_con";
	public static String DATA_DOC_FOLDER_TITLE_01 = "FNC_ECMS_FEX_CREATE_02_01_doc";
	public static String DATA_DOC_FOLDER_NAME_01 = "FNC_ECMS_FEX_CREATE_02_01_doc";
	public static String DATA_DOC_FOLDER_NAME_02_SUB = "FNC_ECMS_FEX_CREATE_02_02_sub";
	public static String DATA_DOC_FOLDER_TITLE_02_SUB = "FNC_ECMS_FEX_CREATE_02_02_sub";
	public static String DATA_DOC_FOLDER_TITLE_02 = "FNC_ECMS_FEX_CREATE_02_02_doc";
	public static String DATA_DOC_FOLDER_NAME_02 = "FNC_ECMS_FEX_CREATE_02_02_doc";
	public static String DATA_DOC_FOLDER_TITLE_21 = "FNC_ECMS_FEX_CREATE_02_21_doc";
	public static String DATA_DOC_FOLDER_NAME_21 = "FNC_ECMS_FEX_CREATE_02_21_doc";
	public static String DATA_CON_FOLDER_TITLE_21 = "FNC_ECMS_FEX_CREATE_02_21_con";
	public static String DATA_CON_FOLDER_NAME_21 = "FNC_ECMS_FEX_CREATE_02_21_con";
	public static String DATA_DOC_FOLDER_TITLE_21_LOCK = "FNC_ECMS_FEX_CREATE_02_21_doc";
	public static String DATA_DOC_FOLDER_NAME_21_LOCK = "FNC_ECMS_FEX_CREATE_02_21_doc";
	public static String DATA_DOC_FOLDER_TITLE_21_SUB = "FNC_ECMS_FEX_CREATE_02_21_sub";
	public static String DATA_DOC_FOLDER_NAME_21_SUB = "FNC_ECMS_FEX_CREATE_02_21_sub";
	public static String USER = "john";
	public static String PASS = "gtn";
	// add a document folder in a content folder 
	@Test
	public void test01_AddDocumentFolderInContentFolder() {
		By bContent = By.xpath("//a[@title='" + DATA_CONTENT_FOLDER_TITLE_01 + " ']");
		By bDoc= By.xpath("//a[@title='"+ DATA_DOC_FOLDER_TITLE_01 + " ']");
		
		info("Add a document folder in a content folder");

		//create a content folder
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE_01,DATA_CONTENT_FOLDER_NAME_01);
		waitForElementPresent(bContent);
		pause(500);
		goToNode(bContent);
		pause(500);

		//create a document folder
		createNewDocumentFolder(DATA_DOC_FOLDER_TITLE_01, DATA_DOC_FOLDER_NAME_01);
		waitForElementPresent(bDoc);

		//delete data
		deleteDocument(bDoc);
		deleteDocument(bContent);
	}

	// add a document folder in a document folder 
	@Test
	public void test02_AddDocumentFolderInDocumentFolder() {
		By bDoc=By.xpath("//a[@title='" + DATA_DOC_FOLDER_TITLE_02 + " ']");
		By bDocSub=By.xpath("//a[@title='"+ DATA_DOC_FOLDER_TITLE_02_SUB + " ']");
		info("Add a document folder in a document folder");

		//create a document folder
		createNewDocumentFolder(DATA_DOC_FOLDER_TITLE_02,DATA_DOC_FOLDER_NAME_02);
		waitForElementPresent(bDoc);
		pause(500);
		goToNode(bDoc);
		pause(500);

		//create a child document folder
		createNewDocumentFolder(DATA_DOC_FOLDER_TITLE_02_SUB, DATA_DOC_FOLDER_NAME_02_SUB);
		waitForElementPresent(bDocSub);

		//delete data
		deleteDocument(bDocSub);
		deleteDocument(bDoc);
	}

	// add a document folder in a locked document folder 
	@Test
	public void test21_AddDocumentFolderInLockedDocumentFolder() {
		By bDoc=By.xpath("//a[@title='" + DATA_DOC_FOLDER_TITLE_21_LOCK + " ']");
		By bDocLock=By.xpath("//a[@title='" + DATA_DOC_FOLDER_TITLE_21_LOCK + " (Locked by "+ USER+ ")']");
		By bDocSub=By.xpath("//a[@title='"+ DATA_DOC_FOLDER_TITLE_21_SUB + " ']");
		
		info("Add a document folder in a document folder");

		//create a document folder
		createNewDocumentFolder(DATA_DOC_FOLDER_TITLE_21_LOCK,DATA_DOC_FOLDER_NAME_21_LOCK);
		waitForElementPresent(bDoc);
		pause(500);
		lockNode(bDoc);
		goToNode(bDocLock);
		pause(500);

		//create a child document folder
		createNewDocumentFolder(DATA_DOC_FOLDER_TITLE_21_SUB, DATA_DOC_FOLDER_NAME_21_SUB);
		waitForElementPresent(bDocSub);

		//delete data
		deleteDocument(bDocSub);
		deleteDocument(bDocLock);
	}

	// add a document folder in a locked content folder 
		@Test
		public void test21_AddDocumentFolderInLockedContentFolder() {
			By bDoc=By.xpath("//a[@title='"+ DATA_DOC_FOLDER_TITLE_21 + " ']");
			By bCon=By.xpath("//a[@title='" + DATA_CON_FOLDER_TITLE_21 + " ']");
			By bConLock=By.xpath("//a[@title='" + DATA_CON_FOLDER_TITLE_21 + " (Locked by "+ USER+ ")']");
			info("Add a document folder in a document folder");

			//create a document folder
			createNewContentFolder(DATA_CON_FOLDER_TITLE_21,DATA_CON_FOLDER_NAME_21);
			waitForElementPresent(bCon);
			pause(500);
			lockNode(bCon);
			goToNode(bConLock);
			pause(500);

			//create a child document folder
			createNewDocumentFolder(DATA_DOC_FOLDER_TITLE_21, DATA_DOC_FOLDER_NAME_21);
			waitForElementPresent(bDoc);

			//delete data
			deleteDocument(bDoc);
			deleteDocument(bConLock);
		}
	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);

		actions = new Actions(driver);
		loginEcms(USER, PASS);
		goToSiteExplorer();
	}

	@AfterMethod
	public void afterMethod() {
		logoutEcms();
		driver.quit();
	}
}