package com.media.smartcore.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import com.media.smartcore.dao.ScUserService;
import com.media.smartcore.entity.ScUser;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;
import com.media.smartcore.lazy.LazyDataModelBaseNew;
import com.media.smartcore.util.MD5;
import com.media.smartcore.util.MessageUtil;

/**
 * 
 */
@ViewScoped
@ManagedBean
public class ScUserController implements Serializable {
	private static Logger logger = LogManager.getLogger(ScUserController.class);

	@ManagedProperty(value = "#{userService}")
	ScUserService userService;
	public void setUserService(ScUserService userService) {
		this.userService = userService;
	}

	private LazyDataModel<ScUser> lazyDataModel;
	private ScUser selectedObj;
	private ScUser newObj;

	private boolean isEdit;
	private ScUser searchObj;
	ResourceBundle bundle;
	@PostConstruct
	public void onStart() {
		clear();
		Map<String, Object> filters = new HashMap<>();
		lazyDataModel = new LazyDataModelBaseNew<>(userService, filters);
	}

	public void search() {
		((DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form").findComponent("objectTable"))
				.setFirst(0);

		Map<String, String> filters = new HashMap<>();

		if (StringUtils.isNotEmpty(searchObj.getUsername()))
			filters.put("username", searchObj.getUsername());
		if (StringUtils.isNotEmpty(searchObj.getLastName()))
			filters.put("lastName", searchObj.getLastName());
		if (StringUtils.isNotEmpty(searchObj.getFirstName()))
			filters.put("firstName", searchObj.getFirstName());
		if (StringUtils.isNotEmpty(searchObj.getEmail()))
			filters.put("email", searchObj.getEmail());
		if (searchObj.getStatus() > -1)
			filters.put("status", searchObj.getStatus() + "");

		lazyDataModel = new LazyDataModelBaseNew(userService, filters);
	}
	
	public void saveOrUpdate() {
		String msg = "";
		try {
			if(userService.checkExistByUserName(newObj.getUsername() == null ? "" : newObj.getUsername()
					, newObj.getEmail() == null ? "" : newObj.getEmail()
					, newObj.getId() == null ? " " : newObj.getId()) != null) {
				msg = "Tên đăng nhập hoặc email đã được sử dụng";
				MessageUtil.setErrorMessage(msg);
				return;
			}
			
			if (isEdit) {
				msg = bundle.getString("msg.update_success");//"Cập nhật thành công";
				// Kiểm tra password có đc cập nhật ko
				if(newObj.getPassword() == null || newObj.getPassword().isEmpty()) {
					newObj.setPassword(selectedObj.getPassword());
				}
				else {
					newObj.setPassword(MD5.toDigest(newObj.getUsername() + newObj.getPassword()));
				}
				newObj.setPasswordLog(selectedObj.getPasswordLog());
	    		newObj.setOwnerId(selectedObj.getOwnerId());
	    		newObj.setInvalidLoginCount(selectedObj.getInvalidLoginCount());
	    		newObj.setSrcId(selectedObj.getSrcId());
	    		newObj.setFacebookId(selectedObj.getFacebookId());
	    		newObj.setSkypeId(selectedObj.getSkypeId());
	    		newObj.setGoogleId(selectedObj.getGoogleId());
	    		newObj.setToken(selectedObj.getToken());
	    		newObj.setCreatorId(selectedObj.getCreatorId());
				newObj.setCreateDate(selectedObj.getCreateDate());
				newObj.setLastLogin(selectedObj.getLastLogin());
				newObj.setCreatorId(selectedObj.getCreatorId());
				newObj.setScId(selectedObj.getScId());
				newObj.setLastUpdate(new Date());
			}
			else {
				msg = bundle.getString("msg.add_new_success");//"Thêm mới thành công";
				// thêm mới
				newObj.setPassword(MD5.toDigest(newObj.getUsername() + newObj.getPassword()));
				//khởi tạo dữ liệu, nếu cần
	    		newObj.setPasswordLog("");
	    		newObj.setOwnerId(0L);
	    		newObj.setInvalidLoginCount(0L);
	    		newObj.setSrcId("");
	    		newObj.setFacebookId("");
	    		newObj.setSkypeId("");
	    		newObj.setGoogleId("");
	    		newObj.setToken("");
	    		newObj.setCreatorId(0L);
	    		newObj.setLastUpdate(new Date());
				newObj.setCreateDate(new Date());
				newObj.setLastLogin(new Date());
				newObj.setCreatorId(0L);
				newObj.setScId("");
			}
			
			selectedObj = new ScUser();
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(selectedObj, newObj);

			if (!isEdit) {
				// thêm mới thì setid null
				selectedObj.setId(null);
			}
			userService.saveOrUpdate(selectedObj);
			MessageUtil.setInfoMessage(msg);
			RequestContext.getCurrentInstance().update("form:objectTable");	// load lại dữ liệu bảng
			clear();
		} catch (SysException | AppException | IllegalAccessException | InvocationTargetException e) {
			msg = bundle.getString("msg.update_fail");//"Cập nhật thất bại";
			MessageUtil.setErrorMessage(msg);
			e.printStackTrace();
		} 
//		RequestContext.getCurrentInstance().execute("editDialog.hide()");
	}

	public void delete(ScUser tempSelectedObj) {
		try {
			userService.delete(tempSelectedObj);
			MessageUtil.setInfoMessage(bundle.getString("msg.delele_success"));
		} catch (SysException | AppException e) {
			MessageUtil.setErrorMessage(bundle.getString("msg.delele_fail"));
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
	public void prepareEdit() {
		isEdit = true;
		
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
		try {
			BeanUtils.copyProperties(newObj, selectedObj);			
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// goi code primeface tu server
//		RequestContext.getCurrentInstance().execute("PF('dlgInfor').show()");
//		RequestContext.getCurrentInstance().update("insertEditForm");
	}

	public void clear() {
		((DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form").findComponent("objectTable"))
		.setFirst(0);
		isEdit = false;
		newObj = new ScUser();
		selectedObj = new ScUser();
		searchObj = new ScUser();
		FacesContext context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context, "msg");
	}

	public LazyDataModel<ScUser> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<ScUser> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public ScUser getSelectedObj() {
		return selectedObj;
	}

	public void setSelectedObj(ScUser selectedObj) {
		this.selectedObj = selectedObj;
	}

	public ScUser getNewObj() {
		return newObj;
	}

	public void setNewObj(ScUser newObj) {
		this.newObj = newObj;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public ScUser getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(ScUser searchObj) {
		this.searchObj = searchObj;
	}

}
