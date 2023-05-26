package edu.project.JobPortalApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployerNotFoundById extends RuntimeException {

	private String message;
}
