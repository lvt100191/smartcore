package com.media.smartcore.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * 
 */
@ViewScoped
@ManagedBean
public class LocaleController implements Serializable {
//	private static Logger logger = LogManager.getLogger(ScUserController.class);
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	public LocaleController() {
		locale = new Locale("vi");	
	}
	public void chooseLanguage(String language) {
		setLanguage(language);
	}
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        this.locale = new Locale(language);
    }
	
}
