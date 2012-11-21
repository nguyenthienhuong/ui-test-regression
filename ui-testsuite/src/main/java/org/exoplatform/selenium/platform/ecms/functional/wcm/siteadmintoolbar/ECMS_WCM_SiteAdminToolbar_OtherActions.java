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
package org.exoplatform.selenium.platform.ecms.functional.wcm.siteadmintoolbar;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ecms.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by The eXo Platform SAS
 * Author : Hoang Manh Dung
 *          dunghm@exoplatform.com
 * Oct 22, 2012  
 */

public class ECMS_WCM_SiteAdminToolbar_OtherActions extends EcmsBase{
	public static final String DATA_USER_ADMIN = "john";
	public static final String DATA_PASS = "gtn";
	public static final String ELEMENT_INLINE_EDITING = "//div[contains(@class,'InlineEditing')]";
	public static final By ELEMENT_ACME_IMAGE = By.xpath("//*[contains(@id,'CurrentText_p')]/p/a/img"); 
	public static final By ELEMENT_EDIT_ICON_EDIT_MODE = By.xpath("//*[@class='EditContentIcon']");
	public static final By ELEMENT_RSS_ICON = By.xpath("//a[@class='RssIcon']");

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with "+ DATA_USER_ADMIN);
		loginEcms(DATA_USER_ADMIN, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod(){
		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}
	/*
	 * Change to edit mode
	 */
	@Test
	public void  test01_DisplayInEditMode(){
		ContentTemplate.changeEditMode();
		waitForElementPresent(ELEMENT_INLINE_EDITING);
		mouseOver(ELEMENT_ACME_IMAGE, true);
		waitForElementPresent(ELEMENT_EDIT_ICON_EDIT_MODE);
	}

	/*
	 * Change to published mode 
	 */
	@Test
	public void test02_DisplayInPublishedMode() {
		ContentTemplate.changeEditMode();
		waitForElementPresent(ELEMENT_INLINE_EDITING);
		ContentTemplate.changeEditMode();
		waitForElementNotPresent(ELEMENT_INLINE_EDITING);
		mouseOver(ELEMENT_RSS_ICON, true);
		waitForElementNotPresent(ELEMENT_EDIT_ICON_EDIT_MODE);
	}

}
