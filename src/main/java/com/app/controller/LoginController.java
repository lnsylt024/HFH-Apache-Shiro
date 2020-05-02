package com.app.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.bean.User;

@RestController
public class LoginController {
	@RequestMapping("/login")
	public String login(User user) {
		
		//権限あり
		// http://localhost:8080/login?username=wsl&password=123456
		//http://localhost:8080/index
		
		//権限なし
		//http://localhost:8080/login?userName=zhangsan&password=123456
		//http://localhost:8080/index

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {

			subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "ERR1！";
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return "ERR2";
		}
		return "login success";
	}

	@RequiresRoles("admin")
	@RequiresPermissions("add")
	@RequestMapping("/index")
	public String index() {
		return "index!";
	}
}
