package com.icms.web.controller.post;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icms.model.Post;
import com.icms.service.remote.PostRemote;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostRemote postService;
	
//	@GetMapping("")
	@RequestMapping("")
	public String index(Map<String, Object> model, Integer pid) {
		
		Post post = postService.getById(pid);
		
		model.put("post", post);
		
		return "view/post";
	}
}
