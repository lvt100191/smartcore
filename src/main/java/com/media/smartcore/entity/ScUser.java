package com.media.smartcore.entity;
// Generated Aug 21, 2017 11:15:42 AM by Hibernate Tools 3.5.0.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SC_USER")
public class ScUser extends BaseEntity implements java.io.Serializable {

	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private String email;
	private String mobile;
	private Integer status;
	private String passwordLog;
	private Long ownerId;
	private Long invalidLoginCount;
	private Date createDate;
	private String srcId;
	private Long type;
	private String facebookId;
	private Long gender;
	private String skypeId;
	private String googleId;
	private String address;
	private Date birthday;
	private String token;
	private String note;
	private Date lastUpdate;
	private Date lastLogin;
	private Long creatorId;
	private String scId;

	public ScUser() {
	}

	public ScUser(String id, String username) {
		this.id = id;
		this.username = username;
	}

	public ScUser(String id, String username, String password, String lastName, String firstName, String email,
			String mobile, Integer status, String passwordLog, Long ownerId, Long invalidLoginCount,
			Date createDate, String srcId, Long type, String facebookId, Long gender, String skypeId,
			String googleId, String address, Date birthday, String token, String note, Date lastUpdate,
			Date lastLogin, Long creatorId, String scId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
		this.passwordLog = passwordLog;
		this.ownerId = ownerId;
		this.invalidLoginCount = invalidLoginCount;
		this.createDate = createDate;
		this.srcId = srcId;
		this.type = type;
		this.facebookId = facebookId;
		this.gender = gender;
		this.skypeId = skypeId;
		this.googleId = googleId;
		this.address = address;
		this.birthday = birthday;
		this.token = token;
		this.note = note;
		this.lastUpdate = lastUpdate;
		this.lastLogin = lastLogin;
		this.creatorId = creatorId;
		this.scId = scId;
	}

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
	public String getId() {
		return this.id;
	}

	@Column(name = "USERNAME", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 800)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "LAST_NAME", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "FIRST_NAME", length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "EMAIL", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "STATUS", precision = 2, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "PASSWORD_LOG", length = 1000)
	public String getPasswordLog() {
		return this.passwordLog;
	}

	public void setPasswordLog(String passwordLog) {
		this.passwordLog = passwordLog;
	}

	@Column(name = "OWNER_ID", precision = 22, scale = 0)
	public Long getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	@Column(name = "INVALID_LOGIN_COUNT", precision = 22, scale = 0)
	public Long getInvalidLoginCount() {
		return this.invalidLoginCount;
	}

	public void setInvalidLoginCount(Long invalidLoginCount) {
		this.invalidLoginCount = invalidLoginCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "SRC_ID", length = 500)
	public String getSrcId() {
		return this.srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	@Column(name = "TYPE", precision = 22, scale = 0)
	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "FACEBOOK_ID", length = 100)
	public String getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	@Column(name = "GENDER", precision = 22, scale = 0)
	public Long getGender() {
		return this.gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	@Column(name = "SKYPE_ID", length = 100)
	public String getSkypeId() {
		return this.skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	@Column(name = "GOOGLE_ID", length = 100)
	public String getGoogleId() {
		return this.googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	@Column(name = "ADDRESS", length = 1000)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY", length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "TOKEN", length = 200)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "NOTE", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE", length = 7)
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN", length = 7)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "CREATOR_ID", precision = 22, scale = 0)
	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "SC_ID", length = 255)
	public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

}
