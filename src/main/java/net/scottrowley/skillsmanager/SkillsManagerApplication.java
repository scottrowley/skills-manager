package net.scottrowley.skillsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "net.scottrowley.skillsmanager"
    }
)
public class SkillsManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SkillsManagerApplication.class, args);
  }
}
