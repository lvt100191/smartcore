package com.media.smartcore.dao;

import java.io.Serializable;

import com.media.smartcore.entity.ScUser;
import com.media.smartcore.exception.AppException;
import com.media.smartcore.exception.SysException;

/**
 * Service interface for domain model class Example.
 */

public interface ScUserService extends GenericDao<ScUser, Serializable> {
	public ScUser checkExistByUserName(String username, String email, String id) throws AppException, SysException;
	
	public ScUser login(String username, String password) throws AppException, SysException;
}
