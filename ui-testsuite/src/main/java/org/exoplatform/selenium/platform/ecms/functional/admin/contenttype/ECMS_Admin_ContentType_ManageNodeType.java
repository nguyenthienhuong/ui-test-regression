package org.exoplatform.selenium.platform.ecms.functional.admin.contenttype;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by The eXo Platform SAS
 * Author : Hoang Manh Dung
 *          dunghm@exoplatform.com
 * Oct 11, 2012  
 */
public class ECMS_Admin_ContentType_ManageNodeType extends EcmsBase {
	String DATA_USER = "john";
	String DATA_PASS = "gtn";
	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
		gotoManageNoteType();
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}

	@Test
	public void test01_SearchNodeTypeNotInputKeyword(){
		info("Search Node Type when don't input keyword");
		doNodeTypeSearch("");
		waitForTextPresent("The value of the field is empty.");
	}

	@Test
	public void test02_SearchNodeTypeWithSpecialChars(){
		info("Search node type with a set of special characters");
		doNodeTypeSearch("!@#$%^&()_{}[]|\"/?><,~`");
		waitForTextPresent("There are some invalid characters. Please enter another value.");
	}

	@Test
	public void test03_SearchNodeTypeNotMatch(){
		String keyword = RandomStringUtils.randomAlphabetic(20);
		info("Search Node Type when no Node type match with keyword");
		doNodeTypeSearch(keyword);
		waitForTextPresent("There isn't any node type sastisfying the query.");
	}

	@Test
	public void test04_SearchNodeTypeMatch(){
		String keyword = "application";
		info("Search Node type when there are some corresponding node types with keyword");
		doNodeTypeSearch(keyword);
		pause(500);
		List<WebElement> result = driver.findElements(By.xpath("//table[@class='UIGrid']/tbody//tr"));
		for(WebElement tr : result) {
			if(tr.findElement(By.xpath("//div[@class='Text' and contains(text(),'" + keyword + "')]")) == null) {
				assert false: ("This row don't contain keyword");      
			}
		}
	}
	
	@Test
	public void test05_SearchNodeTypeAll(){
		String keyword = "*";
		info("Search all node types");
		doNodeTypeSearch(keyword);     
		captureScreen("test05_SearchNodeTypeAll");
	}
}