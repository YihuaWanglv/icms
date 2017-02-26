package com.icms.web.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -8236563306402600960L;

	private Long uid;
	private Integer rid;
	private String name;
	private String password;
	private Integer type;
	private String salt;
	private String email;
	private String mobile;
	private Integer deleted;
	private Integer enable;
	private String code;
	
	public UserDto() {
		super();
	}

	public UserDto(Long uid, String name, String password, Integer type) {
		super();
		this.name = name;
	}

	public UserDto(Long uid, String name, String password, Integer type, String salt, String email, String mobile, Integer deleted, Integer enable, String code) {
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

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", password=" + password + ", type=" + type + ", salt=" + salt + ", email=" + email + ", mobile=" + mobile + ", deleted=" + deleted + ", enable="
				+ enable + ", code=" + code + "]";
	}
}
