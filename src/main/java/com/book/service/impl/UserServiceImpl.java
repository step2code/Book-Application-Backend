package com.book.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.exception.UnauthorizedException;
import com.book.exception.UserAlreadyExistException;
import com.book.exception.UserNotFoundException;
import com.book.model.User;
import com.book.repository.UserRepository;
import com.book.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public User saveUser(User user) {
		Optional<User> savedUser = repo.findByUsernameIgnoreCase(user.getUsername());
		if(savedUser.isPresent()) {
			throw new UserAlreadyExistException("User with username "+user.getUsername()+" already exist");
		}
		return repo.save(user);
	}

	@Override
	public User checkUserByUsernameAndPassword(String username, String password) {
		Optional<User> storedUser = repo.findByUsernameIgnoreCase(username);
		if( !storedUser.isPresent() || !(storedUser.get().getPassword().equals(password)) ) {
			throw new UnauthorizedException("Please check you credentials");
		}
		return storedUser.get();
	}
 
	@Override
	public List<User> getAlluser() {
		List<User> allUsers = repo.findAll();
		if(allUsers.size() == 0) {
			throw new UserNotFoundException("No users found");
		}
		return allUsers;
	}

	@Override
	public User getUserByUsername(String username) {
		Optional<User> storedUser = repo.findByUsernameIgnoreCase(username);
		if(!storedUser.isPresent()) {
			throw new UserNotFoundException("User with username "+username+" not found");
		}
		return storedUser.get();
	}
	
	@Override
	public User updateUsername(Integer id,String username) {
		Optional<User> savedUser = repo.findById(id);
		if(!savedUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		if(!isUsernameAvailable(username)) {
			throw new UserAlreadyExistException("User with username "+username+" already exist");
		}
		savedUser.get().setUsername(username);
		return repo.save(savedUser.get());
	}
	
	@Override
	public User updatePassword(User user) {
		Optional<User> savedUser = repo.findById(user.getId());
		if(!savedUser.isPresent()) {
			throw new UserNotFoundException("User with username "+user.getUsername()+" not found");
		}
		savedUser.get().setPassword(user.getPassword());
		return repo.save(savedUser.get());
	}
	
	private boolean isUsernameAvailable(String username) {
		Optional<User> savedUser = repo.findByUsernameIgnoreCase(username);
		return !savedUser.isPresent();
	}

}
