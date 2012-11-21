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
 *@date: 26/09/2012
 */
public class EXOGTN_Group_Organization_GroupManagement_EditRole extends PlatformBase {
	public String ELEMENT_GROUP_NAME = "organization" ;
	public String ELEMENT_GROUP_LABEL = "Organization" ;
	public String ELEMENT_USER_NAME = "mary";
	public String ELEMENT_MEMBER_SHIP= "manager"; 
	By ELEMENT_EDIT_MEMBER= By.xpath("//img[@alt='Edit']");
	By ELEMENT_SELECT_MEMBERSHIP = By.xpath("//select[@name='membership']");
	boolean SELECT = true; 
	By VERIFY_BEFORE_EDIT_ROLE = By.xpath("//td/div[@title='mary']/following::td/div[@title='manager']");
	By VERIFY_AFTER_EDIT_ROLE = By.xpath("//td/div[@title='mary']/following::td/div[@title='member']");

	@BeforeMethod()
	public void beforeTest() throws Exception {
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@Test
	public void test01_EditMembershipForUserFromExistingGroup () {
		info("--login portal--");
		signIn("john", "gtn");

		info("--Go to User and group--");
		//Go to user and group management page
		goToUsersAndGroupsManagement();

		//Choose group tab
		info("--Choose group tab--");
		chooseGroupTab();

		//Add user into group
		info("--Add user into group--");
		selectGroup("Organization");
		addUsersToGroup(ELEMENT_USER_NAME, ELEMENT_MEMBER_SHIP, SELECT, true);
		waitForElementPresent(VERIFY_BEFORE_EDIT_ROLE);

		// Edit user into group
		click(ELEMENT_EDIT_MEMBER);

		// change membership
		select(ELEMENT_SELECT_MEMBERSHIP, "member");
		save();
		waitForElementPresent(VERIFY_AFTER_EDIT_ROLE);

		// Delete user in group
		deleteUserInGroup(ELEMENT_GROUP_NAME,ELEMENT_GROUP_LABEL,ELEMENT_USER_NAME);
	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}