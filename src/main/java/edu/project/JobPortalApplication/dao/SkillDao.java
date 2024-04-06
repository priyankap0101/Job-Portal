package edu.project.JobPortalApplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.entity.Skill;
import edu.project.JobPortalApplication.repositories.SkillRepo;

@Repository
public class SkillDao {

	@Autowired
	private SkillRepo repo;

	public Skill saveSkill(Skill skill) {
		return repo.save(skill);
	}

	public Skill getSkillByName(String skillName) {
		Optional<Skill> optional = repo.getSkillByName(skillName);

		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}
}
