package org.exoplatform.selenium.platform.exogtn.functional.group.organization.groupmanagement;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;

/**
 *@author HangNTT
 *@date: 19/09/2012
 */
public class EXOGTN_Group_Organization_GroupManagement_Add extends PlatformBase {
	/**
	 * @param args
	 */
	String GROUP_NAME = "fnc_gtn_plt_org_03_02_001"; 
	String GROUP_LABEL= "fnc_gtn_plt_org_03_02_001"; 
	String GROUP_DESC = "fnc_gtn_plt_org_03_02_001"; 
	boolean verify = true	 ;

	String GROUP_NAME1 = "fnc_gtn_plt_org_03_02_005"; 
	String GROUP_LABEL1= "fnc_gtn_plt_org_03_02_005"; 
	String GROUP_DESC1 = "group example"; 

	String GROUP_NAME2 = "FNC_GTN_PLT_ORG_03_02_005"; 
	String GROUP_LABEL2= "FNC_GTN_PLT_ORG_03_02_005"; 
	String GROUP_DESC2 = "group example"; 

	By UP_LEVEL = By.xpath("//a[@title='Up Level']");

	@BeforeMethod()
	public void beforeTest() throws Exception {
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	// Add new group with valid value
	@Test   
	public void test01_AddGroup () {
		info("--login portal--");
		signIn("john", "gtn");

		//Go to user and group management page
		info("--Go to User and group--");
		goToUsersAndGroupsManagement();

		//Select group tab
		info("--Choose group tab--");
		chooseGroupTab();
		info("--Add new group--");

		//Add new group
		addGroup(GROUP_NAME, GROUP_LABEL, GROUP_DESC, true);

		info("--Select group--");
		//Select new group
		selectGroup(GROUP_NAME);

		//Delete new group
		info("Delete group");
		deleteGroup(GROUP_NAME, true);
	}

	// Add new group with upper and lower case
	@Test 
	public void test05_AddGroupWithUpperAndLowerCase () {
		By elementUsername = By.xpath("//input[@id='username']");

		info("--login portal--");
		signIn("john", "gtn");

		//Go to user and group management page
		info("--Go to User and group--");
		goToUsersAndGroupsManagement();

		// Choose group tab
		info("--Choose group tab--");
		chooseGroupTab();

		//Add new group with lower case
		info("--Add new group with lower case--");
		addGroup(GROUP_NAME1, GROUP_LABEL1, GROUP_DESC1, verify);
		click(UP_LEVEL);

		//Add new group with upper case
		info("--Add new group with upper case--");
		addGroup(GROUP_NAME2, GROUP_LABEL2, GROUP_DESC2, verify);
		click(UP_LEVEL);

		//Delete group is lower case
		info("--Delete group with lower case--");
		selectGroup(GROUP_NAME1);
		waitForElementPresent(elementUsername);

		//Delete group is upper case
		info("Delete group");
		deleteGroup(GROUP_NAME1, true);
		waitForTextNotPresent(GROUP_NAME1);

		info("--Delete group with upper case--");
		selectGroup(GROUP_NAME2);
		deleteGroup(GROUP_NAME2, true);
		waitForTextNotPresent(GROUP_NAME2);
	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}