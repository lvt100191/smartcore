package com.media.smartcore.lazy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.media.smartcore.dao.GenericDao;
import com.media.smartcore.exception.AppException;

public class LazyDataModelBase<T, PK extends Serializable> extends LazyDataModel<T> {
	private static final long serialVersionUID = -173540452642281539L;
	private GenericDao<T, PK> daoService;

	public LazyDataModelBase(GenericDao<T, PK> daoService) {
		this.daoService = daoService;
	}

	public void setRowIndex(int rowIndex) {
		if ((rowIndex == -1) || (getPageSize() == 0))
			super.setRowIndex(-1);
		else
			super.setRowIndex(rowIndex % getPageSize());
	}

	public T getRowData(PK rowKey) {
		T object = null;
		try {
			object = this.daoService.findById(rowKey);
		} catch (AppException e) {
			e.printStackTrace();
		}

		return object;
	}

	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List data = new ArrayList();
		int dataSize = 0;
		try {
			data = this.daoService.findList(first, pageSize, filters);
			dataSize = this.daoService.count(filters);
		} catch (AppException e) {
		}
		setRowCount(dataSize);
		return data;
	}

	public GenericDao<T, PK> getDaoService() {
		return this.daoService;
	}

	public void setDaoService(GenericDao<T, PK> daoService) {
		this.daoService = daoService;
	}
}