package net.scottrowley.skillsmanager.repository;

import net.scottrowley.skillsmanager.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
}
