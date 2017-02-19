package com.icms.web.dto;

import com.icms.model.Post;

public class PostView extends Post {

	private boolean editting = false;
	private String detail;
	private String categoryName;

	public boolean getEditting() {
		return editting;
	}

	public void setEditting(boolean editting) {
		this.editting = editting;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
