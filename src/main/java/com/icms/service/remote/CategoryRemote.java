package com.icms.service.remote;

import java.util.List;

import com.icms.model.Category;


public interface CategoryRemote {

	Category getById(Integer id);
	
	void save(Category item, Boolean isUpdateAll);
	
	List<Category> getListByParentId(Integer id);
	
	List<Category> findByParams(Category item);
}
