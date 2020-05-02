package com.app.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.app.bean.Permissions;
import com.app.bean.Role;
import com.app.bean.User;
import com.app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Override
	public User getUserByName(String getMapByName) {

		return this.getMapByName(getMapByName);
	}
	
	private User getMapByName(String userName) {
		Permissions per1 = new Permissions("1","query");
		Permissions per2 = new Permissions("2","add");
		
		Set<Permissions> perSet = new HashSet<>();
		perSet.add(per1);
		perSet.add(per2);
		
		Role role = new Role("1","admin",perSet);
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(role);
		
		User user = new User("1","wsl","123456",roleSet);
		Map<String, User> map = new HashMap<>();
		map.put(user.getUsername(), user);
		
		Permissions per3 = new Permissions("3","query");
        
		Set<Permissions> per3Set = new HashSet<>();
        per3Set.add(per3);
        
        Role role1 = new Role("2","user",per3Set);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        
        User user1 = new User("2","zhangsan","123456",roleSet1);
        map.put(user1.getUsername(), user1);
		
		
		
		return map.get(userName);
	}

}
