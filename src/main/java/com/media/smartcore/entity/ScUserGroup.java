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
 * ScUserGroup generated by hbm2java
 */
@Entity
@Table(name = "SC_USER_GROUP")
public class ScUserGroup extends BaseEntity implements java.io.Serializable {

	private String scGroupId;
	private String scUserId;
	private String creatorId;
	private Date createDate;

	public ScUserGroup() {
	}

	public ScUserGroup(String id) {
		this.id = id;
	}

	public ScUserGroup(String id, String scGroupId, String scUserId, String creatorId, Date createDate) {
		this.id = id;
		this.scGroupId = scGroupId;
		this.scUserId = scUserId;
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

	@Column(name = "SC_GROUP_ID", length = 64)
	public String getScGroupId() {
		return this.scGroupId;
	}

	public void setScGroupId(String scGroupId) {
		this.scGroupId = scGroupId;
	}

	@Column(name = "SC_USER_ID", length = 64)
	public String getScUserId() {
		return this.scUserId;
	}

	public void setScUserId(String scUserId) {
		this.scUserId = scUserId;
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