package com.icms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.icms.mapper.PostMapper;
import com.icms.model.Post;
import com.icms.model.TemplateHome;
import com.icms.model.TemplateItem;
import com.icms.repository.PostRepository;
import com.icms.service.remote.PostRemote;

@Service
public class PostService implements PostRemote {
	
	@Autowired PostMapper postMapper;
	@Autowired PostRepository postRepository;

	@Override
	public Post getById(Integer id) {
		return postMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Post post, Boolean isAllField) {
		if (post.getPid() == null) {
			postMapper.insertSelective(post);
		} else {
			if (isAllField) {
				postMapper.updateByPrimaryKey(post);
			} else {
				postMapper.updateByPrimaryKeySelective(post);
			}
		}
	}

	@Override
	public List<Post> getListByCategoryId(Integer id) {
		Post q = new Post();
		q.setCid(id);
		return postMapper.findByParams(q);
	}

	@Override
	public void loadTemplateHomeDatas(TemplateHome th) {
		Integer headlinePostId = th.getHeadlinePostId();
		if (null != headlinePostId && headlinePostId > 0) {
			th.setHeadlinePost(postMapper.selectByPrimaryKey(headlinePostId));
		}
		List<TemplateItem> tis = th.getTemplateItems();
		
//		for (TemplateItem ti : tis) {
//			int staticSize = null == ti.getStaticPosts() ? 0 : ti.getStaticPosts().size();
//			List<Post> posts = this.loadRecentPosts(ti.getCategoryId(), ti.getCount() - staticSize);
//		}
		for (Iterator<TemplateItem> iterator = tis.iterator(); iterator.hasNext();) {
			TemplateItem ti = (TemplateItem) iterator.next();
			int staticSize = null == ti.getStaticPosts() ? 0 : ti.getStaticPosts().size();
			List<Post> posts = this.loadRecentPosts(ti.getCategoryId(), ti.getCount() - staticSize);
			posts.addAll(getByIds(ti.getStaticPosts()));
			ti.setPosts(posts);
		}
	}

	private List<Post> getByIds(List<Integer> staticPosts) {
		
		return (null == staticPosts || staticPosts.size() == 0) ? new ArrayList<Post>() : postMapper.findByIds(staticPosts);
	}

	public List<Post> loadRecentPosts(Integer cid, Integer count) {
		
		return postMapper.loadRecentPosts(cid, count);
	}

	@Override
	public void delete(Integer pid) {
		postMapper.deleteByPrimaryKey(pid);
	}

	@Override
	public Page<Post> loadRecentPostsByPage(Integer cid, Integer page) {
		Assert.notNull(page, "param[page] must not be null.");
		Assert.notNull(cid, "param[cid] must not be null.");
		Sort sort = new Sort(Direction.DESC, "created");
		Pageable pageable = new PageRequest(page-1, 6, sort);
		Page<Post> pages = postRepository.findByCid(cid, pageable);
		return pages;
	}

}
