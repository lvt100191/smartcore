package com.media.smartcore.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
* Các hàm thao tác với session cơ bản của cả hệ thống. 
*
*/

public class SessionWrapper implements Serializable{
	private static final long serialVersionUID 	= -8318262775763386620L;
	private static final String _AUTHEN_USER_TOKEN = "authenUserToken";
	private static final String _AUTHEN_USER_ID 	= "userID";
	
	/**
	 * Get current session cua he thong.
	 * 
	 * @return current session
	 */
	public static HttpSession getCurrentSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request.getSession();
	}
	
	/**
	 * Lay gia tri session attribute.
	 * 
	 * @param attributeName
	 * @return
	 */
	public String getSessionAttribute(String attributeName){
		return (String) getCurrentSession().getAttribute(attributeName);
	}

	/**
	 * Lay thong tin cua user hien tai dang login.
	 */
	public static String getCurrentUserId(){
		return (String) getCurrentSession().getAttribute(_AUTHEN_USER_ID);
	}
	
	
}// End class
