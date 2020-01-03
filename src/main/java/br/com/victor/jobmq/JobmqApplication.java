package br.com.victor.jobmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("br.com.victor.jobmq.properties")
public class JobmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobmqApplication.class, args);
	}

}
