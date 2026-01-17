package eu.powerdot.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "eu.powerdot")
public class CodeChallenge {

    public static void main(String[] args) {
        SpringApplication.run(CodeChallenge.class, args);
    }

}
