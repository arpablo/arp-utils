/**
 * 
 */
package de.arp.utils.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author arp
 *
 */
public class XsltTasklet implements Tasklet {

	public static final String STYLESHEETS_PARAM = "stylesheets";
	
	
	private static final Logger log = LoggerFactory.getLogger(XsltTasklet.class);
	

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		String stylesheets = cc.getStepContext().getStepExecution().getJobParameters().getString(STYLESHEETS_PARAM);
		log.info("Processing stylesheets {}", stylesheets);
		return RepeatStatus.FINISHED;
	}

}
