package com.icms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icms.mapper.CategoryMapper;
import com.icms.model.Category;
import com.icms.service.remote.CategoryRemote;




@Service
public class CategoryService implements CategoryRemote {

	@Autowired CategoryMapper categoryMapper;
	
	@Override
	public Category getById(Integer id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Category cate, Boolean isUpdateAll) {
		if (cate.getCid() == null) {
			categoryMapper.insert(cate);
		} else {
			if (isUpdateAll) {
				categoryMapper.updateByPrimaryKey(cate);				
			} else {
				categoryMapper.updateByPrimaryKeySelective(cate);
			}
		}
	}

	@Override
	public List<Category> getListByParentId(Integer id) {
		Category query = new Category();
		query.setPid(id);
		return categoryMapper.findByParams(query);
	}

	@Override
	public List<Category> findByParams(Category item) {
		return categoryMapper.findByParams(item);
	}

	@Override
	public void delete(Integer id) {
		categoryMapper.deleteByPrimaryKey(id);
	}

}
