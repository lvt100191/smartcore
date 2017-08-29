package com.media.smartcore.util;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtil implements Serializable {
	private static final long serialVersionUID = 754591118471464797L;
	private static ResourceBundle bundle;

	public static ResourceBundle getResourceBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (bundle == null) {
			bundle = context.getApplication().getResourceBundle(context, "msgs");
		}
		return bundle;
	}

	public static String getResourceBundleMessage(String key) {
		if (key.equals("")) {
			return "";
		}
		bundle = getResourceBundle();

		return bundle.getString(key);
	}

	public static void setErrorMessage(String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message);

		FacesContext.getCurrentInstance().addMessage("mainMessage", msg);
	}

	public static void setInfoMessage(String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message);

		FacesContext.getCurrentInstance().addMessage("mainMessage", msg);
	}

	public static void setWarnMessage(String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", message);

		FacesContext.getCurrentInstance().addMessage("mainMessage", msg);
	}

	public static void setFatalMessage(String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", message);

		FacesContext.getCurrentInstance().addMessage("mainMessage", msg);
	}
}