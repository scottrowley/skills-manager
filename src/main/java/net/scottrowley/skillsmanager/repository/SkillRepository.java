package net.scottrowley.skillsmanager.repository;

import net.scottrowley.skillsmanager.model.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillRepository extends PagingAndSortingRepository<Skill, Integer> {
}
