package net.scottrowley.skillsmanager.rest.controller;

import static net.scottrowley.skillsmanager.rest.RestSupport.dataMapFromId;

import net.scottrowley.skillsmanager.model.Person;
import net.scottrowley.skillsmanager.model.Proficiency;
import net.scottrowley.skillsmanager.repository.PersonRepository;
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

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
  private final PersonRepository repository;
  private final RestSupport restSupport;

  public PersonController(final RestSupport restSupport, final PersonRepository repository) {
    this.repository = repository;
    this.restSupport = restSupport;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findOne(@PathVariable final Integer id) {
    Optional<Person> result = repository.findById(id);

    if (result.isPresent()) {
      return restSupport.singleEntity(result.get());
    } else {
      Map<String, String> data = dataMapFromId(id);
      return restSupport.notFound(data);
    }
  }

  @GetMapping()
  public ResponseEntity<?> findAll(final Pageable pageable) {
    Page<Person> page = repository.findAll(pageable);

    return restSupport.pagedEntities(page);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody final Person person) {
    Person savedPerson = repository.save(person);

    return restSupport.created(savedPerson);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable final Integer id, @RequestBody final Person body) {
    Optional<Person> result = repository.findById(id);

    if (result.isPresent()) {
      Person person = result.get();
      person.setForename(body.getForename());
      person.setSurname(body.getSurname());
      Person savedPerson = repository.save(person);

      return restSupport.singleEntity(savedPerson);
    } else {
      return restSupport.notFound(dataMapFromId(id));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable final Integer id) {
    Optional<Person> result = repository.findById(id);

    if (repository.existsById(id)) {
      repository.deleteById(id);
      return restSupport.noContent();
    } else {
      return restSupport.notFound(dataMapFromId(id));
    }
  }
}
