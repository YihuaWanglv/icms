package com.icms.service.remote;

import java.util.List;

import com.icms.model.Post;

public interface PostRemote {

	Post getById(Integer id);
	
	void save(Post point, Boolean isAllField);
	
	List<Post> getListByCategoryId(Integer id);
}
