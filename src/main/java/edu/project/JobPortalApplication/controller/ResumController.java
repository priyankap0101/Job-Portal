package edu.project.JobPortalApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.JobPortalApplication.dto.ResumeDto;
import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.service.ResumeService;
import edu.project.JobPortalApplication.util.responseStructre;

@RestController
@RequestMapping("/resume")
public class ResumController {

	@Autowired
	private ResumeService resumeService;

	@PostMapping
	public ResponseEntity<responseStructre<Resume>> saveResume(@RequestParam long applicantId,
			@RequestBody ResumeDto dto) {
		return resumeService.saveResume(applicantId, dto);
	}
	@GetMapping
	public ResponseEntity<responseStructre<Resume>> getResumeById(@RequestParam long resumeId)
	{
		return resumeService.getResumeById(resumeId);
	}

	
}
