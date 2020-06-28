package net.scottrowley.skillsmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Person")
public class Person {
  @Id
  @GeneratedValue
  private Integer id;
  private String forename;
  private String surname;

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
}
