package com.media.smartcore.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import com.media.smartcore.dao.GenericDao;
import com.media.smartcore.entity.BaseEntity;
import com.media.smartcore.entity.ScAdminMenu;
import com.media.smartcore.entity.ScUser;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;
import com.media.smartcore.lazy.LazyDataModelBaseNew;
import com.media.smartcore.util.MessageUtil;

public abstract class GenericController<T extends BaseEntity> {
	private static Logger logger = LogManager.getLogger(GenericController.class);
	public abstract GenericDao<T, Serializable> getService();
	public abstract void initNewObj();
	public abstract void initSelectedObj();
	public abstract void initSearchObj();
	
	public abstract void processSearch(Map<String, String> filters);
	public abstract void processSaveOrUpdate() throws AppException, SysException;
	
	public T selectedObj;
	public T newObj;
	public T searchObj;
	public boolean isEdit;
	public LazyDataModel<T> lazyDataModel;
	public ResourceBundle bundle;
	
	public String pageTitle ="SmartCore";
	
	@PostConstruct
	public void onStart() {
		clear();
		FacesContext context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context, "msg");
		Map<String, Object> filters = new HashMap<>();
		lazyDataModel = new LazyDataModelBaseNew<>(getService(), filters);
	}
	public void clear() {
		((DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form").findComponent("objectTable"))
		.setFirst(0);
		isEdit = false;
		initNewObj();;
		initSearchObj();
		initSelectedObj();
	}
	
	public void search() {
		((DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form").findComponent("objectTable"))
				.setFirst(0);
		Map<String, String> filters = new HashMap<>();
		processSearch(filters);
		lazyDataModel = new LazyDataModelBaseNew(getService(), filters);
	}
	public void saveOrUpdate() {
		String msg = "";
		try {
			processSaveOrUpdate();
			if (isEdit) {
				msg = bundle.getString("msg.update_success");//"Cập nhật thành công";
			}
			else {
				msg = bundle.getString("msg.add_new_success");//"Thêm mới thành công";
			}
			initSelectedObj();
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(selectedObj, newObj);

			if (!isEdit) {
				// thêm mới thì setid null
				selectedObj.setId(null);
			}
			getService().saveOrUpdate(selectedObj);
			MessageUtil.setInfoMessage(msg);
			RequestContext.getCurrentInstance().update("form:objectTable");	// load lại dữ liệu bảng
			clear();
		} catch (SysException | AppException | IllegalAccessException | InvocationTargetException e) {
			msg = bundle.getString("msg.update_fail");//"Cập nhật thất bại";
			MessageUtil.setErrorMessage(msg);
			logger.error(e);
		} 
	}
	public void delete(T tempSelectedObj) {
		try {
			getService().delete(tempSelectedObj);
			MessageUtil.setInfoMessage(bundle.getString("msg.delele_success"));
		} catch (SysException | AppException e) {
			MessageUtil.setErrorMessage(bundle.getString("msg.delele_fail"));
			logger.error(e);
		} 
	}
	public void prepareEdit() {
		isEdit = true;
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
		try {
			BeanUtils.copyProperties(newObj, selectedObj);			
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e);
		}
	}
	public void duplicate(ScUser obj) {
		isEdit = false;
		obj.setId(null);
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
		try {
			BeanUtils.copyProperties(newObj, obj);
			saveOrUpdate();
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e);
		}
	}
	
	public T getSelectedObj() {
		return selectedObj;
	}
	public void setSelectedObj(T selectedObj) {
		this.selectedObj = selectedObj;
	}
	public T getNewObj() {
		return newObj;
	}
	public void setNewObj(T newObj) {
		this.newObj = newObj;
	}
	public T getSearchObj() {
		return searchObj;
	}
	public void setSearchObj(T searchObj) {
		this.searchObj = searchObj;
	}
	public boolean getIsEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	public LazyDataModel<T> getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyDataModel<T> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
}
