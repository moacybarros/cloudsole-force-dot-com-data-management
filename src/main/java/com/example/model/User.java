package com.example.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User 
{
	@JsonProperty(value="Name")
	private String Name;
	private String Id;
	private String UserRole;
	private String Profile;
	private String Email;
	private String Phone;
	private String Alias;
	private String Username;
	private String CommunityNickname;
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) 
	{
		Id = id;
	}
	
	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getCommunityNickname() {
		return CommunityNickname;
	}

	public void setCommunityNickname(String communityNickname) {
		CommunityNickname = communityNickname;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public String getProfile() {
		return Profile;
	}

	public void setProfile(String profile) {
		Profile = profile;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}
	
}
