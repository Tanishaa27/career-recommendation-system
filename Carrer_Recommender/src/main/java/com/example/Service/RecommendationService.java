package com.example.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.RecommendationResult;
import com.example.Entity.SkillInput;
import com.example.Entity.User;
import com.example.Repository.RecommendationResultRepo;
import com.example.Repository.UserRepository;

@Service
public class RecommendationService {
	
@Autowired
private RecommendationResultRepo resultrepo;
@Autowired
private UserRepository userRepository;


public RecommendationResult recommendCarrer(SkillInput input) {
	
	String career;
	List<String> skills=input.getSkills();
	
	 
	
	if (skills == null || skills.isEmpty()) {
        throw new IllegalArgumentException("Skills list cannot be null or empty");
    }
	

	 // Convert all skills to for consistent matching
   List<String> skill = skills.stream()
   	    .map(s -> s.trim().toUpperCase())  // normalize case
   	    .toList();
	
  
   if (skill.contains("JAVA") && skill.contains("SPRING")) {
	    career = "Backend Developer";
	} else if (skill.contains("HTML") && skill.contains("CSS") && skill.contains("JAVASCRIPT")) {
	    career = "Frontend Developer";
	} else if (skill.contains("PYTHON") && skill.contains("PANDAS")) {
	    career = "Data Analyst";
	} else {
	    career = "Explore Full Stack or AI-related fields";
	}

	
	RecommendationResult result = new RecommendationResult();
    result.setRecommendedCareer(career);
    User user = userRepository.findById(input.getId())
    	    .orElseThrow(() -> new RuntimeException("User not found with ID: " + input.getId()));
    	result.setUser(user);
    result.setRecommendedOn(new java.sql.Date(System.currentTimeMillis()));


    return resultrepo.save(result);
	
}

public List<RecommendationResult> getAllRecommendations() {
    return resultrepo.findAll();
}

public List<RecommendationResult> getRecommendationsByUserId(Integer userId) {
    return resultrepo.findByUserId(userId);
}

}