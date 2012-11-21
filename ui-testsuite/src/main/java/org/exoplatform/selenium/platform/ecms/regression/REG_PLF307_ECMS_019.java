package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToAddPageEditor;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToEditPageEditor;
import static org.exoplatform.selenium.platform.PageManagement.addNewPageEditor;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_019 extends EcmsBase{
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_FRAME_CONTAIN_PORTLET = By.xpath("//div[contains(@id,'UIPortlet')]");
	public static final By ELEMENT_EDIT_PORTLET_LINK = By.xpath("//a[@title='Edit Portlet']");
	public static final By ELEMENT_SELECT_TYPE = By.xpath("//select[@id='usecase']");
	public static final By ELEMENT_BUTTON_SAVE=By.linkText("Save");
	public static final By ELEMENT_BUTTON_OK=By.linkText("OK");
	public static final By ELEMENT_BUTTON_CLOSE=By.linkText("Close");
	public static By ELEMENT_SELECT_DRIVE	 = By.xpath("//img[@title='Select Drive']");
	public static By ELEMENT_SELECT_PATH	 = By.xpath("//img[@title='SelectNodePath']");
	public static final By ELEMENT_TARGET_DRIVE_PATH = By.xpath("//*[@id='UIDriveSelector']//*[text()='collaboration']/../td/div/img[@class='Select16x16Icon']");
	public static final By ELEMENT_SELECT_TARGET_PATH = By.xpath("//img[@title='Add Root Node']");
	
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
	/*
	 * - Create a new page containing a site explorer portlet
	 * - In the site explorer options
	 * + choose Select Type as "Parameterize"
	 * + Choose a drive.
	 * + Select a path
	 * - Save then close
	 * - Save this page
	 * - Open this page
	 */
	@Test
	public void test_contentExplorerwithparameterize(){
		String ELEMENT_PAGE_NAME="REG_PLF307_ECMS_019_5";
		Map<String, String> ELEMENT_PORTLET_ID_LEVEL_3 = new HashMap<String, String>();
		ELEMENT_PORTLET_ID_LEVEL_3.put("Content/FileExplorerPortlet","");
		boolean EXTENDED_LABEL_MODE = true;
		String ELEMENT_LANGUAGE="English";
		String ELEMENT_CATEGORY_TITLE = "Content";
		goToOverView();
		goToAddPageEditor();
		addNewPageEditor(ELEMENT_PAGE_NAME, ELEMENT_PAGE_NAME, ELEMENT_LANGUAGE, ELEMENT_CATEGORY_TITLE,ELEMENT_PORTLET_ID_LEVEL_3, EXTENDED_LABEL_MODE);
		goToEditPageEditor();
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		click(ELEMENT_EDIT_PORTLET_LINK);
		select(ELEMENT_SELECT_TYPE, "Parameterize");
		click(ELEMENT_SELECT_DRIVE);
		click(ELEMENT_TARGET_DRIVE_PATH);
		click(ELEMENT_SELECT_PATH);
		click(ELEMENT_SELECT_TARGET_PATH);
		click(ELEMENT_BUTTON_SAVE);
		click(ELEMENT_BUTTON_OK);
		click(ELEMENT_BUTTON_CLOSE);
		pause(500);
		click(ELEMENT_PAGE_FINISH_BUTTON);
		captureScreen("REG_PLF307_ECMS_019");
		
		
	}

}
