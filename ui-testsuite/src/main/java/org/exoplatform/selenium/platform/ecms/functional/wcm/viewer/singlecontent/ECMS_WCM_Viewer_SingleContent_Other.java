package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.singlecontent;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import static org.exoplatform.selenium.TestLogger.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.platform.ecms.PageEditor.*; 
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;

/*
 * @author: Thuntn
 * @date: 22/10/2012
 */
public class ECMS_WCM_Viewer_SingleContent_Other extends EcmsBase {
	public static String USER = "john";
	public static String PASS = "gtn";
	
  @Test
  public void test01_EditContentOfSCVPage() {
	  String namePage="ECMS_WCM_Viewer_Other3";
	  String file="TestData/ECMS_WCM_Viewer_SingleContent_Other.png";
	  String newName="new ECMS_WCM_Other";
	  String content="conditions.doc";
	  String ELEMENT_EDIT_INCONTEXT="//div[contains(text(),'"+content+"')]/../../div/div/div/div/div/div[2]/a";
	  
	  String pathContent = "General Drives/Sites Management/acme/documents/conditions.doc";
	  
	  info("Edit content of SCV page");
	  //create a page with empty layout
	  goToPageEditor_EmptyLayout(namePage);
	  addContentDetailEmptyLayout();
	  click(ELEMENT_NEWPAGE_SAVE_BUTTON);
	  
	  //edit a page
	  goToEditPageEditor();
	  selectContentPath(pathContent);
	  click(ELEMENT_PAGE_EDIT_FINISH);
	 
	  //edit content of SCV inline
	  changeEditMode();
	  mouseOver(ELEMENT_CONTAINER_CONTENT,true);
	  click(ELEMENT_EDIT_INCONTEXT);
	  editUploadedFile(namePage, file, newName,newName,newName,newName);
	  click(ELEMENT_BUTTON_BACK);
	 
	  //verify result
	  waitForElementPresent(By.xpath("//div[text()='"+content+"']"));
	  captureScreen("result.png");
	  
	  //delete node, page
	  goToPortalSites();
	  deleteNode("acme", "Overview", namePage, true);
	  goToManagePages();
	  deletePage(PageType.PORTAL, namePage);
  }
  
  @Test
  public void test02_OpenFormOfSettingPage() {
	  String namePage="ECMS_WCM_Viewer_Other2";
	  String pathContent="General Drives/Sites Management/acme/documents/conditions.doc";	  
	  String content="conditions.doc";
	  String ELEMENT_PREFERENCE_INCONTEXT="//div[contains(text(),'"+content+"')]/../../div/div/div/div/div/div[1]/a";
	  
	  info("Open the form of setting page");
	  //create a page
	  goToPageEditor_EmptyLayout(namePage);
	  addContentDetailEmptyLayout();
	  click(ELEMENT_NEWPAGE_SAVE_BUTTON);
	  
	  //edit a page
	  goToEditPageEditor();
	  selectContentPath(pathContent);
	  click(ELEMENT_PAGE_EDIT_FINISH);
	  
	  //open form of setting page
	  changeEditMode();
	  mouseOver(ELEMENT_CONTAINER_CONTENT,true);
	  click(ELEMENT_PREFERENCE_INCONTEXT);
	  waitForElementPresent(ELEMENT_PREFERENCE_TITLE);
	 
	  //delete node, page
	  goToPortalSites();
	  deleteNode("acme", "Overview", namePage, true);
	  goToManagePages();
	  deletePage(PageType.PORTAL, namePage);
  }
  @BeforeMethod
  public void beforeMethod() {
	  initSeleniumTest();
	  driver.get(baseUrl);
	  actions = new Actions(driver);
	  loginEcms(USER, PASS);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }
}