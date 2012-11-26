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
import static org.exoplatform.selenium.platform.PageManagement.deletePageAtManagePageAndPortalNavigation;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;


import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.PageEditor;
import org.exoplatform.selenium.platform.ecms.WcmAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by The eXo Platform SAS Author : Hoang Manh Dung
 * dunghm@exoplatform.com Nov 1, 2012
 */
public class ECMS_WCM_Viewer_ListContent_EditIcon extends EcmsBase {
	String DATA_USER = "john";
	String DATA_PASS = "gtn";
	public static By ELEMENT_PUBLICATION = By.linkText("Publications");
	public static By ELEMENT_PUBLIC_STATUS = By.xpath("//a[contains(text(), 'Published')]");
	public static By EMENET_CURRENT_STATUS = By.xpath("//a[@class='CurrentStatus']");
	public static By ELEMENT_CURRENT_PUBLIC_STATUS = By.xpath("//a[@class='CurrentStatus' and contains(text(), 'Published')]");

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
	
	/*
	 * Change to View mode
	 */
	@Test
	public void test01_changeModeView() {
		changeEditMode();
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");
		mouseOver("//a[@class='RssIcon']", true);
		changeEditMode();
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");
	}
	
	/*
	 * Verify data not change after back from edit content screen
	 */
	@Test
	public void test02_verifyDataNotChange(){    
		String DATA_ARTICLE_TITLE = "Test arcticle";
		String DATA_PAGE_NAME= "CLVTEST";
		String DATA_FOLDER_NAME = "FolderTest";

		By DATA_FOLDER_PATH = By.xpath("//a[@title='"+DATA_FOLDER_NAME+" "+"']");
		String DATA_CLV_PATH = "General Drives/Sites Management/acme";
		By DATA_CONTENT_TITLE = By.xpath("//a[text()='"+DATA_ARTICLE_TITLE+"']");
		By EDIT_CONTENT_ICON = By.xpath("//div[@class='UICLVPortlet']//*[@title='Edit in Content Explorer']");

		addNewContentTest(DATA_FOLDER_NAME, DATA_FOLDER_PATH, DATA_ARTICLE_TITLE);

		goToOverView();

		PageEditor.createPage_ContentList_CLVpath(DATA_PAGE_NAME, DATA_CLV_PATH, DATA_FOLDER_NAME);

		editContentInEditMode(DATA_CONTENT_TITLE, EDIT_CONTENT_ICON);    

		click(ELEMENT_BUTTON_BACK); 

		waitForElementPresent(DATA_CONTENT_TITLE);

		//Reset data
		goToSiteExplorer();
		goToNode(ELEMENT_ACME_SITE_LINK);
		waitForElementPresent(DATA_FOLDER_PATH);

		//deleteDocument(DATA_FOLDER_PATH);
		rightClickOnElement(DATA_FOLDER_PATH);
		click(ELEMENT_MENU_DELETE);
		waitForTextPresent("Confirm Deletion");
		click(ELEMENT_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_OK_BUTTON);

		info("Go to manage page and delete page");
		deletePageAtManagePageAndPortalNavigation(DATA_PAGE_NAME, true, "acme", false, "");
	}
	
	/*
	 * Quick edit content
	 */
	@Test
	public void test03_fastEditing() {
		// Change Edit mode
		changeEditMode();
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");

		// Edit title of content
		WcmAdmin.editTitleInline("Power Pack 1", "Power Pack 2");

		// Restore previous state of title
		WcmAdmin.editTitleInline("Power Pack 2", "Power Pack 1");
	}

	//function to public a document
	public static void publishDocument(){
		info("Publish this document");
		waitForElementPresent(ELEMENT_PUBLICATION);
		click(ELEMENT_PUBLICATION);
		WebElement current = waitForAndGetElement(EMENET_CURRENT_STATUS);
		if (current.getText().contains("Published") == false){
			click(ELEMENT_PUBLIC_STATUS);
		}
		waitForElementPresent(ELEMENT_CURRENT_PUBLIC_STATUS);
		save();
		info("Publish document is successful");
	}  

	public static void addNewContentTest(String DATA_FOLDER_NAME,
			By DATA_FOLDER_PATH,
			String DATA_ARTICLE_TITLE) {
		info("-- Add/Publish a new content --");
		goToSiteExplorer();
		goToNode(ELEMENT_ACME_SITE_LINK);
		createNewContentFolder(DATA_FOLDER_NAME, DATA_FOLDER_NAME);
		goToNode(DATA_FOLDER_PATH);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		info("Publish document");
		publishDocument();
	}

	public void editContentInEditMode(By DATA_CONTENT_TITLE, By EDIT_CONTENT_ICON) {
		info("Change page to edit content mode");
		changeEditMode();
		waitForElementPresent("//div[contains(@class,'InlineEditing')]");

		info("Edit content");
		mouseOver(DATA_CONTENT_TITLE, true);
		waitForElementPresent(EDIT_CONTENT_ICON);
		click(EDIT_CONTENT_ICON);
	}
}