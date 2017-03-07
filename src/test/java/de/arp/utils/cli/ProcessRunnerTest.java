/**
 * 
 */
package de.arp.utils.cli;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author arp
 *
 */
public class ProcessRunnerTest {

	@Test
	public void testJavaVersion() {
		try {
			ProcessRunner runner = new ProcessRunner("java", "-version");
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void testJavaVersionFromCli() {
		try {
			ProcessRunner runner = ProcessRunner.newRunner("java -version");
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}


	@Test
	public void testJar() {
		try {
			ProcessRunner runner = new ProcessRunner("jar");
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void testRedirectStdout() {
		try {
			ProcessRunner runner = new ProcessRunner("jar");
			runner.redirectStdoutTo(System.out);
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void testTeeStdout() {
		try {
			ProcessRunner runner = new ProcessRunner("jar");
			runner.redirectStdoutTo(System.out).teeStdoutTo(System.out);
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void testRedirectStderr() {
		try {
			ProcessRunner runner = new ProcessRunner("jar");
			runner.redirectStderrTo(System.err);
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void testTeeStdErr() {
		try {
			ProcessRunner runner = new ProcessRunner("jar");
			runner.redirectStderrTo(System.err).teeStderrTo(System.out);
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	
}
