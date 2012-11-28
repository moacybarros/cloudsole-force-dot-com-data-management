package com.example.service;
import java.util.List;
import com.example.model.User;
import com.force.api.Identity;

public interface IdentityService 
{
	public Identity getLoggedInUser();
	public List<User> getAllUsers();
	public void deleteUser(String id);

}
