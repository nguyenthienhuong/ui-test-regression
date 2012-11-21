package org.exoplatform.selenium.platform.ecms.functional.wcm.categories;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: Lientm
 * @date: 23/10/2012
 */
public class ECMS_WCM_Categories_CategoryNavigation extends EcmsBase {

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";

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
	
	/*case17: Check the displaying of document/web content when change target path
	 * change home path on config category navigation portlet of New pages
	 * check page is changed follow home path
	 */
	@Test
	public void test017_CheckDisplayWhenChangeTargetPath(){
		//go to acme/news page
		goToNews();
		
		//change to edit mode
		info("Enable edit mode");
		enableEditMode(true);
		
		// go to Preferences of Categories
		waitForElementPresent(ELEMENT_CATEGORY_CONTAINER);
		pause(1000);
		mouseOver(ELEMENT_CATEGORY_CONTAINER, true);
		click(ELEMENT_CATEGORY_PREFER);
		
		//select folder path
		selectHomePathOnContentList("General Drives/Sites Management/acme","categories");
		save();
		
		//check category of page is changed
		waitForElementPresent(ELEMENT_CATEGORY_ACME);
		assert isElementPresent(ELEMENT_CATEGORY_ACME);
		assert isElementPresent(ELEMENT_CATEGORY_DEFENCE):"Category display is not true";
		assert isElementPresent(ELEMENT_CATEGORY_VISION):"Category display is not true";
		assert isElementPresent(ELEMENT_CATEGORY_VISIBILITY):"Category display is not true";
		assert isElementPresent(ELEMENT_CATEGORY_HEALING):"Category display is not true";
		assert isElementPresent(ELEMENT_CATEGORY_IMMUNITY):"Category display is not true";
		assert isElementPresent(ELEMENT_CATEGORY_MOVEMENT):"Category display is not true";
		assert isElementPresent(ELEMENT_CATEGORY_NATURAL):"Category display is not true";
		
		//return old category for News page
		mouseOver(ELEMENT_CATEGORY_CONTAINER, true);
		click(ELEMENT_CATEGORY_PREFER);
		selectHomePathOnContentList("General Drives","acme-category");
		save();	
	}
}
