/**
 * 
 */
package de.arp.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author arp
 *
 */
public class IOUtils {

	
	private static final Logger log = LoggerFactory.getLogger(IOUtils.class);

	/**
	 * Silently close the given InputStream
	 * @param in
	 */
	public static void closeStream(InputStream in) {
		if (in != null) {
			try {
				in.close();
				in = null;
			} catch (IOException ex) {
				log.error("Error closing stream {}", in, ex);
			}
		}
	}

	/**
	 * Silently close the given OutputStream
	 * @param in
	 */
	public static void closeStream(OutputStream out) {
		if (out != null) {
			try {
				out.close();
				out = null;
			} catch (IOException ex) {
				log.error("Error closing stream {}", out, ex);
			}
		}
	}
	
	/**
	 * Silently lush the given OutputStream
	 * @param out
	 */
	public static void flushStream(OutputStream out) {
		if (out != null) {
			try {
				out.flush();
			} catch (IOException ex) {
				log.error("Error flushing stream {}", out, ex);
			}
		}
	}
	
}
