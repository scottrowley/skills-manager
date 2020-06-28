package net.scottrowley.skillsmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;

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
}
