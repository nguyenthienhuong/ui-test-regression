package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;


public class ECMS_DMS_SE_JSFile extends EcmsBase {
	public static String USER = "john";
	public static String PASS = "gtn";
	public static final By ELEMENT_DRIVE_SITE_MANAGE= By.xpath("//a[@title='Sites Management']");
	public static final String ELEMENT_PATH_ACME="acme/js";
	public static final String ELEMENT_PATH_SHARED="shared/js";
	public static final By ELEMENT_JS_NAME= By.id("name");
	public static final By ELEMENT_JS_ACTIVE= By.id("activeJS");
	public static final By ELEMENT_JS_PRIORITY= By.id("JSpriority");
	public static final By ELEMENT_JS_LANG= By.id("content-lang");
	public static final By ELEMENT_JS_DATA= By.id("contentHtml");
	public static final By ELEMENT_JS_FOLDER= By.xpath("//a[@title='js ']");
	public static String DATA_JS_NAME_01="FNC_ECMS_FEX_CREATE_18_01";
	public static String DATA_JS_ACTIVE_01="true";
	public static String DATA_JS_PRIORITY_01="1";
	public static String DATA_JS_LANG_01="fr";
	public static String DATA_JS_DATA_01="alert (\"Hello!\");";
	public static String DATA_JS_NAME_02="FNC_ECMS_FEX_CREATE_18_02_1";
	public static String DATA_JS_ACTIVE_02="true";
	public static String DATA_JS_PRIORITY_02="1";
	public static String DATA_JS_LANG_02="fr";
	public static String DATA_JS_DATA_02="alert (\"Hello 1!\");";
	public static String DATA_JS_NAME_02_2="FNC_ECMS_FEX_CREATE_18_02_2";
	public static String DATA_JS_ACTIVE_02_2="true";
	public static String DATA_JS_PRIORITY_02_2="2";
	public static String DATA_JS_LANG_02_2="en";
	public static String DATA_JS_DATA_02_2="alert (\"Hello 2!\");";
	public static String DATA_JS_NAME_03="FNC_ECMS_FEX_CREATE_18_03_1";
	public static String DATA_JS_ACTIVE_03="true";
	public static String DATA_JS_PRIORITY_03="1";
	public static String DATA_JS_LANG_03="fr";
	public static String DATA_JS_DATA_03="function b(){ alert(\"Hello!\");}";
	public static String DATA_JS_NAME_03_2="FNC_ECMS_FEX_CREATE_18_03_2";
	public static String DATA_JS_ACTIVE_03_2="true";
	public static String DATA_JS_PRIORITY_03_2="2";
	public static String DATA_JS_LANG_03_2="en";
	public static String DATA_JS_DATA_03_2="function b(){alert(\"Good bye!\");}";
	public static String DATA_JS_NAME_03_3="FNC_ECMS_FEX_CREATE_18_03_3";
	public static String DATA_JS_ACTIVE_03_3="true";
	public static String DATA_JS_PRIORITY_03_3="3";
	public static String DATA_JS_LANG_03_3="en";
	public static String DATA_JS_DATA_03_3="b();";
	public static String DATA_JS_NAME_04="FNC_ECMS_FEX_CREATE_18_04";
	public static String DATA_JS_ACTIVE_04="true";
	public static String DATA_JS_PRIORITY_04="3";
	public static String DATA_JS_LANG_04="en";
	public static String DATA_JS_DATA_04="alert(\"Hello!\");";
	//add js file
	public void addJSFile(String name, String... data)
	{
		goToAddNewContent();
		type(ELEMENT_JS_NAME, name, false);
		if (data.length >0)
		{
			type(ELEMENT_JS_DATA,data[0],false);
		}
		if (data.length > 1)
			selectOption(ELEMENT_JS_ACTIVE, data[1]);
		if (data.length >2 )
			type(ELEMENT_JS_PRIORITY,data[2], false);
		if (data.length > 3)
			selectOption(ELEMENT_JS_LANG, data[3]);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	//Create JS file with active
	@Test
	public void test01_CreateJSFileWithActive() {
		By bJs= By.xpath("//a[@title='"+ DATA_JS_NAME_01 + " ']");


		info("Create JS file with active");
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		goToNodeByPath(ELEMENT_PATH_ACME);
		addJSFile(DATA_JS_NAME_01, DATA_JS_DATA_01, DATA_JS_ACTIVE_01, DATA_JS_PRIORITY_01, DATA_JS_LANG_01);
		waitForElementPresent(bJs);

		//verify expected result
		clearCache();
		pause(1000);
		waitForConfirmation("Hello!");

		//delete js file
		deleteDocument(bJs);

	}
	//Check JS priority on Sites Explorer when there are two JS files which have been defined by calling function
	@Test
	public void test02_CheckJSPriority() {
		By bJs= By.xpath("//a[@title='"+ DATA_JS_NAME_02 + " ']");
		By bJs2= By.xpath("//a[@title='"+ DATA_JS_NAME_02_2 + " ']");

		info("Check JS priority on Sites Explorer when there are two JS files which have been defined by calling function");
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		goToNodeByPath(ELEMENT_PATH_ACME);
		addJSFile(DATA_JS_NAME_02, DATA_JS_DATA_02, DATA_JS_ACTIVE_02, DATA_JS_PRIORITY_02, DATA_JS_LANG_02);
		waitForElementPresent(bJs);
		goToNode(ELEMENT_JS_FOLDER);
		//add js file 2
		addJSFile(DATA_JS_NAME_02_2, DATA_JS_DATA_02_2, DATA_JS_ACTIVE_02_2, DATA_JS_PRIORITY_02_2, DATA_JS_LANG_02_2);
		waitForElementPresent(bJs2);

		//verify expected result
		clearCache();
		pause(1000);
		waitForConfirmation("Hello 2!");
		pause(1000);
		waitForConfirmation("Hello 1!");

		//delete js file
		deleteDocument(bJs);
		deleteDocument(bJs2);
	}	
	//Check JS priority on Sites Explorer when there are two JS files which have been defined by calling function
	@Test
	public void test03_CheckJSPriorityWithTwoOverloadingFunction() {
		By bJs= By.xpath("//a[@title='"+ DATA_JS_NAME_03 + " ']");
		By bJs2= By.xpath("//a[@title='"+ DATA_JS_NAME_03_2 + " ']");
		By bJs3= By.xpath("//a[@title='"+ DATA_JS_NAME_03_3 + " ']");

		info("Check JS priority on Sites Explorer when there are two JS files which have been defined by calling function");
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		goToNodeByPath(ELEMENT_PATH_ACME);
		addJSFile(DATA_JS_NAME_03, DATA_JS_DATA_03, DATA_JS_ACTIVE_03, DATA_JS_PRIORITY_03, DATA_JS_LANG_03);
		waitForElementPresent(bJs);
		goToNode(ELEMENT_JS_FOLDER);
		//add js file 2
		addJSFile(DATA_JS_NAME_03_2, DATA_JS_DATA_03_2, DATA_JS_ACTIVE_03_2, DATA_JS_PRIORITY_03_2, DATA_JS_LANG_03_2);
		waitForElementPresent(bJs2);
		goToNode(ELEMENT_JS_FOLDER);
		addJSFile(DATA_JS_NAME_03_3, DATA_JS_DATA_03_3, DATA_JS_ACTIVE_03_3, DATA_JS_PRIORITY_03_3, DATA_JS_LANG_03_3);
		waitForElementPresent(bJs3);
		//verify expected result
		clearCache();
		pause(1000);
		waitForConfirmation("Hello!");

		//delete js file
		deleteDocument(bJs);
		deleteDocument(bJs2);
		deleteDocument(bJs3);
	}	
	//Check the affection of JS file in Shared site
	@Test
	public void test04_CreateJSFileInSharedSite() {
		By bJs= By.xpath("//a[@title='"+ DATA_JS_NAME_04 + " ']");

		info("Check the affection of JS file in Share site");
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		goToNodeByPath(ELEMENT_PATH_SHARED);
		addJSFile(DATA_JS_NAME_04, DATA_JS_DATA_04, DATA_JS_ACTIVE_04, DATA_JS_PRIORITY_04, DATA_JS_LANG_04);
		waitForElementPresent(bJs);
		
		//verify expected result
		clearCache();
		pause(1000);
		waitForConfirmation("Hello!");
		pause(1000);
		//delete js file
		deleteDocument(bJs);
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
