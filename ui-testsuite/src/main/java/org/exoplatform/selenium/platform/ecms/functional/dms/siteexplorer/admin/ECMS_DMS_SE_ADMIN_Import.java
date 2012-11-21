package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.admin;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

/**
 * @author thaopth
 * Date: 28/09/2012
 */
public class ECMS_DMS_SE_ADMIN_Import extends EcmsBase {
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
	public void test01_ImportANodeInToContentFolder () {
		By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");
		String DATA_CONTENT_FOLDER_NAME = "Import01";
		By DATA_CONTENT_FOLDER_PATH = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_NAME+" "+"']");
		String DATA_FILE_LINK = "TestOutput/sysview.xml";
		String DATA_FILE_VERSION = "TestOutput/sysview_versionHistory.zip";

		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		createNewContentFolder(DATA_CONTENT_FOLDER_NAME, DATA_CONTENT_FOLDER_NAME);
		pause(500);

		goToNode(DATA_CONTENT_FOLDER_NAME);
		pause(500);

		debug("Import node into content folder");
		importNode(DATA_FILE_LINK,DATA_FILE_VERSION , "Remove Existing", true);

		//Delete data
		goToNode(DATA_CONTENT_FOLDER_PATH);
		deleteDocument(DATA_CONTENT_FOLDER_PATH);
	}
}
