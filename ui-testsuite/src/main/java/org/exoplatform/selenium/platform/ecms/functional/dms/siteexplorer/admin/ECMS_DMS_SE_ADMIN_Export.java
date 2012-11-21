package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.admin;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
/**
 * @author thaopth
 * date: 27/09/2012
 */

public class ECMS_DMS_SE_ADMIN_Export extends EcmsBase {
	String DATA_USER = "john";
	String DATA_PASS = "gtn";
	@BeforeMethod
	public void beforeMethods() throws Exception {
		init();
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

	public static void init(){
		String browser = System.getProperty("browser");
		if("chrome".equals(browser)){
			driver = new ChromeDriver();
			chromeFlag = true;
		} else if ("iexplorer".equals(browser)){
			driver = new InternetExplorerDriver();
			ieFlag = true;
		} else {
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("Export");
			driver = new FirefoxDriver(profile);
		}
		baseUrl = System.getProperty("baseUrl");
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
	}

	/* Case No 144: Export root path
	 * Go to Site management
	 * Export root path
	 */
	@Test 
	public void test01_ExportRootPath()
	{
		goToSiteExplorer();

		debug("Select root path");
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		exportNode(true,true, false);
	}

	/* Case No 145: Export a folder
	 */
	@Test
	public void test02_ExportAFolder () {
		By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");
		String DATA_CONTENT_FOLDER_NAME = "folder01";
		By DATA_COTENT_FOLDER_PATH = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_NAME+" "+"']");

		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		createNewContentFolder(DATA_CONTENT_FOLDER_NAME, DATA_CONTENT_FOLDER_NAME);
		pause(500);

		goToNode(DATA_COTENT_FOLDER_PATH);
		pause(500);
		exportNode(true, false, false);

		debug("Delete data");
		deleteDocument(DATA_COTENT_FOLDER_PATH);
	}
	/*
	 * Case 146: Export a document
	 */
	@Test
	public void test03_ExportADocument () {
		By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");
		String DATA_DOCUMENT_NAME = "document01";
		By DATA_DOCUMENT_PATH = By.xpath("//a[@title='"+DATA_DOCUMENT_NAME+" "+"']");

		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		goToAddNewContent();
		createNewArticle(DATA_DOCUMENT_NAME, DATA_DOCUMENT_NAME, DATA_DOCUMENT_NAME, DATA_DOCUMENT_NAME);
		pause(500);

		goToNode(DATA_DOCUMENT_PATH);
		pause(500);
		exportNode(true, false, true);

		debug("Delete data");
		deleteDocument(DATA_DOCUMENT_PATH);

	}
	/*
	 * Case 147: Export an uploaded file
	 */
	@Test
	public void test04_ExportAnUploadedFile () {
		String DATA_FILE_NAME = "Uploadfile01";
		String DATA_FILE_LINK = "TestData/Winter.jpg";
		By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");
		By ELEMENT_FILE_PATH = By.xpath("//a[@title='"+DATA_FILE_NAME+".jpg "+"']");

		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		uploadFile(DATA_FILE_NAME, DATA_FILE_LINK);
		pause(1000);

		goToNode(ELEMENT_FILE_PATH);
		pause(500);
		exportNode(true, false, false);

		//Delete data
		deleteDocument(ELEMENT_FILE_PATH);

	}

	/*
	 * Case 148: Export a node using format document view
	 * Create node
	 * Export node using format: document view
	 */
	@Test
	public void test05_ExportANodeWithDocumentViewFormat () {
		By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");
		String DATA_DOCUMENT_NAME = "document02";
		By DATA_DOCUMENT_PATH = By.xpath("//a[@title='"+DATA_DOCUMENT_NAME+" "+"']");

		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		goToAddNewContent();
		createNewAnnouncement(DATA_DOCUMENT_NAME, DATA_DOCUMENT_NAME);
		pause(500);

		goToNode(DATA_DOCUMENT_PATH);
		pause(500);
		exportNode(false, false, false);

		debug("Delete data");
		deleteDocument(DATA_DOCUMENT_PATH);
	}
}
