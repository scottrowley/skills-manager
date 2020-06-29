package net.scottrowley.skillsmanager.repository;

import net.scottrowley.skillsmanager.model.SkillProficiency;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillProficiencyRepository extends PagingAndSortingRepository<SkillProficiency, Integer> {
}
