package edu.project.JobPortalApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.JobPortalApplication.dto.ProjectDto;
import edu.project.JobPortalApplication.entity.Project;
import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.service.ProjectService;
import edu.project.JobPortalApplication.util.responseStructre;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping
	public ResponseEntity<responseStructre<Resume>> saveProject(@RequestParam long applicantId,
			@RequestBody ProjectDto projectDto , long resumeId) {
		return projectService.saveProjects(applicantId, projectDto,resumeId);
	}

	@DeleteMapping
	public ResponseEntity<responseStructre<Project>> deleteProject(@RequestParam long projectId,@RequestParam long applicantId) {
		return projectService.deleteProject(projectId, applicantId);
	}
	@PutMapping
	public ResponseEntity<responseStructre<Project>> updateProject(@RequestBody ProjectDto projectdto, @RequestParam long projectId)  {
		
		return projectService.updateProject( projectdto, projectId);
	}

}
