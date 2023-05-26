package edu.project.JobPortalApplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.repositories.ResumeRepo;


@Repository
public class ResumeDao {

	@Autowired
	private ResumeRepo repo;
	
	public Resume saveResume(Resume resume)
	{
		return repo.save(resume);
	}

	public void deleteResume(Resume resume) {
		
		repo.delete(resume);
		
	}
}
