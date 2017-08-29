package com.media.smartcore.lazy;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.media.smartcore.dao.GenericDao;
import com.media.smartcore.dao.impl.GenericDaoImpl;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;

/**
 * @param <T>
 * @param <PK>
 */
public class LazyDataModelBaseNew<T, PK extends Serializable> extends
		LazyDataModelBase<T, PK> {

	private static final long serialVersionUID = -8213459208378430543L;
	private GenericDao<T, PK> daoService;
	private Map<String, Object> filters;
	private LinkedHashMap<String, String> orders;

	@SuppressWarnings("unchecked")
	public LazyDataModelBaseNew(GenericDao<T, PK> daoService,
			Object... filtersOrOrders) {
		super(daoService);
		if (daoService != null)
			this.daoService = daoService;
		else
			this.daoService = new GenericDaoImpl<T, PK>() {
			};
		if (filtersOrOrders != null) {
			switch (filtersOrOrders.length) {
			case 1:
				if (filtersOrOrders[0] instanceof Map<?, ?>)
					filters = (Map<String, Object>) filtersOrOrders[0];
				break;
			case 2:
				if (filtersOrOrders[0] != null
						&& filtersOrOrders[0] instanceof Map<?, ?>)
					filters = (Map<String, Object>) filtersOrOrders[0];
				if (filtersOrOrders[1] != null
						&& filtersOrOrders[1] instanceof Map<?, ?>)
					orders = (LinkedHashMap<String, String>) filtersOrOrders[1];
				break;
			default:
				// No sort or filter
				break;

			}
		}
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		List<T> data = new ArrayList<T>();
		int dataSize = 0;
		try {
			if (this.filters != null) {
				for (Iterator<String> iterator = this.filters.keySet()
						.iterator(); iterator.hasNext();) {
					String field = (String) iterator.next();
					Object value = this.filters.get(field);
					if (value instanceof String) {
						filters.put(field, (String) value);
					} else if (value instanceof Date[]) {
						Date[] filDate = (Date[]) value;
						SimpleDateFormat formatter = new SimpleDateFormat(
								"dd/MM/yyyy HH:mm:ss");
						Date fromDate;
						Date toDate;
						switch (filDate.length) {
						case 1:
							fromDate = filDate[0];
							if (fromDate != null)
								filters.put(field, formatter.format(fromDate)
										+ "-");
							break;
						case 2:
							fromDate = filDate[0];
							toDate = filDate[1];
							if (fromDate == null && toDate != null)
								filters.put(field,
										"-" + formatter.format(toDate));
							else if (fromDate != null && toDate == null)
								filters.put(field, formatter.format(fromDate)
										+ "-");
							else if (fromDate != null && toDate != null)
								filters.put(field, formatter.format(fromDate)
										+ "-" + formatter.format(toDate));
							break;

						default:
							break;
						}
					} else if (value instanceof Number) {
						filters.put(field, ((Number) value).toString());
					}

				}
			}
			LinkedHashMap<String, String> sorter = null;
			if (this.orders != null) {
				sorter = new LinkedHashMap<String, String>();
				for (Iterator<String> iterator = this.orders.keySet()
						.iterator(); iterator.hasNext();) {
					String field = (String) iterator.next();
					String value = this.orders.get(field);
					sorter.put(field, value);
				}
			}
			if (sortField != null) {
				if (sorter == null)
					sorter = new LinkedHashMap<String, String>();
				switch (sortOrder) {
				case ASCENDING:
					sorter.put(sortField, "ASC");
					break;
				case DESCENDING:
					sorter.put(sortField, "DESC");
					break;
				case UNSORTED:
				default:
					sorter = null;
					break;
				}
			}
			data = daoService.findList(first, pageSize, filters, sorter);
			dataSize = daoService.count(filters);

		} catch (SysException | AppException e) {
		}

		this.setRowCount(dataSize);
		return data;
	}
}