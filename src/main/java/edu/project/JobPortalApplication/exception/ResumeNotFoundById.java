package edu.project.JobPortalApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResumeNotFoundById extends RuntimeException {

	private String message;
}
