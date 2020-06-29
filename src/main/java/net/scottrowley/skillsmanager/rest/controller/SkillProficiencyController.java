package net.scottrowley.skillsmanager.rest.controller;

import static net.scottrowley.skillsmanager.rest.RestSupport.dataMapFromId;

import net.scottrowley.skillsmanager.model.Person;
import net.scottrowley.skillsmanager.model.Proficiency;
import net.scottrowley.skillsmanager.model.Skill;
import net.scottrowley.skillsmanager.model.SkillProficiency;
import net.scottrowley.skillsmanager.repository.PersonRepository;
import net.scottrowley.skillsmanager.repository.SkillRepository;
import net.scottrowley.skillsmanager.rest.RestSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SkillProficiencyController {
  private final PersonRepository personRepository;
  private final SkillRepository skillRepository;
  private final RestSupport restSupport;

  public SkillProficiencyController(
      final PersonRepository personRepository,
      final SkillRepository skillRepository,
      final RestSupport restSupport
  ) {
    this.personRepository = personRepository;
    this.skillRepository = skillRepository;
    this.restSupport = restSupport;
  }

  @PostMapping("/person/{userId}/skill")
  public ResponseEntity<?> registerProficiency(
      @PathVariable final Integer userId,
      @RequestBody final RegisterProficiencyRequest request
  ) {
    Optional<Person> findPersonResult = personRepository.findById(userId);
    if (findPersonResult.isPresent()) {
      Optional<Skill> findSkillResult = skillRepository.findById(request.skillId);
      if (findSkillResult.isPresent()) {
        Person person = findPersonResult.get();
        Skill skill = findSkillResult.get();

        SkillProficiency proficiency = new SkillProficiency(request.proficiency);
        proficiency.setPerson(person);
        proficiency.setSkill(skill);

        person.addSkillProficiency(proficiency);
        Person updatedPerson = personRepository.save(person);

        return restSupport.singleEntity(updatedPerson);
      } else {
        return restSupport.badRequest(
            "SKILL_NOT_FOUND",
            "Skill could not be found",
            dataMapFromId(request.skillId)
        );
      }
    } else {
      return restSupport.notFound(dataMapFromId(userId));
    }
  }

  private static class RegisterProficiencyRequest {
    Integer skillId;
    Proficiency proficiency;

    public Integer getSkillId() {
      return skillId;
    }

    public void setSkillId(final Integer skillId) {
      this.skillId = skillId;
    }

    public Proficiency getProficiency() {
      return proficiency;
    }

    public void setProficiency(final Proficiency proficiency) {
      this.proficiency = proficiency;
    }
  }
}
