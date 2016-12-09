package com.icms.web.manager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.icms.model.User;


@Component
public class LoginSessionManager {

	public User getSessionUser() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null && subject.getPrincipal() != null && subject.getPrincipal() instanceof User) {
			return (User) subject.getPrincipal();
		}
		return null;
	}
	
	public Long getSessionUserId() {
		Subject subject = SecurityUtils.getSubject();
		User user = null;
		if (subject != null && subject.getPrincipal() != null && subject.getPrincipal() instanceof User) {
			user = (User) subject.getPrincipal();
			if (user != null) {
				return user.getUid();
			}
		}
		return null;
	}
}
