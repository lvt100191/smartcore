/*
 * Created on Jun 7, 2013
 *
 * Copyright (C) 2013 by Viettel Network Company. All rights reserved
 */
package com.media.smartcore.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.media.smartcore.dao.ScUserService;
import com.media.smartcore.entity.ScUser;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;
import com.media.smartcore.util.MD5;
import com.media.smartcore.util.MessageUtil;
import com.media.smartcore.util.SessionWrapper;

/**
 * Chức năng chính để forward các url tới phần phân quyền.
 * 
 * @version 1.0.0
 */
@RequestScoped
@ManagedBean
public class AuthenController implements Serializable {
	private static final long serialVersionUID = 4870520554535423726L;
	// Trang home.
	private static final String _HOME_PAGE = "/home";
	private static final String _AUTHEN_USER_TOKEN = "authenUserToken";
	private static final String _AUTHEN_USER_ID 	= "userID";

	private ScUser newObj;
	ResourceBundle bundle;
	@ManagedProperty(value = "#{userService}")
	ScUserService userService;
	public void setUserService(ScUserService userService) {
		this.userService = userService;
	}
	
	@PostConstruct
	public void onStart() {
		FacesContext context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context, "msg");
		newObj = new ScUser();
	}
	
	public void login() {
		String msg = "";
		String username = newObj.getUsername() == null ? "" : newObj.getUsername();
		String password = MD5.toDigest(newObj.getUsername() + newObj.getPassword());
		
		try {
			ScUser user = userService.login(username, password);
			if(user == null) {
				msg = "Tên đăng nhập hoặc mật khẩu không đúng";
				MessageUtil.setErrorMessage(msg);
				return;
			}
			HttpSession session = SessionWrapper.getCurrentSession();
			session.setAttribute(_AUTHEN_USER_TOKEN, user);
			session.setAttribute(_AUTHEN_USER_ID, user.getId());
			
			homeForward();
		} catch (SysException | AppException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Dieu huong den trang home page.
	 * 
	 */
	private void homeForward() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		try {
			fc.getExternalContext().redirect(req.getContextPath() + _HOME_PAGE);
		} catch (IOException e) {
		}
	}

	/**
	 * Redirect toi trang home.
	 * 
	 * @throws IOException
	 */
	public void doForward(final ComponentSystemEvent event) throws IOException {
		homeForward();
	}

	/**
	 * Dieu huong den trang mac dinh cua user.
	 * 
	 * @throws IOException
	 */
	public void doRedirect(final ComponentSystemEvent event) throws IOException {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();

			// Lay gia tri menu default cua user dang nhap.
			/*String defaultUrl = SessionWrapper.getMenuDefault();
			if (defaultUrl == "")
				homeForward();
			else
				fc.getExternalContext().redirect(req.getContextPath() + defaultUrl);*/
		} catch (SysException e) {
			e.printStackTrace();
		}
	}

	public ScUser getNewObj() {
		return newObj;
	}

	public void setNewObj(ScUser newObj) {
		this.newObj = newObj;
	}
}