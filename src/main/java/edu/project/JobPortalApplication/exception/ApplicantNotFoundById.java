package edu.project.JobPortalApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicantNotFoundById extends RuntimeException {

	private String message;
}
