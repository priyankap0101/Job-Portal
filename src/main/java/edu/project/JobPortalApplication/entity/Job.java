package edu.project.JobPortalApplication.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobId;

	// @NotEmpty(message = "Invalid Job Title")
	private String jobTitle;

	// @Size(min = 50, max = 100) //description size should be min 50
	private String jobDescription;

	private String company;

	private double salary;

	private String jobLocation;
	private LocalDateTime jobCreationDateTime;

	@ManyToOne
	@JoinColumn
	private Employer employer;

	@OneToMany(mappedBy = "job")
	private List<JobApplication> applications;
}
