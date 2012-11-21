package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadFile;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadTheSameFile;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_002 extends EcmsBase{
	
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_REPLACE_DATA_LINK =By.linkText("Replace data");
	
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
	 * Case: Replacing a file in categories
	 * Go to FE
	 * Upload a file
	 * Upload the same file again
	 * Choose replace data
	 * Expected: The existed file is replaced
	 */
	@Test
	public void test_ReplaceFile(){
	String ELEMENT_UPLOAD_FILE_NAME = "ECMS_DMS_SE_Upload_imgfile.jpg";
	
		//Go to Site Explore
		goToSiteExplorer();
		//Upload a file
		uploadFile(ELEMENT_UPLOAD_FILE_NAME,"TestData/"+ELEMENT_UPLOAD_FILE_NAME);
		//Upload the same file 
		uploadTheSameFile(ELEMENT_UPLOAD_FILE_NAME,"TestData/"+ELEMENT_UPLOAD_FILE_NAME);
		//Replace data
		waitForElementPresent(ELEMENT_REPLACE_DATA_LINK);
		click(ELEMENT_REPLACE_DATA_LINK);
		click(ELEMENT_CLOSE_BUTTON);
		captureScreen("test_ReplaceFile_REG_PLF307_ECMS_002.png");
	}

}
