package org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.contentbyquery;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.platform.ecms.PageEditor.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

/**
 * @author NhungVT
 * Date: 31/10/2012
 */

public class ECMS_WCM_Viewer_ContentsQuery extends EcmsBase
{
	public String DATA_USER_JOHN = "john";
	public String DATA_USER_MARY = "mary";
	public String DATA_PASS = "gtn";
	public String DATA_PAGE_NAME_01 = "ECMS_WCM_Viewer_ContentsQuery_001";
	public String DATA_PAGE_NAME_02 = "ECMS_WCM_Viewer_ContentsQuery_002";
	public String DATA_PAGE_NAME_03 = "ECMS_WCM_Viewer_ContentsQuery_003";
	public String DATA_PAGE_NAME_04 = "ECMS_WCM_Viewer_ContentsQuery_004";
	public String DATA_PAGE_NAME_05 = "ECMS_WCM_Viewer_ContentsQuery_005";
	public String DATA_PAGE_NAME_06 = "ECMS_WCM_Viewer_ContentsQuery_006";
	public String DATA_PAGE_NAME_07 = "ECMS_WCM_Viewer_ContentsQuery_007";
	public String DATA_BY_QUERY = "Select * from nt:file";
	public String DATA_BY_QUERY_INVALID = "Test";
	public String CONTENT_PATH = "General Drives/acme-category";
	public String FOLDER_PATH = "General Drives";
	public String NO_ARTICLE_MESSAGE = "Sorry, no articles are available.";

	
	@BeforeMethod
	public void beforeMethods() throws Exception 
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
	}
	
	//Check if a query and a workspace condition work
	@Test()
	public void test01_CheckQueryAndWorkspaceWithFolder()
	{
		//Login acme
		loginEcms(DATA_USER_JOHN, DATA_PASS);
		
		//Create a page with portlet Content By Query
		createPage_ContentByQuery_EmptyLayout(DATA_PAGE_NAME_01);
		
		//Open Portlet Setting
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		waitForElementPresent(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_EDIT_PORTLET_LINK);
		
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		
		//Input data for Query By and Folder Path
		selectContentPathInEditMode(FOLDER_PATH, true);
		click(ELEMENT_ACME_CATEGORY);
		waitForElementPresent(ELEMENT_BY_QUERY_TEXTAREA);
		type(ELEMENT_BY_QUERY_TEXTAREA, DATA_BY_QUERY, true);
		
		//Save > Close > Finish
		save();
		close();
		waitForElementPresent(ELEMENT_PAGE_EDIT_FINISH);
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH, 50000);
		
		//Verify documents having node type nt:file in collaboration are displayed
		captureScreen("ECMS_WCM_Viewer_ContentsQuery_test01_check_query_ntfile.jpg");
			
		//Delete data
		deletePageAtManagePageAndPortalNavigation(DATA_PAGE_NAME_01, true, "acme", false, null);
	}
	
	//Check if a Folder option works when a query is missing
	@Test()
	public void test02_CheckWorkWithMissingQuery()
	{
		//Login acme
		loginEcms(DATA_USER_JOHN, DATA_PASS);
		
		//Create a page with portlet Content By Query
		createPage_ContentByQuery_EmptyLayout(DATA_PAGE_NAME_02);
		
		//Open Portlet Setting
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);
		waitForElementPresent(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_EDIT_PORTLET_LINK);
		
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		
		//Input no data for Query By and select Folder Path
		selectContentPathInEditMode(FOLDER_PATH, true);
		click(ELEMENT_ACME_CATEGORY);
		
		//Save > Close > Finish
		save();
		close();
		waitForElementPresent(ELEMENT_PAGE_EDIT_FINISH);
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH, 50000);
		
		//Verify documents in selected folder are displayed
		waitForTextPresent("Flight");
		captureScreen("ECMS_WCM_Viewer_ContentsQuery_test02.jpg");
		
		//Delete data
		deletePageAtManagePageAndPortalNavigation(DATA_PAGE_NAME_02, true, "acme", false, null);
	}
	
	//Check if a Content option works when a query is missing
	@Test()
	public void test03_CheckQueryAndWorkspaceWithContent()
	{
		//Login acme
		loginEcms(DATA_USER_JOHN, DATA_PASS);
		
		//Create a page with portlet Content By Query
		createPage_ContentByQuery_EmptyLayout(DATA_PAGE_NAME_03);
		
		//Open Portlet Setting
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);
		waitForElementPresent(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_EDIT_PORTLET_LINK);
		
		click(ELEMENT_SELECT_BY_CONTENT_PATH);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		
		// Select Content Path
		selectContentPathInEditMode(CONTENT_PATH, true);
		click(ELEMENT_FLIGHT);
		save();
		
		//Input data for Query By
		waitForElementPresent(ELEMENT_BY_QUERY_TEXTAREA);
		type(ELEMENT_BY_QUERY_TEXTAREA, DATA_BY_QUERY_INVALID, true);
		
		//Save > Close > Finish
		save();
		close();
		waitForElementPresent(ELEMENT_PAGE_EDIT_FINISH);
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH, 50000);
		
		//Verify documents in the folder you selected are displayed
		waitForTextPresent("Flight");
		captureScreen("ECMS_WCM_Viewer_ContentsQuery_test03.jpg");
		
		//Delete data
		deletePageAtManagePageAndPortalNavigation(DATA_PAGE_NAME_03, true, "acme", false, null);
	}
	
	//Check if a Folder/Content option works when a query is invalid
	@Test()
	public void test04_CheckWorkWithInvalidQuery()
	{
		//Login acme
		loginEcms(DATA_USER_JOHN, DATA_PASS);
		
		//Create a page with portlet Content By Query
		createPage_ContentByQuery_EmptyLayout(DATA_PAGE_NAME_04);
		
		//Open Portlet Setting
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);
		waitForElementPresent(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_EDIT_PORTLET_LINK);
		
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		
		//Input data for Query By and Folder Path
		selectContentPathInEditMode(FOLDER_PATH, true);
		click(ELEMENT_ACME_CATEGORY);
		waitForElementPresent(ELEMENT_BY_QUERY_TEXTAREA);
		type(ELEMENT_BY_QUERY_TEXTAREA, DATA_BY_QUERY_INVALID, true);
		
		//Save > Close > Finish
		save();
		close();
		waitForElementPresent(ELEMENT_PAGE_EDIT_FINISH);
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH, 50000);
		
		//Verify documents in the folder you selected are displayed
		waitForTextPresent("Flight");
		captureScreen("ECMS_WCM_Viewer_ContentsQuery_test04.jpg");
		
		//Delete data
		deletePageAtManagePageAndPortalNavigation(DATA_PAGE_NAME_04, true, "acme", false, null);
	}
	
	//Check if a query finds no match in a workspace
	@Test()
	public void test05_CheckQueryNoMatch()
	{
		//Login acme
		loginEcms(DATA_USER_JOHN, DATA_PASS);
		
		//Create a page with portlet Content By Query
		createPage_ContentByQuery_EmptyLayout(DATA_PAGE_NAME_05);
		
		//Open Portlet Setting
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		waitForElementPresent(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_EDIT_PORTLET_LINK);
		
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		
		//Input data for Query By and Folder Path
		selectContentPathInEditMode(FOLDER_PATH, true);
		click(ELEMENT_ACME_CATEGORY);
		waitForElementPresent(ELEMENT_BY_QUERY_TEXTAREA);
		type(ELEMENT_BY_QUERY_TEXTAREA, DATA_BY_QUERY, true);
		waitForElementPresent(ELEMENT_WORKSPACE_SELECT);
		select(ELEMENT_WORKSPACE_SELECT, "dev-monit");
		
		//Save > Close > Finish
		save();
		close();
		waitForElementPresent(ELEMENT_PAGE_EDIT_FINISH);
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH, 50000);
		
		//Verify message 'Sorry, no articles are available' displays. 
		waitForTextPresent(NO_ARTICLE_MESSAGE);
		
		//Delete data
		deletePageAtManagePageAndPortalNavigation(DATA_PAGE_NAME_05, true, "acme", false, null);
	}
	
	//Vary tests with some queries
	@Test()
	public void test06_CheckWithVaryQueries()
	{
		//Login acme
		loginEcms(DATA_USER_JOHN, DATA_PASS);
		
		//Create a page with portlet Content By Query
		createPage_ContentByQuery_EmptyLayout(DATA_PAGE_NAME_06);
		
		//Open Portlet Setting
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		waitForElementPresent(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_EDIT_PORTLET_LINK);
		
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		
		//Input data for Query By and Folder Path
		selectContentPathInEditMode(FOLDER_PATH, true);
		click(ELEMENT_ACME_CATEGORY);
		waitForElementPresent(ELEMENT_BY_QUERY_TEXTAREA);
		type(ELEMENT_BY_QUERY_TEXTAREA, DATA_BY_QUERY, true);
		
		//Save > Close > Finish
		save();
		close();
		waitForElementPresent(ELEMENT_PAGE_EDIT_FINISH);
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH, 50000);
		
		//Verify documents having node type nt:file in collaboration are displayed
		//waitForTextPresent("illustration");
		captureScreen("ECMS_WCM_Viewer_ContentsQuery_test06.jpg");
		
		//Change workspace
		goToEditPageEditor();
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		waitForElementPresent(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_EDIT_PORTLET_LINK);
		waitForElementPresent(ELEMENT_WORKSPACE_SELECT);
		select(ELEMENT_WORKSPACE_SELECT, "dev-monit");
		
		//Save > Close > Finish
		save();
		close();
		waitForElementPresent(ELEMENT_PAGE_EDIT_FINISH);
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH, 50000);
		
		//Verify message 'Sorry, no articles are available' displays. 
		waitForTextPresent(NO_ARTICLE_MESSAGE);
		
		//Delete data
		deletePageAtManagePageAndPortalNavigation(DATA_PAGE_NAME_06, true, "acme", false, null);
	}
	
	//User who is not administrator cannot edit portlet settings
	@Test()
	public void test07_CheckEditPortletSettingRight()
	{
		//Login acme
		loginEcms(DATA_USER_MARY, DATA_PASS);
		
		//Create a page with portlet Content By Query
		createPage_ContentByQuery_EmptyLayout(DATA_PAGE_NAME_07);
		
		//Verify mary can't edit portlet
		waitForElementPresent(BLOCK_LAYOUT);
		waitForElementNotPresent(ELEMENT_EDIT_PORTLET_LINK);
		
		//Click Abort button
		click(ELEMENT_PAGE_EDIT_ABORT);
	}

	@AfterMethod
	public void afterMethods() throws Exception 
	{
		driver.quit();
		actions = null;
	}
}
