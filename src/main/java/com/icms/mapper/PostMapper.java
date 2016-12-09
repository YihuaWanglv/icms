package com.icms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icms.model.Post;

@Mapper
public interface PostMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    
    List<Post> findByParams(Post point);
}