package net.scottrowley.skillsmanager.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class PersonSkillKey implements Serializable  {
  private Integer personId;
  private Integer skillId;

  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(final Integer personId) {
    this.personId = personId;
  }

  public Integer getSkillId() {
    return skillId;
  }

  public void setSkillId(final Integer skillId) {
    this.skillId = skillId;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonSkillKey that = (PersonSkillKey) o;
    return personId.equals(that.personId) &&
        skillId.equals(that.skillId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personId, skillId);
  }
}
