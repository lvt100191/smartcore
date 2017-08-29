package com.media.smartcore.entity;

import java.io.Serializable;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -1440789673405049698L;
	protected String []fields;
	protected String objectType = this.getClass().toString();
	protected String id;

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	
	public String[] getFields() {
		return fields;
	}

	public void setFields(String []fields) {
		this.fields = fields;
	}
	
	public void setFieldS(String []fields) {
		this.setFields(fields);
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectType() {
		return objectType;
	}
	
	public String toJSONString() {
		JSONObject obj = null;
		if (fields != null) {
			obj = new JSONObject(this, fields);
		} else {
			obj = new JSONObject(this);
		}
		return obj.toString();
	}
	
	public String toJSONString(String []fields) {
		JSONObject obj = null;
		if (fields != null) {
			obj = new JSONObject(this, fields);
		} else {
			obj = new JSONObject(this);
		}
		return obj.toString();
	}

	public BaseEntity parseJSON2Object(String jsonContent) {
    	try {
			JSONObject jso = new JSONObject(jsonContent);
			this.setId(jso.optString("id"));
		} catch (JSONException e) {
		}
		return this;
    }

}
