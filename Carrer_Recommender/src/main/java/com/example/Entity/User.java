package com.example.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	
private Long id;
private String name;
private String email;
private String password;
@Column(length = 500)
private String about;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<SkillInput> skillInputs;

public User() {
}



public User(Long id, String name, String email, String password, String about, List<SkillInput> skillInputs) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.about = about;
	this.skillInputs = skillInputs;
}



public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


public String getAbout() {
	return about;
}


public void setAbout(String about) {
	this.about = about;
}


public List<SkillInput> getSkillInputs() {
	return skillInputs;
}


public void setSkillInputs(List<SkillInput> skillInputs) {
	this.skillInputs = skillInputs;
}







@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
			+ ", skillInputs=" + skillInputs + "]";
}








	
}
