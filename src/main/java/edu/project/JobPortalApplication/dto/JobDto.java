package edu.project.JobPortalApplication.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDto {

	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String company;
	private double salary;
	private String jobLocation;
}
