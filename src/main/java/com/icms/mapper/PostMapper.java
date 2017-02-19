package com.icms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    
    List<Post> findByIds(List<Integer> ids);
    
    List<Post> loadRecentPosts(@Param("cid")Integer cid, @Param("count")Integer count);
}