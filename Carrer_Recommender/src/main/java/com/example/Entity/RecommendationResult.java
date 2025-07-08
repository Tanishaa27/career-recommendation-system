package com.example.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class RecommendationResult {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;

    private String recommendedCareer;
    private String reason;
    private String skillGap;
   
	@Column(name = "recommended_on")
    private Date recommendedOn;
	
	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecommendedCareer() {
		return recommendedCareer;
	}

	public void setRecommendedCareer(String recommendedCareer) {
		this.recommendedCareer = recommendedCareer;
	}

	public Date getRecommendedOn() {
		return recommendedOn;
	}

	public void setRecommendedOn(Date recommendedOn) {
		this.recommendedOn = recommendedOn;
	}

	public RecommendationResult() {
		
	}
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSkillGap() {
		return skillGap;
	}

	public void setSkillGap(String skillGap) {
		this.skillGap = skillGap;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
    
	
	
    
}
