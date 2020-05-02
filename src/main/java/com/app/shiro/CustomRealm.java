package com.app.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.bean.Permissions;
import com.app.bean.Role;
import com.app.bean.User;
import com.app.service.LoginService;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private LoginService service;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String name = (String) principals.getPrimaryPrincipal();

		User user = service.getUserByName(name);

		SimpleAuthorizationInfo simpleA = new SimpleAuthorizationInfo();

		for (Role role : user.getRoles()) {
			simpleA.addRole(role.getRoleName());
			for (Permissions per : role.getPermissions()) {
				simpleA.addStringPermission(per.getPermissionsName());
			}
		}

		return simpleA;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		if (token.getPrincipal() == null) {
			return null;
		}
		String name = token.getPrincipal().toString();
		User user = service.getUserByName(name);
		if (user == null) {
			return null;
		} else {
			SimpleAuthenticationInfo simpleA = new SimpleAuthenticationInfo(name, user.getPassword().toString(),
					getName());
			return simpleA;
		}
	}

}
