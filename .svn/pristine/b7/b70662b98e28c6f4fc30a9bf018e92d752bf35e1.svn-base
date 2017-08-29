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
import com.media.smartcore.dao.ScRightService;
import com.media.smartcore.dao.ScRoleService;
import com.media.smartcore.entity.ScRight;
import com.media.smartcore.entity.ScRole;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;

/**
 * 
 */
@ViewScoped
@ManagedBean
public class ScRightController extends GenericController<ScRight> implements Serializable {
	private static Logger logger = LogManager.getLogger(ScRightController.class);
	@ManagedProperty(value = "#{rightService}")
	ScRightService rightService;
	public void setrightService(ScRightService rightService) {
		this.rightService = rightService;
	}
	@Override
	public void processSearch(Map<String, String> filters) {
		((DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form").findComponent("objectTable"))
				.setFirst(0);
		if (StringUtils.isNotEmpty(searchObj.getName()))
			filters.put("name", searchObj.getName());
		if (StringUtils.isNotEmpty(searchObj.getDescription()))
			filters.put("description", searchObj.getDescription());
		
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
	public GenericDao<ScRight, Serializable> getService() {
		return rightService;
	}

	@Override
	public void initNewObj() {
		setNewObj(new ScRight());
	}

	@Override
	public void initSelectedObj() {
		setSelectedObj(new ScRight());
	}

	@Override
	public void initSearchObj() {
		setSearchObj(new ScRight());
	}
}
