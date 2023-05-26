package edu.project.JobPortalApplication.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.JobPortalApplication.dao.ApplicantDao;
import edu.project.JobPortalApplication.dao.ProjectDao;
import edu.project.JobPortalApplication.dao.ResumeDao;
import edu.project.JobPortalApplication.dto.ProjectDto;
import edu.project.JobPortalApplication.entity.Applicant;
import edu.project.JobPortalApplication.entity.Project;
import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.util.responseStructre;

@Service
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ResumeDao resumeDao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<responseStructre<Resume>> saveProjects(long applicantId, ProjectDto projectdto) {

		Applicant applicant = applicantDao.getApplicant(applicantId);

		if (applicant != null) {
			Resume resume = applicant.getResume();

			if (resume != null) {
				List<Project> exProjects = resume.getProjects();
				Project project=this.mapper.map(projectdto, Project.class);
				projectDao.saveProject(project);
				exProjects.add(project);

				resumeDao.saveResume(resume);

				responseStructre<Resume> responseStructre = new responseStructre<>();

				responseStructre.setStatusCode(HttpStatus.CREATED.value());
				responseStructre.setMessage("Project Added Successfully");
				responseStructre.setData(resume);

				return new ResponseEntity<responseStructre<Resume>>(responseStructre, HttpStatus.CREATED);

			} else {

				return null;
			}
		} else {
			return null;
		}

	}

	public ResponseEntity<responseStructre<Project>> deleteProject(Project project) {
		
		 	projectDao.deleteProject(project);
		 
			responseStructre<Project> responseStructre = new responseStructre<>();

			responseStructre.setStatusCode(HttpStatus.OK.value());
			responseStructre.setMessage("Project Deleted Successfully");
			responseStructre.setData(project);

			return new ResponseEntity<responseStructre<Project>>(responseStructre, HttpStatus.OK);
		
	}
}
