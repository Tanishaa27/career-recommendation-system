package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.User;
import com.example.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepo;
	
	public User registredUser(User user) {
		return userrepo.save(user);
		
	}
	
	public List<User> getAllUsers(){
		return userrepo.findAll();
	}
	
	public User getUserById(Long id) {
		return userrepo.findById(id).orElse(null);
	}
	
	public void  updateUser(User user,Long id) {
		user.setId(id);
		 userrepo.save(user);
	}
	
	public void deleteUser(Long id) {
		userrepo.deleteById(id);
	}
	
}
