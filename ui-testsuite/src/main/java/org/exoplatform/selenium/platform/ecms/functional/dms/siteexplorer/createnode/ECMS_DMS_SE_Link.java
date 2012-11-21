package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import static org.exoplatform.selenium.TestLogger.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

/*
 * @author: Thuntn
 * @date: 18/09/2012
 */

public class ECMS_DMS_SE_Link extends EcmsBase {
	public static String USER = "john";
	public static String PASS = "gtn";
	public static final By ELEMENT_DRIVE_SITE_MANAGE= By.xpath("//a[@title='Sites Management']");
	public static final String ELEMENT_PATH_ACME="acme/links";
	public static final By ELEMENT_LINK_NAME= By.id("name");
	public static final By ELEMENT_LINK_URL= By.id("LinkURL");
	public static final By ELEMENT_LINK_LANG= By.id("content-lang");
	public static final By ELEMENT_LINK_DESC= By.id("LinkDescription");
	public static final String DATA_LINK_NAME_01="FNC_ECMS_FEX_CREATE_17_01_LINK";
	public static final String DATA_LINK_URL_01="http://www.exoplatform.com";
	public static final String DATA_LINK_LANG_01="fr";
	public static final String DATA_LINK_DESC_01="FNC_ECMS_FEX_CREATE_17";
	public static final String DATA_LINK_NAME_02="FNC_ECMS_FEX_CREATE_17_02_LINK";
	public static final String DATA_LINK_URL_02="FNC_ECMS_FEX_CREATE_17_02_LINK";
	public static final String DATA_LINK_LANG_02="en";
	public static final String DATA_LINK_DESC_02="FNC_ECMS_FEX_CREATE_17_02";
	
	//add web link 
	public void addLink(String name, String url, String... lang)
	{
		goToAddNewContent();
		type(ELEMENT_LINK_NAME,name,false);
		type(ELEMENT_LINK_URL, url, false);
		if (lang.length != 0)
		{
		 selectOption(ELEMENT_LINK_LANG, lang[0]);
		}
		if (lang.length > 1)
		{
			type(ELEMENT_LINK_DESC,lang[1],false);
		}
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	
	//Create a link in site explorer
	@Test
	public void test01_CreateLinkInSiteExplorer() {
		By bLink = By.xpath("//a[@title='" + DATA_LINK_NAME_01 + " ']");
		
		info("Create a link in site explorer");

		//choose Sites management drive, and go to acme/links
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		goToNodeByPath(ELEMENT_PATH_ACME);
		addLink(DATA_LINK_NAME_01, DATA_LINK_URL_01, DATA_LINK_LANG_01, DATA_LINK_DESC_01);
		waitForElementPresent(bLink);
			
		//delete link
		deleteDocument(bLink);
		waitForElementNotPresent(bLink);
	} 
	//Create a link in site explorer with invalid URL
		@Test
		public void test02_CreateLinkInSiteExplorerWithInvalidURL() {
			String fileName="TestData/Output/result_FNC_ECMS_FEX_CREATE_17_02";
			By bLink = By.xpath("//a[@title='" + DATA_LINK_NAME_02 + " ']");
			
			info("Create a link in site explorer with invalid url");

			//choose Sites management drive, and go to acme/links
			chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
			goToNodeByPath(ELEMENT_PATH_ACME);
			addLink(DATA_LINK_NAME_02, DATA_LINK_URL_02, DATA_LINK_LANG_02, DATA_LINK_DESC_02);
			waitForElementPresent(bLink);
			
			//verify expected result
			click(By.linkText(DATA_LINK_URL_02));
			waitForElementPresent(bLink);
			captureScreen(fileName);
			pause(2000);
			//delete link
			deleteDocument(bLink);
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
