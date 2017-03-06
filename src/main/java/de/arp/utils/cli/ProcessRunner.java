/**
 * 
 */
package de.arp.utils.cli;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import de.arp.utils.io.StreamPiper;

/**
 * @author arp
 *
 */
public class ProcessRunner {

	private ProcessBuilder processBuilder = new ProcessBuilder();
	
	protected List<String> command;
	protected Thread inputThread;
	protected Thread stdoutThread;
	protected Thread stderrThread;

	public ProcessRunner(String... command) {
		processBuilder.command(command);
	}
	
	public ProcessResult execute() throws IOException, InterruptedException, TimeoutException {
		if (processBuilder.command().isEmpty()) {
			throw new IllegalStateException("Command may not be null");
		}
		Process p = processBuilder.start();
		
		stdoutThread = new Thread(new StreamPiper(p.getInputStream(), System.out));
		stderrThread = new Thread(new StreamPiper(p.getErrorStream(), System.err));
		
		stderrThread.start();
		stdoutThread.start();
		
		p.waitFor();
		return null;
	}
	
}
