package edu.project.JobPortalApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectNotFoundById extends RuntimeException {

	private String message;
}
