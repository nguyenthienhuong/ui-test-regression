/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.listcontent;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.exoplatform.selenium.platform.ecms.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.ContextMenu;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.WcmAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by The eXo Platform SAS Author : Hoang Manh Dung
 * dunghm@exoplatform.com Oct 24, 2012
 */
public class ECMS_WCM_Viewer_ListContent_Other extends EcmsBase {
	String DATA_USER = "john";

	String DATA_PASS = "gtn";

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();    
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout ECMS");
		//logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	} 
	/**
	 * Add new content for CLV
	 * 
	 */
	@Test
	public static void test12_AddNewContentForCLVPage() throws Exception {
		//Change to Edit Mode
		ContentTemplate.changeEditMode();    
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");

		//Goto add content screen
		WcmAdmin.doCLVEditingToolAction("RssIcon","AddContentIcon");
		ContentTemplate.createNewArticle("Test New Article","test-new-article","Test Summary","Test Content");
		waitForElementPresent(By.xpath("//div[@class='GtmplTitle' and contains(text(),'Test New Article')]"));    
		click(ELEMENT_BUTTON_BACK);

		//Reset data
		WcmAdmin.doCLVEditingToolAction("RssIcon","ManageContentIcon");
		ContextMenu.deleteDocument(By.xpath("//a[contains(@title,'Test New Article')]"));

	}

	/**
	 * Manage content of a CLV
	 */
	@Test
	public static void test13_ManageContentForCLVPage() throws Exception {
		ContentTemplate.changeEditMode();
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");

		// Go to manage content screen
		WcmAdmin.doCLVEditingToolAction("RssIcon","ManageContentIcon");

		// Create Document
		click(ActionBar.ELEMENT_MENU_NEW_CONTENT_LINK);
		ContentTemplate.createNewArticle("Test Document","test-document", "Test Summary", "Test Content");
		waitForElementPresent(By.xpath("//div[@class='GtmplTitle' and contains(text(),'Test Document')]"));  

		By doc = By.xpath("//a[contains(@title,'Test Document')]");    
		ContextMenu.lockNode(doc);
		ContextMenu.checkLockNode(doc);
		ContextMenu.unLockNode(doc);

		// Delete Document
		ContextMenu.deleteDocument(doc);
	}
}
