package edu.project.JobPortalApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobNotFoundById extends RuntimeException {

	private String message;
}
