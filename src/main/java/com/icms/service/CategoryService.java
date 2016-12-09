package com.icms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icms.mapper.CategoryMapper;
import com.icms.model.Category;
import com.icms.service.remote.CategoryRemote;




@Service
public class CategoryService implements CategoryRemote {

	@Autowired CategoryMapper itemMapper;
	
	@Override
	public Category getById(Integer id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Category cate, Boolean isUpdateAll) {
		if (cate.getCid() == null) {
			itemMapper.insert(cate);
		} else {
			if (isUpdateAll) {
				itemMapper.updateByPrimaryKey(cate);				
			} else {
				itemMapper.updateByPrimaryKeySelective(cate);
			}
		}
	}

	@Override
	public List<Category> getListByParentId(Integer id) {
		Category query = new Category();
		query.setPid(id);
		return itemMapper.findByParams(query);
	}

	@Override
	public List<Category> findByParams(Category item) {
		return itemMapper.findByParams(item);
	}

}
