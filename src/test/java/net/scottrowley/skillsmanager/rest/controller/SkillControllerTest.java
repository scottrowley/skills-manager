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
import net.scottrowley.skillsmanager.model.Skill;
import net.scottrowley.skillsmanager.repository.SkillRepository;
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
public class SkillControllerTest {
  @Autowired
  private MockMvc api;

  @Autowired
  private SkillRepository repo;

  private final List<Skill> prepopulatedSkills = new ArrayList<>();
  private static final String BASE_PATH = "/skill/";

  @Before
  public void setup() {
    prepopulatedSkills.add(repo.save(new Skill("Java")));
    prepopulatedSkills.add(repo.save(new Skill("Node.js")));
  }

  @After
  public void tearDown() {
    repo.deleteAll();
  }

  @Test
  public void canReadSkill() throws Exception {
    api.perform(get(BASE_PATH + prepopulatedSkills.get(0).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk()
    ).andExpect(jsonPath("$.name").value("Java")
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
        .content(asJsonString(new Skill("test")))
    ).andExpect(status().isCreated()
    ).andExpect(jsonPath("$.name").value("test")
    ).andExpect(jsonPath("$.id").exists());
  }

  @Test
  public void canUpdateSkill() throws Exception {
    api.perform(put(BASE_PATH + prepopulatedSkills.get(1).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new Skill(".NET")))
    ).andExpect(status().isOk()
    ).andExpect(jsonPath("$.name").value(".NET")
    ).andExpect(jsonPath("$.id").value(prepopulatedSkills.get(1).getId()));

    Skill updatedSkill = repo.findById(prepopulatedSkills.get(1).getId()).orElseThrow(RuntimeException::new);
    assertThat(updatedSkill.getName()).isEqualTo(".NET");
  }

  @Test
  public void canDeleteSkill() throws Exception {
    api.perform(delete(BASE_PATH + prepopulatedSkills.get(1).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk()
    ).andExpect(jsonPath("$.name").value("Node.js")
    ).andExpect(jsonPath("$.id").exists());

    assertThat(repo.findById(prepopulatedSkills.get(1).getId())).isEmpty();
  }

  static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
