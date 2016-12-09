package com.icms.web.controller.admin;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.icms.model.Post;
import com.icms.service.remote.CategoryRemote;
import com.icms.service.remote.PostRemote;
import com.icms.web.dto.PostView;



@RestController
@RequestMapping("/admin/post")
public class AdminPostController {
	
	@Autowired PostRemote postService;
	@Autowired CategoryRemote itemService;
	
	
	@RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
	@ResponseBody
	public List<PostView> listPoint(@PathVariable Integer cid) {
		List<PostView> pvs = new ArrayList<PostView>();
		List<Post> points = postService.getListByCategoryId(cid);
		for (Post p : points) {
			PostView pv = new PostView();
			BeanUtils.copyProperties(p, pv);
			pvs.add(pv);
		}
		return pvs;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Post addPoint(@RequestBody Post post) {
		postService.save(post, null);
		return post;
	}
	
	@RequestMapping(value = "/point/{pid}", method = RequestMethod.PUT)
	@ResponseBody
	public Post updatePost(@RequestBody Post post, @PathVariable Integer pid) {
		postService.save(post, false);
		return post;
	}

}
