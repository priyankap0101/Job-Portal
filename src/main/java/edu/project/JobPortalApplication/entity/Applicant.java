package edu.project.JobPortalApplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private long applicantPhoneNo;
	private String applicantPassword;

	@OneToMany(mappedBy = "applicant")
	@JsonIgnore
	private List<JobApplication> jobApplications;

	@OneToOne
	@JoinColumn
	private Resume resume;

}
