/**
 * 
 */
package de.arp.utils.cli;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.arp.utils.io.IOUtils;
import de.arp.utils.io.StreamPiper;

/**
 * This class executes a command line 
 * @author arp
 *
 */
public class ProcessRunner {

	
	private static final Logger log = LoggerFactory.getLogger(ProcessRunner.class);

	private Map<String, String> environment = new LinkedHashMap<String, String>();
	
	private ProcessBuilder processBuilder = new ProcessBuilder();
	
	protected ProcessStreamSlurper processStreamSlurper = new ProcessStreamSlurper(null, NullOutputStream.NULL_OUTPUT_STREAM, NullOutputStream.NULL_OUTPUT_STREAM);
	
	/**
	 * Create a new ProcessRunner from a command line
	 * @param cmdLine
	 * @return
	 */
	public static ProcessRunner newRunner(String cmdLine) {
		if (cmdLine != null) {
			return new ProcessRunner(cmdLine.split("\\s+"));
		} else {
			return new ProcessRunner("");
		}
	}
	
	/**
	 * Constructor
	 * @param command the command to execute
	 */
	public ProcessRunner(String... command) {
		processBuilder.command(command);
	}
	
	
	/**
	 * @return the environment
	 */
	public Map<String, String> getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public ProcessRunner setEnvironment(Map<String, String> environment) {
		this.environment = environment;
		return this;
	}

	public ProcessRunner addEnvironment(String key, String value) {
		if (this.environment == null) {
			this.environment = new LinkedHashMap<String, String>();
		}
		this.environment.put(key, value);
		return this;
	}
	
	/**
	 * Redirect stdout of the process to the given
	 * OutputStream
	 * @param out
	 */
	public ProcessRunner redirectStdoutTo(OutputStream out) {
		this.processStreamSlurper.stdout = out;
		return this;
	}
	
	/**
	 * Redirect stdout also to the given OutputStream
	 * @param out
	 * @return
	 */
	public ProcessRunner teeStdoutTo(OutputStream out) {
		OutputStream stdout = this.processStreamSlurper.stdout;
		if (stdout == null || stdout == NullOutputStream.NULL_OUTPUT_STREAM ) {
			return redirectStdoutTo(out);
		}
		TeeOutputStream tOut = new TeeOutputStream(stdout, out);
		return redirectStdoutTo(tOut);
	}
	
	/**
	 * Redirect stderr of the process to the given
	 * OutputStream
	 * @param out
	 */
	public ProcessRunner redirectStderrTo(OutputStream out) {
		this.processStreamSlurper.stderr = out;
		if (processBuilder.redirectErrorStream() == true) {
			processBuilder.redirectErrorStream(false);
		}
		return this;
	}

	/**
	 * Redirect stdout also to the given OutputStream
	 * @param out
	 * @return
	 */
	public ProcessRunner teeStderrTo(OutputStream out) {
		OutputStream stderr = this.processStreamSlurper.stderr;
		if (stderr == null || stderr == NullOutputStream.NULL_OUTPUT_STREAM ) {
			return redirectStderrTo(out);
		}
		TeeOutputStream tOut = new TeeOutputStream(stderr, out);
		return redirectStderrTo(tOut);
	}
	
	
	/**
	 * Read the OutputStreams of the process and return them
	 * in the result
	 */
	public ProcessRunner processOutputStreams() {
		redirectStdoutTo(new ByteArrayOutputStream());
		if (this.processBuilder.redirectErrorStream() == false) {
			redirectStderrTo(new ByteArrayOutputStream());
		}
		return this;
	}

	public ProcessRunner redirectStderr(boolean value) {
		this.processBuilder.redirectErrorStream(value);
		if (value == true) {
			this.processStreamSlurper.stderr = null;
		}
		return this;
	}
	
