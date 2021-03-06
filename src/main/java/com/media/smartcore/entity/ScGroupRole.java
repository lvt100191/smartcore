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
 * ScGroupRole generated by hbm2java
 */
@Entity
@Table(name = "SC_GROUP_ROLE")
public class ScGroupRole extends BaseEntity implements java.io.Serializable {

	private String scGroupId;
	private String scRoleId;
	private String creatorId;
	private Date createDate;

	public ScGroupRole() {
	}

	public ScGroupRole(String id) {
		this.id = id;
	}

	public ScGroupRole(String id, String scGroupId, String scRoleId, String creatorId, Date createDate) {
		this.id = id;
		this.scGroupId = scGroupId;
		this.scRoleId = scRoleId;
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

	@Column(name = "SC_ROLE_ID", length = 64)
	public String getScRoleId() {
		return this.scRoleId;
	}

	public void setScRoleId(String scRoleId) {
		this.scRoleId = scRoleId;
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
