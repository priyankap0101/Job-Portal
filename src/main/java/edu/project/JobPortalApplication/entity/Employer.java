package edu.project.JobPortalApplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employerId;

	// @NotEmpty(message = "Invalid Employer Name")
	private String employerName;

	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "Invalid Email")
	// @NotEmpty(message = "Invalid Employer Email")
	private String employerEmail;

	@Pattern(regexp = "(?=.*[0-9])+(?=.*[a-z])+(?=.*[A-Z])+(?=.*[@#$%^&+=])+(?=\\S+$).{8,}", message = "minimum 8 characters mandatory(1 upperCase,1 lowerCase,1 specialCharacter,1 number)")
	// @NotEmpty(message = "Invalid Employer Password")
	private String employerPassword;

	@OneToMany(mappedBy = "employer")
	@JsonIgnore
	private List<Job> jobs;

}
