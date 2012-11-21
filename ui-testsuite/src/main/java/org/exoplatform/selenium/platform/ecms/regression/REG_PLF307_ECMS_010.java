package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToAddPageEditor;
import static org.exoplatform.selenium.platform.PageManagement.addNewPageEditor;
import static org.exoplatform.selenium.platform.ecms.PageEditor.goToPageEditor_EmptyLayout;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_010 extends EcmsBase{
	public static final String DATA_USER = "root";
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
	/*
	 * - Login acme site as root.
	 * - Add new page "level1"
	 * - Add new page "level2" children of "level1"
	 * - Add new page "level3" children of "level2"
	 * - Drag the "content explorer" portlet into the new page "level3"
	 * - Save.
	 */
	@Test
	public void test_displayContentPortlet(){
		String ELEMENT_PAGE_NAME_LEVEL_1="level1";
		String ELEMENT_PAGE_NAME_LEVEL_2="level2";
		String ELEMENT_PAGE_NAME_LEVEL_3="level3";
		String ELEMENT_LANGUAGE="English";
		String ELEMENT_CATEGORY_TITLE = "Content";
		Map<String, String> ELEMENT_PORTLET_ID = new HashMap<String, String>();
		ELEMENT_PORTLET_ID.put("Content/ContentListViewerPortlet","");
		Map<String, String> ELEMENT_PORTLET_ID_LEVEL_3 = new HashMap<String, String>();
		ELEMENT_PORTLET_ID_LEVEL_3.put("Content/FileExplorerPortlet","");
		boolean EXTENDED_LABEL_MODE = true;
		goToOverView();
		//Go to Add Page Editor with Editor
		goToPageEditor_EmptyLayout(ELEMENT_PAGE_NAME_LEVEL_1);
		click(ELEMENT_PAGE_EDIT_FINISH);
		//Add new page "level2"
		goToPageEditor_EmptyLayout(ELEMENT_PAGE_NAME_LEVEL_2);
		click(ELEMENT_PAGE_EDIT_FINISH);
		//Add new page "level3"
		goToAddPageEditor();
		addNewPageEditor(ELEMENT_PAGE_NAME_LEVEL_3, ELEMENT_PAGE_NAME_LEVEL_3, ELEMENT_LANGUAGE, ELEMENT_CATEGORY_TITLE,ELEMENT_PORTLET_ID_LEVEL_3, EXTENDED_LABEL_MODE);
		//capture screen
		captureScreen("REG_PLF307_ECMS_010");
		
	}
}
