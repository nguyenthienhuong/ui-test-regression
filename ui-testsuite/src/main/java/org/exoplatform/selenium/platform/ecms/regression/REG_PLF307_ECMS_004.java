package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToAddPageEditor;
import static org.exoplatform.selenium.platform.PageManagement.addNewPageEditor;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewFreeLayoutWebContent;
import static org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.listcontent.WCM_Viewers_ListContent_PublishIcon.publicDocument;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.changeEditMode;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_004 extends EcmsBase{
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
	/*
	 * Case:
	 * - Login 
	 * - Go to Content Explorer
	 * - Create 2 free layout web contents with illustration which the second one is inside the first one
	 * - Publish them
	 * - Create new page
	 * - Add Content List Portlet. 
	 * - Choose directory containing these new web content
	 * Expected result: Illustration of this document is displayed well, even users refresh many times
	 */
	@Test
	public void test_ImagesinACMESite(){
		String ELEMENT_FREE_WEB_CONTENT_TITLE = "REG_PLF307_ECMS_004";
		String ELEMENT_FREE_WEB_CONTENT_CONTENTS = "REG_PLF307_ECMS_004";
		String ELEMENT_UPLOAD_FILE_NAME = "ECMS_DMS_SE_Upload_imgfile.jpg";
		String ELEMENT_FREE_WEB_CONTENT_IMAGE= "TestData/"+ELEMENT_UPLOAD_FILE_NAME;
		String ELEMENT_FREE_WEB_CONTENT_JS= "";
		String ELEMENT_NAME="test_ImagesinACMESite";
		String ELEMENT_LANGUAGE="English";
		String ELEMENT_CATEGORY_TITLE = "Content";
		Map<String, String> ELEMENT_PORTLET_ID = new HashMap<String, String>();
		ELEMENT_PORTLET_ID.put("Content/ContentListViewerPortlet","");
		boolean EXTENDED_LABEL_MODE = true;
		
		//Go to Site Explorer
		goToSiteExplorer();
		//Add new Content
		goToAddNewContent();
		//Add 2 free web contents
		//Create the first free web contents
		createNewFreeLayoutWebContent(ELEMENT_FREE_WEB_CONTENT_TITLE, ELEMENT_FREE_WEB_CONTENT_TITLE, ELEMENT_FREE_WEB_CONTENT_CONTENTS,ELEMENT_FREE_WEB_CONTENT_IMAGE, ELEMENT_FREE_WEB_CONTENT_CONTENTS, ELEMENT_FREE_WEB_CONTENT_JS, ELEMENT_FREE_WEB_CONTENT_JS);
		//Create the second free web contents inside the first
		//Publication these created free web contents
		publicDocument();
		//Go to Add Page Editor with Editor
		goToAddPageEditor();
		//Add a new page
		addNewPageEditor(ELEMENT_NAME, ELEMENT_NAME, ELEMENT_LANGUAGE, ELEMENT_CATEGORY_TITLE,ELEMENT_PORTLET_ID, EXTENDED_LABEL_MODE);
		//Choose category content point to the new created web contents
		changeEditMode();
	}
}
