package net.scottrowley.skillsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Skill {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<SkillProficiency> skillProficiencies;

  public Skill() {}

  public Skill(final String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Set<SkillProficiency> getSkillProficiencies() {
    return skillProficiencies;
  }

  public void setSkillProficiencies(final Set<SkillProficiency> skillProficiencies) {
    this.skillProficiencies = skillProficiencies;
  }
}
