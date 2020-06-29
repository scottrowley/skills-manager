package net.scottrowley.skillsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Person")
public class Person {
  @Id
  @GeneratedValue
  private Integer id;
  private String forename;
  private String surname;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<SkillProficiency> skillProficiencies;

  public Person() {
  }

  public Person(final String forename, final String surname) {
    this.forename = forename;
    this.surname = surname;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getForename() {
    return forename;
  }

  public void setForename(final String forename) {
    this.forename = forename;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(final String surname) {
    this.surname = surname;
  }

  public Set<SkillProficiency> getSkillProficiencies() {
    return skillProficiencies;
  }

  public void setSkillProficiencies(final Set<SkillProficiency> skillProficiencies) {
    this.skillProficiencies = skillProficiencies;
  }

  public void addSkillProficiency(final SkillProficiency proficiency) {
    this.skillProficiencies.add(proficiency);
  }
}
