/**
 * 
 */
package de.arp.utils.batch;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author arp
 *
 */
@Configuration
@EnableBatchProcessing
@SpringBootApplication
public class TaskletApplication {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	private HashMap<String, Step> stepDefs = new HashMap<String, Step>();
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TaskletApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		stepDefs.put("xsltStep", stepBuilderFactory.get("xsltStep").tasklet(new XsltTasklet()).build());
	}
	
	public Step xsltStep() {
		return stepBuilderFactory.get("xsltStep").tasklet(new XsltTasklet()).build();
	}
	
	@Bean
	public Job getMainJob() {
		return jobBuilderFactory.get("myjob").incrementer(new RunIdIncrementer())
				.start(stepDefs.get("xsltStep"))
				.build();
	}
}
