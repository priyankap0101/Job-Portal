package edu.project.JobPortalApplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantDto {

	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private long applicantPhoneNo;
}
