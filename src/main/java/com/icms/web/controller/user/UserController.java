package com.icms.web.controller.user;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.icms.model.User;
import com.icms.model.base.JsonObject;
import com.icms.service.remote.UserRemote;
import com.icms.util.EmailUtil;
import com.icms.web.dto.UserAccountSaveDTO;
import com.icms.web.dto.UserSaveResultDTO;
import com.icms.web.manager.LoginSessionManager;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserRemote userService;
	@Autowired LoginSessionManager loginSessionManager;
	
	@Value("${project.host}")
	private String host = "localhost";

	@RequestMapping(method = RequestMethod.GET)
	public User get() {
		Long uid = loginSessionManager.getSessionUserId();
		System.err.println("uid=" + uid);
		User u = userService.findUserById(uid);
		return u;
	}

//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody
//	public User create(User user) {
//		System.out.println(JsonUtil.getGsonInstance().toJson(user));
//		return user;
//	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject createUser(UserAccountSaveDTO user) {
		JsonObject json = new JsonObject();
		User save = new User();
		BeanUtils.copyProperties(user, save);
		save.setEnable(1);
		save.setDeleted(0);
		save.setType(0);
		String code = UUID.randomUUID().toString();
		save.setCode(code);
		save.setCreated(new Date());
		save.setUpdated(new Date());
		save.setStatus(0);
		save.setRid(2);
		try {
			User saved = userService.createUser(save);
			if (saved.getUid() != null) {
				json.setData(new UserSaveResultDTO(saved.getUid(), saved.getName(), saved.getEmail(), EmailUtil.getEmailIndexFromUserEmail(saved.getEmail())));
			} else {
				json.setStatus(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setStatus(1);
			json.setMessage("操作失敗! " + e.getMessage());
		}
		return json;
	}
	
	

	
	
}
