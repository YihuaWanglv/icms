package com.icms.web.controller.admin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.icms.model.Category;
import com.icms.model.Post;
import com.icms.service.remote.CategoryRemote;
import com.icms.service.remote.PostRemote;
import com.icms.web.dto.PostView;
import com.icms.web.manager.LoginSessionManager;



@RestController
@RequestMapping("/admin/post")
public class AdminPostController {
	
	@Autowired LoginSessionManager loginSessionManager;
	@Autowired PostRemote postService;
	@Autowired CategoryRemote categoryService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<PostView> listPost(Integer cid) {
		List<PostView> pvs = new ArrayList<PostView>();
		List<Post> points = postService.getListByCategoryId(cid);
		for (Post p : points) {
			PostView pv = new PostView();
			BeanUtils.copyProperties(p, pv);
			pvs.add(pv);
		}
		return pvs;
	}
	
	@RequestMapping(value = "/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public PostView getPost(@PathVariable Integer pid) {
		PostView pv = new PostView();
		Post p = postService.getById(pid);
		BeanUtils.copyProperties(p, pv);
		if (p != null) {
			Category c = categoryService.getById(p.getCid());
			pv.setCategoryName(c.getName());
		}
		return pv;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Post addPost(@RequestBody Post post) {
		if (post.getCid() == null) post.setCid(2);
//		if (loginSessionManager == null) {
//			System.err.println("loginSessionManager is null---------------");
//			post.setCreator(1);
//			post.setEditor(1);
//		} else {
//			post.setCreator(loginSessionManager.getSessionUserId().intValue());
//			post.setEditor(loginSessionManager.getSessionUserId().intValue());
//		}
		post.setCreator(1);
		post.setEditor(1);
		post.setCreated(new Date());
		post.setUpdated(new Date());
		post.setDeleted(0);
		post.setStatus(0);
		post.setType(0);
		postService.save(post, null);
		return post;
	}
	
	@RequestMapping(value = "/{pid}", method = RequestMethod.PUT)
	@ResponseBody
	public Post updatePost(@RequestBody Post post, @PathVariable Integer pid) {
		postService.save(post, false);
		return post;
	}
	
	@RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer pid) {
		postService.delete(pid);
	}

}
