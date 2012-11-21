package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_012 extends EcmsBase{
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_COLLABORATION_DRIVER = By.xpath("//a[@class='DriveLabel' and @title = 'collaboration']");
	public static final By ELEMENT_PRIVATE_DRIVER = By.xpath("//a[@class='DriveLabel' and @title = 'Private']");
	public static final By DATA_CONTENT_FOLDER_PATH = By.xpath("//div[@title='Favorites']");
	
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
	 * - Go to Content Explorer
	 * - Delete Favorite folder in the Private Drive
	 * - Choose another drive/ folder
	 * Expected result: No UI error and no exception
	 */
	@Test
	public void test_deleteFavoriteDriver(){
		//Go to Content Explorer
		goToSiteExplorer();
		//Go to Private driver
		chooseDrive(ELEMENT_PRIVATE_DRIVER);
		//Delete Favorite folder
		deleteDocument(DATA_CONTENT_FOLDER_PATH);
		//Choose another driver/folder: Choose Collaboration driver
		chooseDrive(ELEMENT_COLLABORATION_DRIVER);
		// Capture screen
		captureScreen("REG_PLF307_ECMS_012");
	}

}
