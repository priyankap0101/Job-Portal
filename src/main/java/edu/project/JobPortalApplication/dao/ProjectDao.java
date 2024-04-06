package edu.project.JobPortalApplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.entity.Project;
import edu.project.JobPortalApplication.repositories.ProjectRepo;


@Repository
public class ProjectDao {

	@Autowired
	private ProjectRepo projectRepo;
	
	public Project saveProject(Project project)
	{
		return projectRepo.save(project);
	}

	public void deleteProject(Project project) {
	
		projectRepo.delete(project);
		
	}
	public Project updateProject(Project project, long projectId)
	{
		 Optional<Project> optional =projectRepo.findById(projectId);
		 
		 if(optional.isEmpty())
		 {
			 return null;
		 }
		 else {
			 projectRepo.save(project);
			 
			 return optional.get();
		 }
	}
	public Optional<Project> getProjectById(long projectId)
	{
		 return projectRepo.findById(projectId);
		 
	}
}
