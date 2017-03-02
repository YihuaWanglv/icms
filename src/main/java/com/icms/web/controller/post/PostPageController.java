package com.icms.web.controller.post;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icms.model.Category;
import com.icms.model.Post;
import com.icms.service.remote.CategoryRemote;
import com.icms.service.remote.PostRemote;

@Controller
@RequestMapping("/post")
public class PostPageController {
	
	@Autowired private PostRemote postService;
	@Autowired private CategoryRemote categoryService;
	
	@Value("${project.host}")
	private String host = "localhost";
	
	@GetMapping("")
	public String index(Map<String, Object> model, Integer pid) {
		if (null == pid || pid < 1) {
			return "blank";
		}
		Post post = postService.getById(pid);
		Category c = categoryService.getById(post.getCid());
		model.put("category", c);
		model.put("post", post);
		
		return "view/post";
	}

	@GetMapping("/list")
	public String index(Map<String, Object> model, Integer cid, Integer page) {
		if (null == page || page < 1) {
			page = 1;
		}
		if (null == cid || cid <= 0) {
			return "blank";
		}
		Category c = categoryService.getById(cid);
		Page<Post> pages = postService.loadRecentPostsByPage(cid, page);
		model.put("pages", pages);
		model.put("totalPage", pages.getTotalPages());
		model.put("page", page);
		model.put("cid", cid);
		model.put("previous", page-1);
		model.put("next", page+1);
		model.put("category", c);
		return "view/post-list";
	}
	
}
