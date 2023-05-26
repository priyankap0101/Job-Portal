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

import edu.project.JobPortalApplication.dto.JobDto;
import edu.project.JobPortalApplication.entity.Job;
import edu.project.JobPortalApplication.service.JobService;
import edu.project.JobPortalApplication.util.responseStructre;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@PostMapping
	public ResponseEntity<responseStructre<Job>> addJob(@Valid @RequestBody JobDto jobDto,
			@Valid @RequestParam long employerId) {
		return jobService.addJob(jobDto, employerId);

	}

	@GetMapping
	public ResponseEntity<responseStructre<Job>> getJob(@RequestParam long jobId) {
		return jobService.getJob(jobId);
	}

	@PutMapping
	public ResponseEntity<responseStructre<JobDto>> updateJob(@RequestBody Job job, @RequestParam long id) {
		return jobService.updateJob(job, id);
	}

	@DeleteMapping
	public ResponseEntity<responseStructre<JobDto>> deleteJob(@RequestParam long jobId) {

		return jobService.deleteJob(jobId);
	}

	@GetMapping("getAllJob")
	public List<Job> getAllJob() {
		return jobService.getAllJob();
	}
}
