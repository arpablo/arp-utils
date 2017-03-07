/**
 * 
 */
package de.arp.utils.cli;

/**
 * @author arp
 *
 */
public class ProcessResult {

	private int exitCode;
	private byte[] stdout;
	private byte[] stderr;
	
	/**
	 * @return the exitCode
	 */
	public int getExitCode() {
		return exitCode;
	}
	/**
	 * @param exitCode the exitCode to set
	 */
	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}
	/**
	 * @return the stdout
	 */
	public byte[] getStdout() {
		return stdout;
	}
	/**
	 * @param stdout the stdout to set
	 */
	public void setStdout(byte[] stdout) {
		this.stdout = stdout;
	}
	/**
	 * @return the stderr
	 */
	public byte[] getStderr() {
		return stderr;
	}
	/**
	 * @param stderr the stderr to set
	 */
	public void setStderr(byte[] stderr) {
		this.stderr = stderr;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ProcessResult [exitCode=%s]", exitCode);
	}
	
	
	
}
