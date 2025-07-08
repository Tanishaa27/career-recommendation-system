package com.example.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.Entity.User;
import com.example.Service.UserService;

@Controller
public class UserController {
@Autowired
private UserService usersrev;

@PostMapping("/user")
public ResponseEntity<User> register(@RequestBody User user) {
   // return ResponseEntity.ok(usersrev.registredUser(user));
    User  use=null;
	try {
		use=usersrev.registredUser(user);
		return ResponseEntity.of(Optional.of(user));
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}

@GetMapping("/user")
public ResponseEntity<List<User>> getAllUsers(){
//	return ResponseEntity.ok(usersrev.getAllUsers());
	List<User> list=usersrev.getAllUsers();
	if(list.size()<=0) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	return ResponseEntity.of(Optional.of(list));
}

@GetMapping("/user/{id}")
public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
	//return ResponseEntity.ok(usersrev.getUserById(id));
	User use=usersrev.getUserById(id);
	if(use==null) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	return ResponseEntity.of(Optional.of(use));
}

@PutMapping("/user/{id}")
public ResponseEntity<User> updateUser(@PathVariable("id") Long id,@RequestBody User user){
	try{
		this.usersrev.updateUser(user, id);
	
	return ResponseEntity.ok().build();
}catch(Exception e) {
	e.printStackTrace();
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	
}
}

@DeleteMapping("/user/{id}")
public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
	try {
		this.usersrev.deleteUser(id);
		return ResponseEntity.ok().build();
		
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
}



}
