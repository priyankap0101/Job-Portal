package edu.project.JobPortalApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.JobPortalApplication.entity.JobApplication;
import edu.project.JobPortalApplication.service.JobApplicationService;
import edu.project.JobPortalApplication.util.responseStructre;

@RestController
@RequestMapping("/apply")
public class JobApplicationController {

	@Autowired
	private JobApplicationService applicationServices;
	
	@PostMapping
	public ResponseEntity<responseStructre<JobApplication>> createJobApplication(@RequestParam long applicantId, @RequestParam  long jobId  )
	{
		return applicationServices.createJobApplication(applicantId, jobId);
	}

}
