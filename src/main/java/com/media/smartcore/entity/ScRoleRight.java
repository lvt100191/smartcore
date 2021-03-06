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
 * ScRoleRight generated by hbm2java
 */
@Entity
@Table(name = "SC_ROLE_RIGHT")
public class ScRoleRight extends BaseEntity implements java.io.Serializable {

	private String scRoleId;
	private String scRightId;
	private String creatorId;
	private Date createDate;

	public ScRoleRight() {
	}

	public ScRoleRight(String id) {
		this.id = id;
	}

	public ScRoleRight(String id, String scRoleId, String scRightId, String creatorId, Date createDate) {
		this.id = id;
		this.scRoleId = scRoleId;
		this.scRightId = scRightId;
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


	@Column(name = "SC_ROLE_ID", length = 64)
	public String getScRoleId() {
		return this.scRoleId;
	}

	public void setScRoleId(String scRoleId) {
		this.scRoleId = scRoleId;
	}

	@Column(name = "SC_RIGHT_ID", length = 64)
	public String getScRightId() {
		return this.scRightId;
	}

	public void setScRightId(String scRightId) {
		this.scRightId = scRightId;
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
