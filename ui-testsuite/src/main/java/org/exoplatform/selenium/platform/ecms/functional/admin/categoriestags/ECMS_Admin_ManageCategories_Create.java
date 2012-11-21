package org.exoplatform.selenium.platform.ecms.functional.admin.categoriestags;

import static org.exoplatform.selenium.TestLogger.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;

/*
 * @author: Lientm
 * @date: 9/10/2012
 */

public class ECMS_Admin_ManageCategories_Create extends EcmsBase {

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

	/*case01+11+12: Add new Category Tree when input valid data in all fields
	 * create new category tree with 4 steps
	 * edit category
	 * delete category
	 */
	@Test
	public void test01_11_12_AddEditDeleteCategoryTree(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Create_tree_01";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Create_action_01";
		String DATA_CATEGORY_TREE_CHILD_01 = "ECMS_Admin_ManageCategories_Create_tree_child_01";
		String DATA_CATEGORY_TREE_CHILD_02 = "ECMS_Admin_ManageCategories_Create_tree_child_02";

		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};
		
		//go to add new category tree
		goToContentAdministration();

		//add new category tree
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);
		
		//edit category tree at step 4
		info("Edit category tree");
		click(By.xpath("//div[@title='" + DATA_CATEGORY_TREE_NAME + "']/../../td/div/img[@class='Edit16x16Icon']"));
		addNewCategoryTree_Step4(DATA_CATEGORY_TREE_NAME, DATA_CATEGORY_TREE_CHILD_01, DATA_CATEGORY_TREE_CHILD_02,"demo",true,false,false,false);

		//delete data
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);
		info("Delete category is successful");
	}
}
