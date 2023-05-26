package edu.project.JobPortalApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.JobPortalApplication.dao.ApplicantDao;
import edu.project.JobPortalApplication.dao.ResumeDao;
import edu.project.JobPortalApplication.dao.SkillDao;
import edu.project.JobPortalApplication.dto.SkillDto;
import edu.project.JobPortalApplication.entity.Applicant;
import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.entity.Skill;
import edu.project.JobPortalApplication.util.responseStructre;

@Service
public class SkillService {

	@Autowired
	private SkillDao dao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ResumeDao resumeDao;

	public ResponseEntity<responseStructre<Resume>> saveSkill(long applicantId, String[] Skills) {

		Applicant applicant = applicantDao.getApplicant(applicantId);

		if (applicant != null) {
			Resume resume = applicant.getResume();

			if (resume != null) {
				/*
				 * 1 iterate over string arrays skills that is received 2 check if the skill is
				 * present with the matching name 3 if present add the exixting skill to the
				 * resume 4 else create new skill
				 * 
				 * previously we had one to many relationship from resume to skill we will
				 * change to many to many
				 * 
				 * 
				 * for
				 */

				for (String Skill : Skills) {
					Skill existingSkill = dao.getSkillByName(Skill);

					if (existingSkill != null) {

						if (!resume.getSkills().contains(existingSkill)) {

							resume.getSkills().add(existingSkill);// adding skiils into existing one
						}
					} else {
						Skill newskill = new Skill();
						newskill.setSkillName(Skill);

						dao.saveSkill(newskill);

						resume.getSkills().add(newskill);
					}

				}

				resume = resumeDao.saveResume(resume);

				responseStructre<Resume> responseStructre = new responseStructre<>();

				responseStructre.setStatusCode(HttpStatus.CREATED.value());
				responseStructre.setMessage("Resume Added Successfully");
				responseStructre.setData(resume);

				return new ResponseEntity<responseStructre<Resume>>(responseStructre, HttpStatus.CREATED);
			} else {
				// throw new ResumeNotFoundByIdException("Failed To ADD Akills");
				return null;
			}
		} else {
			// throw new ResumeNotFoundByIdException("Failed To ADD Akills");
			return null;
		}
	}
}
