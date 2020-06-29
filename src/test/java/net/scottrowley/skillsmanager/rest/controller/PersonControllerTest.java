package net.scottrowley.skillsmanager.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.scottrowley.skillsmanager.model.Person;
import net.scottrowley.skillsmanager.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
  @Autowired
  private MockMvc api;

  @Autowired
  private PersonRepository repo;

  private final List<Person> prepopulatedPersons = new ArrayList<>();
  private static final String BASE_PATH = "/person/";

  @Before
  public void setup() {
    prepopulatedPersons.add(repo.save(new Person("John", "Doe")));
    prepopulatedPersons.add(repo.save(new Person("Jane", "Doe")));
  }

  @After
  public void tearDown() {
    repo.deleteAll();
  }

  @Test
  public void canReadSkill() throws Exception {
    api.perform(get(BASE_PATH + prepopulatedPersons.get(0).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk()
    ).andExpect(jsonPath("$.forename").value("John")
    ).andExpect(jsonPath("$.surname").value("Doe")
    ).andExpect(jsonPath("$.id").exists());
  }

  @Test
  public void canListSkills() throws Exception {
    api.perform(get(BASE_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk()
    ).andExpect(jsonPath("$").isArray()
    ).andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  public void canCreateSkill() throws Exception {
    api.perform(post(BASE_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new Person("John", "Smith")))
    ).andExpect(status().isCreated()
    ).andExpect(jsonPath("$.forename").value("John")
    ).andExpect(jsonPath("$.surname").value("Smith")
    ).andExpect(jsonPath("$.id").exists());
  }

  @Test
  public void canUpdateSkill() throws Exception {
    api.perform(put(BASE_PATH + prepopulatedPersons.get(1).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new Person("Mark", "Anthony")))
    ).andExpect(status().isOk()
    ).andExpect(jsonPath("$.forename").value("Mark")
    ).andExpect(jsonPath("$.surname").value("Anthony")
    ).andExpect(jsonPath("$.id").value(prepopulatedPersons.get(1).getId()));

    Person updatedPerson = repo.findById(prepopulatedPersons.get(1).getId()).orElseThrow(RuntimeException::new);
    assertThat(updatedPerson.getForename()).isEqualTo("Mark");
    assertThat(updatedPerson.getSurname()).isEqualTo("Anthony");
  }

  @Test
  public void canDeleteSkill() throws Exception {
    api.perform(delete(BASE_PATH + prepopulatedPersons.get(1).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isNoContent());

    assertThat(repo.findById(prepopulatedPersons.get(1).getId())).isEmpty();
  }

  static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
