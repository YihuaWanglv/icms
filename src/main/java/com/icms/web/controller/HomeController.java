package com.icms.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icms.model.Config;
import com.icms.model.TemplateHome;
import com.icms.service.remote.ConfigRemote;
import com.icms.service.remote.PostRemote;

@Controller
public class HomeController {

	@Value("${project.host}")
	private String host = "localhost";

	@Autowired
	private ConfigRemote configService;
	@Autowired
	private PostRemote postService;
	private static final Gson g = new GsonBuilder().serializeNulls().create();

//	@GetMapping("/")
	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		Config c = configService.getConfigOfHome();
		TemplateHome th = g.fromJson(c.getContent(), TemplateHome.class);
		
		postService.loadTemplateHomeDatas(th);
		System.err.println(g.toJson(th));
//		model.put("headlinePost", p);
		model.put("home", th);
		
		return "home";
	}

//	@GetMapping("/json")
//	@ResponseBody
//	public JsonObject welcome() {
//		JsonObject jb = new JsonObject();
//		jb.setStatus(1);
//		return jb;
//	}

}
