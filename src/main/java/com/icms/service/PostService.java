package com.icms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icms.mapper.PostMapper;
import com.icms.model.Post;
import com.icms.service.remote.PostRemote;

@Service
public class PostService implements PostRemote {
	
	@Autowired PostMapper pointMapper;

	@Override
	public Post getById(Integer id) {
		return pointMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Post post, Boolean isAllField) {
		if (post.getPid() == null) {
			pointMapper.insertSelective(post);
		} else {
			if (isAllField) {
				pointMapper.updateByPrimaryKey(post);
			} else {
				pointMapper.updateByPrimaryKeySelective(post);
			}
		}
	}

	@Override
	public List<Post> getListByCategoryId(Integer id) {
		Post q = new Post();
		q.setCid(id);
		return pointMapper.findByParams(q);
	}

}
