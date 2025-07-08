package com.example.Entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class SkillInput {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	  @ElementCollection
	private List<String> skills;
	private String interestArea;
	@NotNull(message = "Please select whether you have worked on a project.")
	private Boolean hasProject;
    private String projectDescription;
    private String projectType;  // e.g. Web, Android, ML
    private String educationLevel; // e.g. 10th, 12th, BCA, BTech
    private int experienceYears;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public SkillInput() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public String getInterestArea() {
		return interestArea;
	}
	public void setInterestArea(String interestArea) {
		this.interestArea = interestArea;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getHasProject() {
	    return hasProject;
	}

	public void setHasProject(Boolean hasProject) {
	    this.hasProject = hasProject;
	}
	
	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	
	
	
	

}
