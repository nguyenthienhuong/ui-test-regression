package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;

public class ECMS_DMS_SE_Category extends EcmsBase{
	public static String USER = "john";
	public static String PASS = "gtn";
	public static final By ELEMENT_DRIVE_DMS_ADMIN= By.xpath("//a[@title='DMS Administration']");
	public static final String ELEMENT_PATH_SYSTEM="exo:ecm/exo:taxonomyTrees/storage/System";
	public static final By ELEMENT_VIEW_SIMPLE=By.xpath("//a[@title='Simple View']");
	public static final By ELEMENT_LINK_SETUP=By.xpath("//img[@alt='Setup']");
	public static final By ELEMENT_LINK_CATE_TAG=By.xpath("//div[contains(text(),'Categories & Tags')]");
	public static final By ELEMENT_LINK_CATE=By.xpath("//div[contains(text(),'Manage Categories')]");
	public static final By ELEMENT_LINK_SYSTEM=By.linkText("System");
	public static final By ELEMENT_ICON_EDIT_CATE=By.xpath("//img[@alt='Edit Category Tree'][1]");
	public static final By ELEMENT_BUTTON_CLOSE=By.linkText("Close");
	public static final String DATA_CATE_NAME_04="FNC_ECMS_FEX_CREATE_13_04_^%$@@!#";
	public static final String DATA_CATE_NAME_08="FNC_ECMS_FEX_CREATE_13_04";
	public static final String DATA_CATE_NAME_08_TITLE="fncecmsfexcreate1304";


	// Add category that name contains special characters
	@Test
	public void test04_AddCategoryWithSpecialCharacter() {
		By bCate = By.xpath("//a[@title='"+ DATA_CATE_NAME_04+ " ']");
		info("Add category that the name contains special characters");
		//choose drive and go to exo:ecm/exo:taxonomyTrees/storage/System
		chooseDrive(ELEMENT_DRIVE_DMS_ADMIN);
		goToNodeByPath(ELEMENT_PATH_SYSTEM);
		pause(200);
		click(ELEMENT_VIEW_SIMPLE);
		pause(500);

		//add category
		addCategory(DATA_CATE_NAME_04);
		assert isElementPresent(bCate):"Fail to create a category!";
		pause(500);
		//delete data
		deleteDocument(bCate);
		waitForElementNotPresent(bCate);

	}
	//Add category, then check if it displays in ECM administration
	@Test
	public void test08_AddCategoryThenCheckDisplay() {
		By bCate=By.xpath("//a[@title='"+ DATA_CATE_NAME_08+ " ']");
		info("Add category, then check if to display in ECM administration!");
		//choose drive and go to exo:ecm/exo:taxonomyTrees/storage/System
		chooseDrive(ELEMENT_DRIVE_DMS_ADMIN);
		goToNodeByPath(ELEMENT_PATH_SYSTEM);
		pause(200);
		click(ELEMENT_VIEW_SIMPLE);
		pause(500);

		//add category
		addCategory(DATA_CATE_NAME_08);
		assert isElementPresent(bCate):"Fail to create a category!";
		pause(500);

		//check display 
		goToContentAdministration();
		click(ELEMENT_LINK_CATE_TAG);
		pause(500);
		click(ELEMENT_LINK_CATE);
		pause(500);
		click(ELEMENT_ICON_EDIT_CATE);
		pause(500);
		click(ELEMENT_LINK_SYSTEM);
		waitForElementPresent(By.linkText(DATA_CATE_NAME_08_TITLE));
		click(ELEMENT_BUTTON_CLOSE);

		//go to Site explorer and delete data
		goToSiteExplorer();
		deleteDocument(bCate);
		waitForElementNotPresent(bCate);
	}
	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);

		actions = new Actions(driver);
		loginEcms(USER, PASS);
		goToSiteExplorer();
	}

	@AfterMethod
	public void afterMethod() {
		logoutEcms();
		driver.quit();
	}
}