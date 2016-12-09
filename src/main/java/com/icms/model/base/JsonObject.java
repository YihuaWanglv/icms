package com.icms.model.base;

import java.io.Serializable;

public class JsonObject implements Serializable {

	private static final long serialVersionUID = 5636518400415762281L;
	private int status = 0;
	private String message = "";
	private Object data;

	public JsonObject() {
		super();
	}
	
//	public JsonObject(Object data) {
//		super();
//		this.data = data;
//	}

	public JsonObject(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public JsonObject(Builder b) {
		this.status = b.status;
//		this.data = b.data;	
		if (b.data != null) this.data = b.data;			
		this.message = b.message;
	}
	
	public static class Builder {
		private int status = 0;
		private Object data;
		private String message = "";
		public Builder(Integer status) {
			if (status != null) {
				this.status = status;
				
			}
		}
		public Builder errcode(int status) {
			this.status = status; 
			return this;
		}
		public Builder data(Object data) {this.data = data; return this;}
		public Builder errmsg(String message) {this.message = message; return this;}
		public JsonObject build() {return new JsonObject(this);}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
