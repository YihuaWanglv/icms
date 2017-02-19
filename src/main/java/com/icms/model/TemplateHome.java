package com.icms.model;

import java.io.Serializable;
import java.util.List;

public class TemplateHome implements Serializable {

	private static final long serialVersionUID = -862088404913732000L;
	
	private int isOnWebSiteHeader=1;
	private String webSiteName;
	private String webSiteDetail;
	private int isOnHeadline=1;
	private int headlineCategoryId;
	private Integer headlinePostId;
	private Post headlinePost;
	private List<TemplateItem> templateItems;
	
	
	public int getIsOnWebSiteHeader() {
		return isOnWebSiteHeader;
	}
	public void setIsOnWebSiteHeader(int isOnWebSiteHeader) {
		this.isOnWebSiteHeader = isOnWebSiteHeader;
	}
	public String getWebSiteName() {
		return webSiteName;
	}
	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}
	public String getWebSiteDetail() {
		return webSiteDetail;
	}
	public void setWebSiteDetail(String webSiteDetail) {
		this.webSiteDetail = webSiteDetail;
	}
	public int getIsOnHeadline() {
		return isOnHeadline;
	}
	public void setIsOnHeadline(int isOnHeadline) {
		this.isOnHeadline = isOnHeadline;
	}
	public int getHeadlineCategoryId() {
		return headlineCategoryId;
	}
	public void setHeadlineCategoryId(int headlineCategoryId) {
		this.headlineCategoryId = headlineCategoryId;
	}
	public List<TemplateItem> getTemplateItems() {
		return templateItems;
	}
	public void setTemplateItems(List<TemplateItem> templateItems) {
		this.templateItems = templateItems;
	}
	public Integer getHeadlinePostId() {
		return headlinePostId;
	}
	public void setHeadlinePostId(Integer headlinePostId) {
		this.headlinePostId = headlinePostId;
	}
	public Post getHeadlinePost() {
		return headlinePost;
	}
	public void setHeadlinePost(Post headlinePost) {
		this.headlinePost = headlinePost;
	}
	
}
