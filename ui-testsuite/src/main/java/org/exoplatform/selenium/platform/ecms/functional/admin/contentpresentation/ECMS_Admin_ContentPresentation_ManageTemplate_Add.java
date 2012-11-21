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
package org.exoplatform.selenium.platform.ecms.functional.admin.contentpresentation;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.WcmAdmin;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by The eXo Platform SAS
 * Author : Hoang Manh Dung
 *          dunghm@exoplatform.com
 * Oct 9, 2012  
 */
public class ECMS_Admin_ContentPresentation_ManageTemplate_Add extends EcmsBase {
  public static final String DATA_USER_ADMIN = "john";
  public static final String DATA_PASS = "gtn";
    
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
  
  /**
   * Add new template when input valid data into all required fields
   */
  @Test 
  public void test01_AddNewTempate(){
    //Open Manage Template screen
    WcmAdmin.openManageTemplateForm();
    //Open Add New Template Form
    WcmAdmin.openAddNewTemplateForm();
    //Fill All form data
    WcmAdmin.fillAddNewTemplateForm("Test Template","app:application","Platform/Administration/Content Management","*");
    info("Reset Data");
    WcmAdmin.deleleTemplate("app:application","Do you really want to delete?");
  }  
}