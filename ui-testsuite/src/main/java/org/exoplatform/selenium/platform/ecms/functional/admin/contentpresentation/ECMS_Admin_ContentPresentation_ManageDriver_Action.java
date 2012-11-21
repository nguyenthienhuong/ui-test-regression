package org.exoplatform.selenium.platform.ecms.functional.admin.contentpresentation;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/*
 * @author: Lientm
 * @date: 9/10/2012
 */
public class ECMS_Admin_ContentPresentation_ManageDriver_Action extends EcmsBase{

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
	  
	  
	  /*case01+02+03: Add drive
	   * go to add drive
	   * add new drive
	   * edit drive
	   * delete drive
	   */
	  @Test
	  public void test01_02_03_AddEditDeleteDrive(){
		  String DATA_DRIVE_NAME = "ECMS_Admin_ContentPresentation_ManageDriver_Action_01";
		  By ELEMENT_DRIVE_EDIT = By.xpath("//div[@title='" + DATA_DRIVE_NAME + "']/../../td/div/img[@title='Edit']");
		  By ELEMENT_DRIVE_DELETE = By.xpath("//div[@title='" + DATA_DRIVE_NAME + "']/../../td/div/img[@title='Delete']");
		  By ELEMENT_DRIVE = By.xpath("//div[@title='" + DATA_DRIVE_NAME + "']");
		  
		  //go to add drive
		  goToContentAdministration();
		  info("Go to add new drive");
		  goToManageDriver();
		  addNewDriver(DATA_DRIVE_NAME,"dms-system","exo:ecm", "Organization/Management/Executive Board","member","member:/organization/management/executive-board","e_admin-view/c_icon-view/b_list-view" );
		  
		  //edit drive
		  info("Edit drive");
		  click(ELEMENT_DRIVE_EDIT);
		  waitForElementPresent(ELEMENT_DRIVE_EDIT_POPUP);
		  //--select workspace
		  select(ELEMENT_WORKSPACE, "system");
		  click(ELEMENT_ADD_PATH);
		  waitForElementPresent(ELEMENT_ADD_PATH_POPUP);
		  click(ELEMENT_JCR_SYSTEM);
		  //--select permission
		  click(ELEMENT_ADD_PERMISSION);	
		  click(By.linkText("manager"));
		  assert getValue(ELEMENT_PERMISSION_TEXTBOX).contains("manager:/organization/management/executive-board"):"Set permission is not true";
		  select(ELEMENT_ALLOW_CREATE_FOLDER, "Folder");
		  //--add apply view
		  click(ELEMENT_APPLY_VIEW_TAB);
		  selectCheckboxList("authoring-view");
		  click(ELEMENT_SAVE_BUTTON);
		  
		  //delete drive
		  info("Delete drive");
		  click(ELEMENT_DRIVE_DELETE);
		  acceptAlert();
		  waitForElementNotPresent(ELEMENT_DRIVE);
		  info("Delete driver successfully");
	  }
}
