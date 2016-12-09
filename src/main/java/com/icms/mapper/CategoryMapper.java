package com.icms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icms.model.Category;
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer iid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer iid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> findByParams(Category record);
}