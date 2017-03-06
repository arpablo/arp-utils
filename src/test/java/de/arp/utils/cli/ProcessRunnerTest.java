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
	public void testJar() {
		try {
			ProcessRunner runner = new ProcessRunner("jar");
			runner.execute();
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
}
