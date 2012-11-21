package org.exoplatform.selenium.platform.ecms.functional.admin.contentpresentation;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/*
 * @author: Lientm
 * @date: 16/10/2012
 */
public class ECMS_Admin_ContentPresentation_ManageDriver_Display extends EcmsBase{
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
	  
	  //function to delete drive on manage drive
	  public void deleteDriver(String driver){
		  By ELEMENT_DRIVER = By.xpath("//*[@title='" + driver + "']");
		  By ELEMENT_DRIVER_DELETE = By.xpath("//div[@title='" + driver + "']/../../td/div/img[@title='Delete']");
				  
		  goToContentAdministration();
		  goToManageDriver();
		  info("Delete driver");
		  click(ELEMENT_DRIVER_DELETE);
		  acceptAlert();
		  waitForElementNotPresent(ELEMENT_DRIVER);
		  info("Delete driver successfully");
	  }
	  
	  /*case01: Check the displaying of drive after being created by user has access right
	   * add new drive
	   * check displaying of drive in site explorer when user has access permission
	   */
	  @Test
	  public void test01_CheckDisplayOfDriverByUserHasAccessPermission(){
		  String DATA_DRIVE_NAME = "ECMS_Admin_ContentPresentation_ManageDriver_Display_01";
		  By ELEMENT_DRIVE = By.xpath("//*[@title='" + DATA_DRIVE_NAME + "']");
  
		  //add drive
		  goToContentAdministration();
		  goToManageDriver();
		  addNewDriver(DATA_DRIVE_NAME,"collaboration","sites content", "Platform/Administration","*","*:/platform/administrators","e_admin-view/c_icon-view/b_list-view" );
		  
		  //check displaying of drive in site explorer when user has access permission
		  goToSiteExplorer();
		  click(ELEMENT_SHOW_DRIVES);
		  waitForElementPresent(ELEMENT_DRIVE);
		  info("Drive display true");
		  
		  //delete data
		  deleteDriver(DATA_DRIVE_NAME);
	  }
	  
	  /*case02: Check the displaying of drive after being created by user does not have access right with this drive
	   * add new drive
	   * check displaying of driver in site explorer when user does not have access permission
	   */
	  @Test
	  public void test02_CheckDisplayOfDriverOfUserNotHaveAccessPermission(){
		  String DATA_DRIVER_NAME = "ECMS_Admin_ContentPresentation_ManageDriver_Display_02";
		  By ELEMENT_DRIVER = By.xpath("//*[@title='" + DATA_DRIVER_NAME + "']");
	  	  
		  //add drive
		  goToContentAdministration();
		  goToManageDriver();
		  addNewDriver(DATA_DRIVER_NAME,"collaboration","sites content", "Platform/Administration","*","*:/platform/administrators","e_admin-view/c_icon-view/b_list-view" );
		  logoutEcms();
		  
		  //login with user mary
		  info("Login as mary who does not have access permission on drive");
		  loginEcms("mary", "gtn");
		  goToSiteExplorer();
		  click(ELEMENT_SHOW_DRIVES);
		  waitForElementNotPresent(ELEMENT_DRIVER);
		  info("User does not see driver");
		  logoutEcms();
		  
		  //delete data
		  loginEcms(DATA_USER, DATA_PASS);
		  deleteDriver(DATA_DRIVER_NAME);
	  }
	  
	  /*case03: Check the displaying of drive after deleting it 
	   * add new drive
	   * delete drive
	   * check displaying of drive in site explorer
	   */
	  @Test
	  public void test03_CheckDisplayOfDriverAfterDelete(){
		  String DATA_DRIVER_NAME = "ECMS_Admin_ContentPresentation_ManageDriver_Display_03";
		  By ELEMENT_DRIVER = By.xpath("//*[@title='" + DATA_DRIVER_NAME + "']");
  
		  //add driver
		  goToContentAdministration();
		  goToManageDriver();
		  addNewDriver(DATA_DRIVER_NAME,"collaboration","sites content", "Platform/Administration","*","*:/platform/administrators","e_admin-view/c_icon-view/b_list-view" );
		  
		  //check displaying of driver in site explorer when user has access permission
		  goToSiteExplorer();
		  click(ELEMENT_SHOW_DRIVES);
		  waitForElementPresent(ELEMENT_DRIVER);
		  info("Drive display true");
		  
		  //delete data
		  deleteDriver(DATA_DRIVER_NAME);
		  
		  //check displaying of drive in site explorer
		  goToSiteExplorer();
		  waitForElementNotPresent(ELEMENT_DRIVER);
		  info("Can not see driver after being deleted");		  
	  }
}
