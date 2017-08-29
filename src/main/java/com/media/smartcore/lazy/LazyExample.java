package com.media.smartcore.lazy;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.apache.commons.lang3.StringUtils;
////import org.apache.logging.log4j.LogManager;
////import org.apache.logging.log4j.Logger;
//import org.primefaces.model.LazyDataModel;
//import org.primefaces.model.SortOrder;
//
//import com.media.smartcore.dao.ExampleService;
//import com.media.smartcore.entity.Example;
//import com.media.smartcore.exception.AppException;
//import com.media.smartcore.exception.SysException;
//
///**
// * 
// */
public class LazyExample{ 
//	extends LazyDataModel<Example> {
////	private static Logger logger = LogManager.getLogger(LazyExample.class);
//
//	private ExampleService exampleService;
//	private Map<String, String> search;
//
//	public LazyExample(ExampleService exampleService, Map<String, String> search) {
//		this.exampleService = exampleService;
//		this.search = search;
//	}
//
//	@Override
//	public void setRowIndex(int rowIndex) {
//		if (rowIndex == -1 || getPageSize() == 0) {
//			super.setRowIndex(-1);
//		} else
//			super.setRowIndex(rowIndex % getPageSize());
//	}
//
//	@Override
//	public Object getRowKey(Example object) {
//		return object.getId();
//	}
//
//	@Override
//	public Example getRowData(String rowKey) {
//		Example object = new Example();
//		try {
//			object = exampleService.findById(rowKey);
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		} catch (SysException e) {
//			e.printStackTrace();
//		} catch (AppException e) {
//			e.printStackTrace();
//		}
//		return object;
//	}
//
//	@Override
//	public List<Example> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
//		List<Example> data = new ArrayList<>();
//		int dataSize = 0;
//
//		Map<String, String> order = new HashMap<>();
//		if (StringUtils.isEmpty(sortField)) {
//			// order.put("username", "ASC");
//		} else {
//			if (sortOrder.equals(SortOrder.ASCENDING))
//				order.put(sortField, "ASC");
//			else
//				order.put(sortField, "DESC");
//		}
//
//		if (search != null) {
//			for (Entry<String, String> filter : search.entrySet()) {
//				filters.put(filter.getKey(), filter.getValue());
//			}
//		}
//
//		try {
//			data = exampleService.findList(first, pageSize, filters, order);
//			dataSize = exampleService.count(filters);
//		} catch (SysException | AppException e) {
//			e.printStackTrace();
//		}
//
//		this.setRowCount(dataSize);
//		return data;
//	}
}
