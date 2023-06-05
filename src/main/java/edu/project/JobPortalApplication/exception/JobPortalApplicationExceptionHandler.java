package edu.project.JobPortalApplication.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import edu.project.JobPortalApplication.util.responseStructre;

@RestControllerAdvice
public class JobPortalApplicationExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, WebRequest request) {

		List<ObjectError> list = ex.getAllErrors(); // getting all error exception and storing in list of object type

		Map<String, String> errors = new HashMap<>();

		for (ObjectError error : list) // iterating all errors
		{
			String message = error.getDefaultMessage();

			String fieldName = ((FieldError) error).getField();

			errors.put(fieldName, message);// adding errors

		}

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);// passing error object
	}

	@ExceptionHandler
	public ResponseEntity<responseStructre<String>> employerNotFoundById(EmployerNotFoundById employerNotFoundById) {
		responseStructre<String> responseStructre = new responseStructre<>();
		responseStructre.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructre.setMessage(employerNotFoundById.getMessage());
		responseStructre.setData("Employer Not Found With Requested Id");

		return new ResponseEntity<responseStructre<String>>(responseStructre, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler // handling exception in proper way
	public ResponseEntity<responseStructre<String>> ApplicantNotFoundById(ApplicantNotFoundById applicantNotFoundById) {
		responseStructre<String> responseStructure = new responseStructre<>();

		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());

		responseStructure.setMessage(applicantNotFoundById.getMessage());

		responseStructure.setData("Applicant not Found With the requested ID!!");

		return new ResponseEntity<responseStructre<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler // handling exception in proper way
	public ResponseEntity<responseStructre<String>> JobNotFoundById(JobNotFoundById jobNotFoundById) {
		responseStructre<String> responseStructure = new responseStructre<>();

		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());

		responseStructure.setMessage(jobNotFoundById.getMessage());

		responseStructure.setData("Job not Found With the requested ID!!");

		return new ResponseEntity<responseStructre<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler // handling exception in proper way
	public ResponseEntity<responseStructre<String>> ProjectNotFoundById(ProjectNotFoundById projectNotFoundById) {
		responseStructre<String> responseStructure = new responseStructre<>();

		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());


		responseStructure.setData("Project not Found With the requested ID!!");

		return new ResponseEntity<responseStructre<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler // handling exception in proper way
	public ResponseEntity<responseStructre<String>> ResumeNotFoundById(ResumeNotFoundById resumeNotFoundById) {
		responseStructre<String> responseStructure = new responseStructre<>();

		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());


		responseStructure.setData("Resume not Found With the requested ID!!");

		return new ResponseEntity<responseStructre<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

}
