package com.app.bean;

public class Permissions {

	private String id;
	
	private String permissionsName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}

	public Permissions(String id, String permissionsName) {
		super();
		this.id = id;
		this.permissionsName = permissionsName;
	}
	
}
