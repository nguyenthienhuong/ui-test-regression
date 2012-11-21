package org.exoplatform.selenium.platform.exogtn.functional.others;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.ManageAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.ManageApplications.*;

/**
 * 
 * @author thaopth
 * date: 26/09/2012
 */

public class EXOGTN_Others_Sitemap extends ManageAccount {
	
	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest() throws Exception {
		//signOut();
		driver.quit();
	}
	
	/* Case 01: Check site map portlet
	 * Import all applications
	 * Add page with site map portlet
	 * Check site map portlet
	 */
	@Test
	public void test01_CheckSiteMapPortlet () {
		By ELEMENT_IMPORT_ALL_BUTTON = By.xpath("//div[text()='Import Applications']");
		String DATA_NODE_NAME = "Sitemap1";
		String DATA_LANGUAGE = "English";
		String DATA_CATEGORY_TITLE = "Navigation";
		Map<String, String> portletIds = new HashMap<String, String>();
		portletIds.put("Navigation/local._web.SiteMapPortlet", "");
		By SITEMAP_PORTLET = By.className("UIRowContainer");
		By ELEMNT_FORUMS_LINK = By.linkText("Forums");
		By ELEMENT_FORUM_ACTION_BAR = By.id("UIForumActionBar");
		
		signIn("john", "gtn");
		showImportApplication(true);
			
		info("Import all apps");
		click(ELEMENT_IMPORT_ALL_BUTTON);
		waitForConfirmation("This will automatically import all gadgets and portlets into new categories.");
		pause(2000);
		
		info("Add page");
		goToAddPageEditor();
		
		info("Add new page");
		addNewPageEditor(DATA_NODE_NAME, DATA_NODE_NAME, DATA_LANGUAGE, DATA_CATEGORY_TITLE, portletIds, true);
		waitForElementPresent(SITEMAP_PORTLET);
		
		info("Click on a page");
		click(ELEMNT_FORUMS_LINK);
		pause(1000);
		
		info("Verify home page is displayed");
		waitForElementPresent(ELEMENT_FORUM_ACTION_BAR);
		
		/*----Reset data-------*/
		
		info("Delete page");
		goToManagePages();
		deletePage(PageType.GROUP,DATA_NODE_NAME);
		goToGroupSites();
		deleteNode("Administration", "Application Manager", DATA_NODE_NAME, false);
		showImportApplication(false);
		
	}
}