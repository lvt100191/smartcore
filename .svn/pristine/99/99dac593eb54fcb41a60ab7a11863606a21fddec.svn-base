package com.media.smartcore.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.datatable.DataTable;

import com.media.smartcore.dao.GenericDao;
import com.media.smartcore.dao.ScAdminMenuService;
import com.media.smartcore.entity.ScAdminMenu;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;

/**
 * 
 */
@ViewScoped
@ManagedBean
public class ScAdminMenuController extends GenericController<ScAdminMenu> implements Serializable {
	private static Logger logger = LogManager.getLogger(ScAdminMenuController.class);
	@ManagedProperty(value = "#{adminMenuService}")
	ScAdminMenuService adminMenuService;
	public void setAdminMenuService(ScAdminMenuService adminMenuService) {
		this.adminMenuService = adminMenuService;
	}
	@Override
	public void processSearch(Map<String, String> filters) {
		((DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form").findComponent("objectTable"))
				.setFirst(0);
		if (StringUtils.isNotEmpty(searchObj.getTitle()))
			filters.put("title", searchObj.getTitle());
		if (StringUtils.isNotEmpty(searchObj.getParentId()))
			filters.put("parentId", searchObj.getParentId());
		
	}
	
	@Override
	public void processSaveOrUpdate() throws AppException, SysException {
		if (isEdit) {	// sửa: lấy lại thông tin hiện tại của đối tượng
			newObj.setCreatorId(selectedObj.getCreatorId());
    		newObj.setCreateDate(selectedObj.getCreateDate());
		}
		else {	// thêm mới: set những thông tin tự động, ngoài phần nhập trên giao diện
			newObj.setCreatorId("");
    		newObj.setCreateDate(new Date());
		}
	}

	@Override
	public GenericDao<ScAdminMenu, Serializable> getService() {
		return adminMenuService;
	}

	@Override
	public void initNewObj() {
		setNewObj(new ScAdminMenu());
	}

	@Override
	public void initSelectedObj() {
		setSelectedObj(new ScAdminMenu());
	}

	@Override
	public void initSearchObj() {
		setSearchObj(new ScAdminMenu());
	}
}
