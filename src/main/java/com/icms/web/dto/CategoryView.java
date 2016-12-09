package com.icms.web.dto;

import com.icms.model.Category;

public class CategoryView extends Category {

	private static final long serialVersionUID = 4737721570901262949L;
	
	private boolean editting = false;

	public boolean isEditting() {
		return editting;
	}

	public void setEditting(boolean editting) {
		this.editting = editting;
	}
	
}
