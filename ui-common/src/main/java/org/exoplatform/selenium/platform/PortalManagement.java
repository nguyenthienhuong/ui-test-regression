package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.Map;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

public class PortalManagement extends PlatformBase {

	//Add new portal
	public static void addNewPortal(String portalName, String portalLocale, String portalSkin, String portalSession, 
			boolean publicMode, Map<String, String> permissions, String editGroupId, String editMembership){
		info("--Create new portal--");

		click(ELEMENT_ADD_NEW_PORTAL_LINK);
		waitForTextPresent("Portal Setting");
		type(ELEMENT_INPUT_NAME, portalName, true);
		select(ELEMENT_SELECT_LOCALE, portalLocale);
		select(ELEMENT_SELECT_SKIN, portalSkin);
		click(ELEMENT_PROPERTIES_TAB);
		select(ELEMENT_SELECT_SESSION_ALIVE, portalSession);
		click(ELEMENT_PERMISSION_SETTING_TAB);

		if (publicMode) {
			waitForAndGetElement(ELEMENT_ADD_PERMISSION_BUTTON);
			check(ELEMENT_CHECKBOX_PUBLIC_MODE);
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}
		click(ELEMENT_LINK_EDIT_PERMISSION);
		setEditPermissions(editGroupId, editMembership);
		save();
	}

	//Edit a portal
	public static void editPortal(String portalName, String portalLocale, String portalSkin, String portalSession, 
			boolean publicMode, Map<String, String> permissions, String editGroupId, String editMembership){
		info("--Create new portal--");

		String editIcon = ELEMENT_SELECT_EDIT_PORTAL_CONFIG.replace("${portalName}", portalName);		
		click(editIcon);

		waitForTextPresent("Portal Setting");

		select(ELEMENT_SELECT_LOCALE, portalLocale);
		select(ELEMENT_SELECT_SKIN, portalSkin);
		click(ELEMENT_PROPERTIES_TAB);
		select(ELEMENT_SELECT_SESSION_ALIVE, portalSession);
		click(ELEMENT_PERMISSION_SETTING_TAB);

		click (ELEMENT_CHECKBOX_PUBLIC_MODE);

		if (publicMode) {
			waitForAndGetElement(ELEMENT_ADD_PERMISSION_BUTTON);
			check(ELEMENT_CHECKBOX_PUBLIC_MODE);
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}
		click(ELEMENT_LINK_EDIT_PERMISSION);
		setEditPermissions(editGroupId, editMembership);
		save();
	}

	//Delete a portal	
	public static void deletePortal(String portalName){
		String portalDeleteIcon = ELEMENT_PORTAL_DELETE_ICON.replace("${portalName}", portalName);
		info("--Delete portal (" + portalName + ")--");		
		click(portalDeleteIcon);
		waitForConfirmation("Are you sure to delete this portal?");
		//info("--Verify portal is deleted--");
		pause(30000);
		waitForTextNotPresent(portalName);
	}

	//Verify the existence of portal
	public static void verifyPortalExists(String portalName) {
		String portal = ELEMENT_PORTAL_IN_LIST.replace("${portalName}", portalName);

		info("--Verify portal (" + portalName + ") exists--");
		goToPortalSites();
		waitForAndGetElement(portal);
	}

}
