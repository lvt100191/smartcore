package com.media.smartcore.dao.impl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.media.smartcore.dao.GenericDao;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;

/**
 * Class implement interface Generic Dao Service
 * 
 */
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	private static Logger logger = LogManager.getLogger(GenericDaoImpl.class);

	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> domainClass = (Class<T>) getTypeArguments(GenericDaoImpl.class, this.getClass()).get(0);

	/**
	 * Method to return the class of the domain object
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnpt.persistence.util.GenericDaoService#findById(java.io.
	 * Serializable)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T findById(PK id) throws AppException, SysException {
		return (T) currentSession().get(domainClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#delete(java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T object) throws AppException, SysException {
		currentSession().delete(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#delete(java.util.List)
	 */
	@Override
	public void delete(List<T> objects) throws AppException, SysException {
		for (T object : objects)
			delete(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#saveOrUpdate(java.lang.
	 * Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(T object) throws AppException, SysException {
		currentSession().saveOrUpdate(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#save(java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PK save(T object) throws AppException, SysException {
		return (PK) currentSession().save(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#saveOrUpdate(java.util.
	 * List)
	 */
	@Override
	public void saveOrUpdate(List<T> objects) throws AppException, SysException {
		for (T object : objects)
			saveOrUpdate(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnpt.persistence.util.GenericDaoService#findList()
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findList() throws AppException, SysException {
		Criteria criteria = currentSession().createCriteria(domainClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#findList(java.util.Map,
	 * java.util.Map)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findList(Map<String, Object> filters, Map<String, String> orders) throws AppException, SysException {
		Criteria criteria = currentSession().createCriteria(domainClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// Xu ly filter.
		setCriteriaRestrictions(criteria, filters);

		// Xu ly order
		setCriteriaOrders(criteria, orders);

		return criteria.list();
			
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnpt.persistence.util.GenericDaoService#findList(int, int,
	 * java.util.Map)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findList(int first, int pageSize, Map<String, Object> filters) throws AppException, SysException {
		Criteria criteria = currentSession().createCriteria(domainClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// Xu ly filter.
		setCriteriaRestrictions(criteria, filters);

		// Xu ly paging
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnpt.persistence.util.GenericDaoService#findList(int, int,
	 * java.util.Map, java.util.Map)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findList(int first, int pageSize, Map<String, Object> filters, Map<String, String> orders)
		throws AppException, SysException {
	
		Criteria criteria = currentSession().createCriteria(domainClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// Xu ly filter.
		setCriteriaRestrictions(criteria, filters);

		// Xu ly order
		setCriteriaOrders(criteria, orders);

		// Xu ly paging
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnpt.persistence.util.GenericDaoService#count(java.util.Map)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public int count(Map<String, Object> filters) throws AppException, SysException {
			Criteria criteria = currentSession().createCriteria(domainClass);

			// Xu ly filter.
			setCriteriaRestrictions(criteria, filters);

			criteria.setProjection(Projections.rowCount());
			return criteria.uniqueResult() == null ? 0 : ((Long) criteria.uniqueResult()).intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnpt.persistence.util.GenericDaoService#count()
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public int count() throws AppException, SysException {
		Criteria criteria = currentSession().createCriteria(domainClass);
		criteria.setProjection(Projections.rowCount());
		return criteria.uniqueResult() == null ? 0 : ((Long) criteria.uniqueResult()).intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#persist(java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(T object) throws AppException, SysException {
		currentSession().persist(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vnpt.persistence.util.GenericDaoService#merge(java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(T object) throws AppException, SysException {
		currentSession().merge(object);
	}

	@Override
	public T get(PK id) throws AppException, SysException {
		return (T) currentSession().get(domainClass, id);
	}

	/**
	 * Thêm filter cho criteria.
	 * 
	 * @param criteria
	 * @return criteria
	 */
	protected Criteria setCriteriaRestrictions(Criteria criteria, Map<String, Object> filters) {
		if (filters == null)
			return criteria;

		Map<String, String> properties = getFields();

		String type = "";
		String filedName = "";
		Object fieldValue = "";
		Map<String, String> alias = new HashMap<>();

		for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
			filedName = it.next();
			fieldValue = filters.get(filedName);

			String[] fields = filedName.split("\\.");

			type = properties.get(filedName);
			switch (type) {
				case "java.lang.String":
					if (fields.length != 2) {
							criteria.add(Restrictions.ilike(filedName, fieldValue.toString().toLowerCase(), MatchMode.ANYWHERE));
					} else {
						if (alias.get(fields[0]) == null) {
							criteria.createAlias(fields[0], fields[0] + "alias");
							alias.put(fields[0], fields[0] + "alias");
						}
						criteria.add(Restrictions.ilike(fields[0] + "alias." + fields[1], fieldValue.toString().toLowerCase(), MatchMode.ANYWHERE));
					}
					break;
				case "java.lang.Integer":
					if (fields.length != 2)
						criteria.add(Restrictions.eq(filedName, Integer.valueOf(fieldValue.toString())));
					else {
						if (alias.get(fields[0]) == null) {
							criteria.createAlias(fields[0], fields[0] + "alias");
							alias.put(fields[0], fields[0] + "alias");
						}
						criteria.add(Restrictions.eq(fields[0] + "alias." + fields[1], Integer.valueOf(fieldValue.toString())));
					}
					break;
				case "java.lang.Long":
					if (fields.length != 2)
						criteria.add(Restrictions.eq(filedName, Long.valueOf(fieldValue.toString())));
					else {
						if (alias.get(fields[0]) == null) {
							criteria.createAlias(fields[0], fields[0] + "alias");
							alias.put(fields[0], fields[0] + "alias");
						}
						criteria.add(Restrictions.eq(fields[0] + "alias." + fields[1], Long.valueOf(fieldValue.toString())));
					}
					break;
				case "java.lang.Boolean":
					if (fields.length != 2)
						criteria.add(Restrictions.eq(filedName, fieldValue.equals("1")));
					else {
						if (alias.get(fields[0]) == null) {
							criteria.createAlias(fields[0], fields[0] + "alias");
							alias.put(fields[0], fields[0] + "alias");
						}
						criteria.add(Restrictions.eq(fields[0] + "alias." + fields[1], fieldValue.equals("1")));
					}
					break;
				default:
					break;
			}
		}

		return criteria;
	}

	/**
	 * Thêm order cho criteria.
	 * 
	 * @param criteria
	 * 
	 * @return criteria
	 */
	protected Criteria setCriteriaOrders(Criteria criteria, Map<String, String> orders) {
		if (orders == null)
			return criteria;

		final String _ASC = "ASC";
		final String _DESC = "DESC";
		String propertyName = "";
		String orderType = "";
		for (Iterator<String> it = orders.keySet().iterator(); it.hasNext();) {
			propertyName = it.next();
			orderType = orders.get(propertyName);

			switch (orderType.toUpperCase()) {
			case _ASC:
				criteria.addOrder(Order.asc(propertyName));
				break;
			case _DESC:
				criteria.addOrder(Order.desc(propertyName));
				break;
			default:
				criteria.addOrder(Order.asc(propertyName));
				break;
			}
		}

		return criteria;
	}

	/**
	 * Get all field and type of object.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Map<String, String> getFields() {
		PropertyDescriptor[] propertyDescriptors;
		Map<String, String> result = new HashMap<String, String>();
		try {
			propertyDescriptors = Introspector.getBeanInfo(domainClass).getPropertyDescriptors();
			String fieldName = "";
			String fieldType = "";
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				fieldName = propertyDescriptor.getName();
				fieldType = propertyDescriptor.getPropertyType().getCanonicalName();
				Class<?> clazz = null;
				try {
					clazz = Class.forName(fieldType);
				} catch (ClassNotFoundException e) {
				}
				
				if (clazz!= null && ClassUtils.isPrimitiveOrWrapper(clazz)) {
					result.put(fieldName, fieldType);
				} else if (fieldType.equalsIgnoreCase("java.lang.String")) {
					result.put(fieldName, fieldType);
				} else if (fieldType.equalsIgnoreCase("java.util.Date")) {
					result.put(fieldName, fieldType);
				} else {
					if (!fieldType.equalsIgnoreCase("java.lang.Class")) {
						result = getSubField(fieldName, fieldType, result);
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		return result;
	}

	private Map<String, String> getSubField(String fieldName, String fieldType, Map<String, String> result) {
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Class.forName(fieldType))
					.getPropertyDescriptors();
			String subFieldName = "";
			String subFieldType = "";
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				subFieldName = fieldName.concat("." + propertyDescriptor.getName());
				subFieldType = propertyDescriptor.getPropertyType().getCanonicalName();

				if (ClassUtils.isPrimitiveOrWrapper(Class.forName(subFieldType))) {
					result.put(subFieldName, subFieldType);
				} else if (subFieldType.equalsIgnoreCase("java.lang.String")) {
					result.put(subFieldName, subFieldType);
				} else if (subFieldType.equalsIgnoreCase("java.util.Date")) {
					result.put(subFieldName, subFieldType);
				} else {
					if (!subFieldType.equalsIgnoreCase("java.lang.Class")) {
						result = getSubField(subFieldName, subFieldType, result);
					}
				}
			}
		} catch (IntrospectionException | ClassNotFoundException e) {
		}

		return result;
	}

	/**
	 * Get the actual type arguments a child class has used to extend a generic
	 * base class. (Taken from
	 * http://www.artima.com/weblogs/viewpost.jsp?thread=208860. Thanks
	 * mathieu.grenonville for finding this solution!)
	 * 
	 * @param baseClass
	 *            the base class
	 * @param childClass
	 *            the child class
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(Class<T> baseClass, Class<? extends T> childClass) {
		Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass)) {
			if (type instanceof Class) {
				// there is no useful information for us in raw types, so just
				// keep going.
				type = ((Class) type).getGenericSuperclass();
			} else {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?> rawType = (Class) parameterizedType.getRawType();

				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
				for (int i = 0; i < actualTypeArguments.length; i++) {
					resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
				}

				if (!rawType.equals(baseClass)) {
					type = rawType.getGenericSuperclass();
				}
			}
		}

		// finally, for each actual type argument provided to baseClass,
		// determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class) {
			actualTypeArguments = ((Class) type).getTypeParameters();
		} else {
			actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
		}
		List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
		// resolve types by chasing down type variables.
		for (Type baseType : actualTypeArguments) {
			while (resolvedTypes.containsKey(baseType)) {
				baseType = resolvedTypes.get(baseType);
			}
			typeArgumentsAsClasses.add(getClass(baseType));
		}
		return typeArgumentsAsClasses;
	}

	/**
	 * Get the underlying class for a type, or null if the type is a variable
	 * type.
	 * 
	 * @param type
	 *            the type
	 * @return the underlying class
	 */
	private static Class<?> getClass(Type type) {
		if (type instanceof Class) {
			return (Class) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type).getGenericComponentType();
			Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
