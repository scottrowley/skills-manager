package net.scottrowley.skillsmanager.rest.controller;

import static net.scottrowley.skillsmanager.rest.RestSupport.dataMapFromId;

import net.scottrowley.skillsmanager.model.Skill;
import net.scottrowley.skillsmanager.repository.SkillRepository;
import net.scottrowley.skillsmanager.rest.RestSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
public class SkillController {
  private final SkillRepository repository;
  private final RestSupport restSupport;

  public SkillController(final RestSupport restSupport, final SkillRepository repository) {
    this.repository = repository;
    this.restSupport = restSupport;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findOne(@PathVariable final Integer id) {
    Optional<Skill> result = repository.findById(id);

    if (result.isPresent()) {
      return restSupport.singleEntity(result.get());
    } else {
      Map<String, String> data = dataMapFromId(id);
      return restSupport.notFound(data);
    }
  }

  @GetMapping()
  public ResponseEntity<?> findAll(final Pageable pageable) {
    Page<Skill> page = repository.findAll(pageable);

    return restSupport.pagedEntities(page);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody final Skill skill) {
    Skill savedSkill = repository.save(skill);

    return restSupport.created(savedSkill);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable final Integer id, @RequestBody final Skill body) {
    Optional<Skill> result = repository.findById(id);

    if(result.isPresent()) {
      Skill skill = result.get();
      skill.setName(body.getName());
      Skill savedSkill = repository.save(skill);

      return restSupport.singleEntity(savedSkill);
    } else {
      return restSupport.notFound(dataMapFromId(id));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable final Integer id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return restSupport.noContent();
    } else {
      return restSupport.notFound(dataMapFromId(id));
    }
  }
}
