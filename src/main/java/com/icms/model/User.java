package com.icms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 327199294326750001L;

	@Id
	@GeneratedValue
	private Long uid;
	
	@Column(nullable = false)
	private Integer rid;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Integer type;

	@Column(nullable = false)
	private String salt;

	@Column(nullable = false)
	private String email;
	@Column
	private String mobile;
	@Column()
	private Integer deleted;
	@Column()
	private Integer enable;
	@Column()
	private String code;
	@Column()
	private Date created;
	@Column()
	private Date updated;
	@Column()
	private Integer status;
//	@Column
//	private long userConfigId;
	public User() {
		super();
	}

	public User(Long uid, String name, String password, Integer type) {
		super();
		this.name = name;
	}

	public User(Long uid, String name, String password, Integer type, String salt, String email, String mobile, Integer deleted, Integer enable, String code) {
		super();
		this.uid = uid;
		this.name = name;
		this.password = password;
		this.type = type;
		this.salt = salt;
		this.email = email;
		this.mobile = mobile;
		this.deleted = deleted;
		this.enable = enable;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Integer getRid() {
		return rid;
	}
	
	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	public long getUserConfigId() {
//		return userConfigId;
//	}
//
//	public void setUserConfigId(long userConfigId) {
//		this.userConfigId = userConfigId;
//	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", password=" + password + ", type=" + type + ", salt=" + salt + ", email=" + email + ", mobile=" + mobile + ", deleted=" + deleted + ", enable="
				+ enable + ", code=" + code + "]";
	}

}
