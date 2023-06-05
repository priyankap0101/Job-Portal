package edu.project.JobPortalApplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.repositories.ResumeRepo;

@Repository
public class ResumeDao {

	@Autowired
	private ResumeRepo repo;

	public Resume saveResume(Resume resume) {
		return repo.save(resume);
	}
	public Optional<Resume> getResumeById(long resumeId)
	{
		return repo.findById(resumeId);
	}
	
	public void deleteResume(Resume optional) {
		repo.delete(optional);
	}

}
