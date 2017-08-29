package com.media.smartcore.dao.impl;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.media.smartcore.dao.ScUserService;
import com.media.smartcore.entity.ScUser;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;

/**
 * Service implement for interface ExampleService.
 */

@Service(value = "userService")
@Scope("session")
public class ScUserServiceImpl extends GenericDaoImpl<ScUser, Serializable> implements ScUserService, Serializable {
//	private static Logger logger = LogManager.getLogger(ExampleServiceImpl.class);
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ScUser checkExistByUserName(String username, String email, String id) throws AppException, SysException {
		ScUser object = null;
		List<ScUser> objects = null;
		Criteria criteria = currentSession().createCriteria(ScUser.class);
		Criterion rest1= Restrictions.eq("username", username).ignoreCase();
		Criterion rest2= Restrictions.eq("email", email).ignoreCase();
		criteria.add(Restrictions.or(rest1, rest2));

		criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		
		objects = criteria.list();
		if(objects.size() > 0)
			object = objects.get(0);
		return object;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ScUser login(String username, String password) throws AppException, SysException {
		ScUser object = null;
		List<ScUser> objects = null;
		Criteria criteria = currentSession().createCriteria(ScUser.class);
		criteria.add(Restrictions.eq("username", username).ignoreCase());
		criteria.add(Restrictions.eq("password", password));
		
		objects = criteria.list();
		if(objects.size() > 0)
			object = objects.get(0);
		return object;
	}
}
