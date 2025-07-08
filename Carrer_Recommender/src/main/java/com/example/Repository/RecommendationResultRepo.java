package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.RecommendationResult;


public interface RecommendationResultRepo extends JpaRepository<RecommendationResult,Long>{

	List<RecommendationResult> findByUserId(Integer userId);
	
	
}
