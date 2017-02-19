package com.icms.service.remote;

import java.util.List;

import com.icms.model.Post;
import com.icms.model.TemplateHome;

public interface PostRemote {

	Post getById(Integer id);
	
	void save(Post point, Boolean isAllField);
	
	List<Post> getListByCategoryId(Integer id);

	void loadTemplateHomeDatas(TemplateHome th);
	
	public List<Post> loadRecentPosts(Integer cid, Integer count);

	void delete(Integer pid);
}
