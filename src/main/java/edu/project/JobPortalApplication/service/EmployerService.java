package edu.project.JobPortalApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.JobPortalApplication.dao.EmployerDao;
import edu.project.JobPortalApplication.dao.JobDao;
import edu.project.JobPortalApplication.entity.Employer;
import edu.project.JobPortalApplication.entity.Job;
import edu.project.JobPortalApplication.exception.EmployerNotFoundById;
import edu.project.JobPortalApplication.util.responseStructre;

@Service
public class EmployerService {

	@Autowired
	private EmployerDao dao;

	@Autowired
	private JobDao jobDao;

	public ResponseEntity<responseStructre<Employer>> addEmployer(Employer employer) {
		Employer employer2 = dao.addEmployer(employer);

		responseStructre<Employer> responseStructre = new responseStructre<>();
		responseStructre.setStatusCode(HttpStatus.CREATED.value());
		responseStructre.setMessage("Employer Added Successfully");
		responseStructre.setData(employer2);
		return new ResponseEntity<responseStructre<Employer>>(responseStructre, HttpStatus.CREATED);
	}

	public ResponseEntity<responseStructre<Employer>> updateEmployer(Employer employer, long employerId) {

		Employer employer2 = dao.updateEmployer(employer, employerId);

		if (employer2 != null) {
			responseStructre<Employer> responseStructre = new responseStructre<>();
			responseStructre.setStatusCode(HttpStatus.OK.value());
			responseStructre.setMessage("Employer Updated Successfully");
			responseStructre.setData(employer2);
			return new ResponseEntity<responseStructre<Employer>>(responseStructre, HttpStatus.OK);
		} else {
			throw new EmployerNotFoundById("Employer Not Found With Requested Id....!!");
		}
	}

	public ResponseEntity<responseStructre<Employer>> deleteEmployer(long employerId) {
		Employer employer = dao.getEmployer(employerId);

		if (employer != null) {
			for (Job job : employer.getJobs()) {
				job.setEmployer(null);
				jobDao.addJob(job);
			}

			dao.deleteEmployer(employer);
			responseStructre<Employer> responseStructre = new responseStructre<>();
			responseStructre.setStatusCode(HttpStatus.OK.value());
			responseStructre.setMessage("Employer Deleted Successfully");
			responseStructre.setData(employer);
			return new ResponseEntity<responseStructre<Employer>>(responseStructre, HttpStatus.OK);
		}

		else {
			throw new EmployerNotFoundById("Employer Not Found With Requested Id....!!");
		}
	}
	public ResponseEntity<responseStructre<Employer>> getEmployerById(long employerId) {
		Employer employer = dao.getEmployer(employerId);
		if (employer != null) {
			responseStructre<Employer> responseStructre = new responseStructre<>();
			responseStructre.setStatusCode(HttpStatus.FOUND.value());
			responseStructre.setMessage("Employer Found Successfully");
			responseStructre.setData(employer);
			return new ResponseEntity<responseStructre<Employer>>(responseStructre, HttpStatus.FOUND);
		}else {
			throw new EmployerNotFoundById("Employer Not Found With Requested Id....!!");
		}
		
	}
	

	public List<Employer> getallEmployer() {

		return dao.getAllEmployer();

	}
}
