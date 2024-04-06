package edu.project.JobPortalApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.JobPortalApplication.entity.Employer;
import edu.project.JobPortalApplication.service.EmployerService;
import edu.project.JobPortalApplication.util.responseStructre;

@RestController
@RequestMapping("/employer")
public class EmployerController {

	@Autowired
	private EmployerService employerService;

	@PostMapping
	public ResponseEntity<responseStructre<Employer>> addEmployer(@Valid @RequestBody Employer employer) {
		return employerService.addEmployer(employer);
	}

	@PutMapping
	public ResponseEntity<responseStructre<Employer>> updateEmployer(@RequestBody Employer employer,
			@RequestParam long employerId) {
		return employerService.updateEmployer(employer, employerId);
	}

	@DeleteMapping
	public ResponseEntity<responseStructre<Employer>> deleteEmployer(@RequestParam long employerId) {
		return employerService.deleteEmployer(employerId);
	}

	@GetMapping
	public ResponseEntity<responseStructre<Employer>> getEmployerById(@RequestParam long employerId) {
		return employerService.getEmployerById(employerId);
	}

	@GetMapping("/getAll")
	public List<Employer> getallEmployer() {
		return employerService.getallEmployer();
	}
}
