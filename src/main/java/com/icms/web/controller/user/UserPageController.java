package com.icms.web.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icms.model.User;
import com.icms.service.remote.UserRemote;
import com.icms.web.manager.LoginSessionManager;

@Controller
@RequestMapping("/user")
public class UserPageController {
	
	@Autowired private UserRemote userService;
	@Autowired LoginSessionManager loginSessionManager;
	
	@Value("${project.host}")
	private String host = "localhost";

//	@GetMapping("/index")
	@RequestMapping("/index")
	public String index(Map<String, Object> model, Integer pid) {
		
		Long uid = loginSessionManager.getSessionUserId();
		System.err.println("uid=" + uid);
		User u = userService.findUserById(uid);
		model.put("user", u);
		return "view/user/user";
	}
	
}
