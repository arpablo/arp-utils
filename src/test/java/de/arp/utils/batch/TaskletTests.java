/**
 * 
 */
package de.arp.utils.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author arp
 *
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = TaskletApplication.class )
public class TaskletTests {

	@Autowired private JobLauncher jobLauncher;
	@Autowired private Job job;
	
	private static final Logger log = LoggerFactory.getLogger(TaskletTests.class);

	@Test
	public void testXslt1() throws Exception {
		JobParameters params1 = new JobParametersBuilder()
				.addString(XsltTasklet.STYLESHEETS_PARAM, "test1.xsl")
				.toJobParameters();
		JobParameters params2 = new JobParametersBuilder()
				.addString(XsltTasklet.STYLESHEETS_PARAM, "test2.xsl")
				.toJobParameters();
		jobLauncher.run(job, params1);
		jobLauncher.run(job, params2);
	}
}