	/**
	 * Execute the command
	 * @return a ProcessResult
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	public ProcessResult execute() throws IOException, InterruptedException, TimeoutException {
		if (processBuilder.command().isEmpty()) {
			throw new IllegalStateException("Command may not be null");
		}
		log.trace("Executing command {}", processBuilder.command());
		setBuilderEnvironment();
		Process p = processBuilder.start();
		
		ProcCallable task = new ProcCallable(p, processStreamSlurper);
		ProcessResult ret = task.call();
		log.trace("Command {} returned {}", processBuilder.command(), ret);
		return ret;
	}
	
	/**
	 * Copy the current environment to the builder environment
	 * @return
	 */
	protected ProcessRunner setBuilderEnvironment() {
		Map<String, String> env = processBuilder.environment();
		for (Entry<String, String> entry : this.environment.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (value == null) {
				env.remove(key);
			} else {
				env.put(key, value);
			}
		}
		return this;
	}
	
	class ProcCallable implements Callable<ProcessResult> {

		private final Process process;
		private final ProcessStreamSlurper slurper;
		
		public ProcCallable(Process process, ProcessStreamSlurper slurper) {
			this.process = process;
			this.slurper = slurper;
		}
		
		@Override
		public ProcessResult call() throws IOException, InterruptedException, TimeoutException {
			try {
				slurper.prepareThreads(process);
				slurper.start();
				int exitCode = process.waitFor();
				slurper.stop();
				ProcessResult result = new ProcessResult();
				result.setExitCode(exitCode);
				return result;
			} finally {
				
			}
		}
		
	}
	
	static class ProcessStreamSlurper {
		
		
		private static final Logger log = LoggerFactory.getLogger(ProcessRunner.ProcessStreamSlurper.class);

		private OutputStream stdout;
		private OutputStream stderr;
		private InputStream stdin;
		
		private Thread stdoutThread = null;
		private Thread stderrThread = null;
		private Thread stdinThread = null;
		
		public ProcessStreamSlurper(InputStream stdin, OutputStream stdout, OutputStream stderr) {
			this.stdin = stdin;
			this.stdout = stdout;
			this.stderr = stderr;
		}
		
		/**
		 * Return the content of stdout. This will only return a value, if stdout
		 * is a ByteAraryInputStream. Otherwise NULL is returned
		 * @return a byte[] or null
		 */
		public byte[] getStdoutContent() {
			if (stdout != null && stdout instanceof ByteArrayOutputStream) {
				return ((ByteArrayOutputStream) stdout).toByteArray();
			}
			return null;
		}
		
		/**
		 * Return the content of stderr. This will only return a value, if stderr
		 * is a ByteAraryInputStream. Otherwise NULL is returned
		 * @return a byte[] or null
		 */
		public byte[] getStderrContent() {
			if (stderr != null && stderr instanceof ByteArrayOutputStream) {
				return ((ByteArrayOutputStream) stderr).toByteArray();
			}
			return null;
		}
		
		public void prepareThreads(Process p) {
			if (stdout != null) {
				stdoutThread = new Thread(new StreamPiper(p.getInputStream(), stdout));
				stdoutThread.setDaemon(true);
			}
			if (stderr != null) {
				stderrThread = new Thread(new StreamPiper(p.getErrorStream(), stderr));
				stderrThread.setDaemon(true);
			}
			if (stdin != null) {
				stdinThread = new Thread(new StreamPiper(stdin, p.getOutputStream()));
				stdinThread.setDaemon(true);
			}
		}
		
		/**
		 * Start all threads
		 */
		public void start() {
			if (stdoutThread != null) {
				stdoutThread.start();
			}
			if (stderrThread != null) {
				stderrThread.start();
			}
			if (stdinThread != null) {
				stdinThread.start();
			}
		}
		
		/**
		 * Stop all threads
		 */
		public void stop() {
		
			stopThread(stdoutThread);
			stopThread(stderrThread);
			
			if (stdout != null) {
				IOUtils.flushStream(stdout);
			}
			if (stderr != null && (stderr != stdout)) {
				IOUtils.flushStream(stderr);
			}
		}
		
		protected void stopThread(Thread thread) {
			if (thread != null) {
				try {
					thread.join();
					thread = null;
				} catch (InterruptedException ex) {
					log.error("Error stoping thread {}", thread, ex);
				}
			}
		}
		
	}
}
