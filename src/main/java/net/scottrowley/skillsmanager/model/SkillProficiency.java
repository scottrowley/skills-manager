package net.scottrowley.skillsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SkillProficiency {
  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne
  private Person person;

  @ManyToOne
  private Skill skill;

  @Enumerated(EnumType.STRING)
  private Proficiency proficiency;

  public SkillProficiency() {
  }

  public SkillProficiency(final Proficiency proficiency) {
    this.proficiency = proficiency;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(final Person person) {
    this.person = person;
  }

  public Skill getSkill() {
    return skill;
  }

  public void setSkill(final Skill skill) {
    this.skill = skill;
  }

  public Proficiency getProficiency() {
    return proficiency;
  }

  public void setProficiency(final Proficiency proficiency) {
    this.proficiency = proficiency;
  }
}
