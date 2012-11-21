package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadFile;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;


import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_016 extends EcmsBase{
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_SITES_MANAGEMENT_DRIVE = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	
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
	/*
	 * Go to Content Explorer > DMS Administration Drive
	 * Upload a document then remove it
	 * Expected result: Uploaded document is remove successfully, no error in console
	 */
	@Test
	public void test_removeUploadedFile(){
		String ELEMENT_UPLOAD_FILE_NAME = "ECMS_DMS_SE_Upload_imgfile.jpg";
		By DATA_CONTENT_FOLDER_PATH = By.xpath("//a[@title='"+ELEMENT_UPLOAD_FILE_NAME+" "+"']");
		//Go to Site Explorer
		goToSiteExplorer();
		//Choose Site Management driver
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		//Upload a document
		uploadFile(ELEMENT_UPLOAD_FILE_NAME,"TestData/"+ELEMENT_UPLOAD_FILE_NAME);
		//Remove the uploaded document
		deleteDocument(DATA_CONTENT_FOLDER_PATH);
		//Capture screen.
		captureScreen("test_removeUploadedFile");
	}

}
