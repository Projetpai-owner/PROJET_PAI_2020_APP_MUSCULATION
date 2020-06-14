package fr.univ.lille.fil.mbprestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Classe de lancement de l'api REST
 * @author Shadow
 *
 */
@SpringBootApplication
public class SpringBootTomcatApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTomcatApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(
      SpringApplicationBuilder builder) {
        return builder.sources(SpringBootTomcatApplication.class);
    }
 
}