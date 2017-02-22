package com.icms.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.icms.model.Config;
import com.icms.model.TemplateHome;
import com.icms.service.remote.ConfigRemote;
import com.icms.util.JsonUtil;

@RestController
@RequestMapping("/admin/config/home")
public class AdminHomeConfigController {

	@Autowired
	private ConfigRemote configService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public TemplateHome get() {
		Config c = configService.getConfigOfHome();
		TemplateHome th = new TemplateHome();
		if (null != c) {
			th = JsonUtil.getGsonInstance().fromJson(c.getContent(), TemplateHome.class);			
		}
		return th;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void save(@RequestBody TemplateHome th) {
		
		Config c = configService.getConfigOfHome();
		c.setContent(JsonUtil.getGsonInstance().toJson(th));
		configService.save(c);
	}
}
