package com.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.book.model.User;

@Service
public interface IUserService {

	public User saveUser(User user);
	
	public User checkUserByUsernameAndPassword(String username,String password);
	
	public List<User> getAlluser();
	
	public User getUserByUsername(String username);
	
	public User updateUsername(Integer id,String username);
	
	public User updatePassword(User user);
}
