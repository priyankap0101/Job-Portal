package edu.project.JobPortalApplication.controller;

import java.util.List;

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

import edu.project.JobPortalApplication.dto.ApplicantDto;
import edu.project.JobPortalApplication.entity.Applicant;
import edu.project.JobPortalApplication.service.ApplicantService;
import edu.project.JobPortalApplication.util.responseStructre;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;
	
	@PostMapping
	public ResponseEntity<responseStructre<ApplicantDto>> saveApplicant(@RequestBody Applicant applicant)
	{
		return applicantService.saveApplicant(applicant);
	}
	@GetMapping
	public ResponseEntity<responseStructre<ApplicantDto>> getApplicantById(@RequestParam int applicantId)
	{
		return applicantService.getApplicantById(applicantId);
		
	}
	@PutMapping
	public ResponseEntity<responseStructre<ApplicantDto>> updateApplicant(@RequestParam int applicantId,@RequestBody  Applicant applicant)
	{
		return applicantService.updateApplicant(applicantId, applicant);
	}
	@DeleteMapping
	public ResponseEntity<responseStructre<ApplicantDto>> deleteApplicantById(@RequestParam int applicantId)
	{
		return applicantService.deleteApplicant(applicantId);
	}

	@GetMapping("/getAll")
	public List<Applicant> getAllApplicant()
	{
		return applicantService.getAllApplicant();
	}
}
