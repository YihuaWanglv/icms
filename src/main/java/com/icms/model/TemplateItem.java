package com.icms.model;

import java.io.Serializable;
import java.util.List;

public class TemplateItem implements Serializable {

	private static final long serialVersionUID = 220242648996526763L;
	
	private int tiid;
	private String name;
	private int categoryId;
	private int templateId;
	private int indexs;
	private int count = 6;
	private List<Integer> staticPosts;
	private List<Post> posts;
	
	public int getTiid() {
		return tiid;
	}
	public void setTiid(int tiid) {
		this.tiid = tiid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public List<Integer> getStaticPosts() {
		return staticPosts;
	}
	public void setStaticPosts(List<Integer> staticPosts) {
		this.staticPosts = staticPosts;
	}
	public int getIndexs() {
		return indexs;
	}
	public void setIndexs(int indexs) {
		this.indexs = indexs;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
