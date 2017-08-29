package com.media.smartcore.entity;
// Generated Aug 25, 2017 3:08:59 PM by Hibernate Tools 3.5.0.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ScResource generated by hbm2java
 */
@Entity
@Table(name = "SC_RESOURCE")
public class ScResource extends BaseEntity implements java.io.Serializable {

	private String scObjectType;
	private String scObjectId;
	private Integer status;
	private String scUnitId;
	private String creatorId;
	private Date createDate;

	public ScResource() {
	}

	public ScResource(String id) {
		this.id = id;
	}

	public ScResource(String id, String scObjectType, String scObjectId, Integer status, String scUnitId,
			String creatorId, Date createDate) {
		this.id = id;
		this.scObjectType = scObjectType;
		this.scObjectId = scObjectId;
		this.status = status;
		this.scUnitId = scUnitId;
		this.creatorId = creatorId;
		this.createDate = createDate;
	}

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
	public String getId() {
		return this.id;
	}

	@Column(name = "SC_OBJECT_TYPE", length = 64)
	public String getScObjectType() {
		return this.scObjectType;
	}

	public void setScObjectType(String scObjectType) {
		this.scObjectType = scObjectType;
	}

	@Column(name = "SC_OBJECT_ID", length = 64)
	public String getScObjectId() {
		return this.scObjectId;
	}

	public void setScObjectId(String scObjectId) {
		this.scObjectId = scObjectId;
	}

	@Column(name = "STATUS", precision = 2, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "SC_UNIT_ID", length = 64)
	public String getScUnitId() {
		return this.scUnitId;
	}

	public void setScUnitId(String scUnitId) {
		this.scUnitId = scUnitId;
	}

	@Column(name = "CREATOR_ID", length = 64)
	public String getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}