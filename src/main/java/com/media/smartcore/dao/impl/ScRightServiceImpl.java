package com.media.smartcore.dao.impl;


import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.media.smartcore.dao.ScRightService;
import com.media.smartcore.entity.ScRight;

/**
 * Service implement for interface ExampleService.
 */

@Service(value = "rightService")
@Scope("session")
public class ScRightServiceImpl extends GenericDaoImpl<ScRight, Serializable> implements ScRightService, Serializable {
	private static Logger logger = LogManager.getLogger(ScRightServiceImpl.class);
}
