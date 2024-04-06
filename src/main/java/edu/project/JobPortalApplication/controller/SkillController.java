package edu.project.JobPortalApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.service.SkillService;
import edu.project.JobPortalApplication.util.responseStructre;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillService services;

	@PostMapping
	public ResponseEntity<responseStructre<Resume>> saveSkill(@RequestParam long applicantId,
			@RequestParam String[] skills) {
		return services.saveSkill(applicantId, skills);
	}
}
