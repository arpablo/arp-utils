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
 * This class reads the content of a given Stream and writes
 * it to another stream. The class is implemented as a Runnable
 * to prvent blocking
 * @author arp
 *
 */
public class StreamPiper implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(StreamPiper.class);
	
	private static final int DEFAULT_BUFFER_SIZE = 64 * 1024;
	
	private int bufferSize = DEFAULT_BUFFER_SIZE;
	
	private boolean closeInputStream = true;
	private boolean closeOutputStream = true;
	
	private InputStream input;
	private OutputStream output;
	
	/**
	 * Constructor
	 * @param in	the InputStream to read from
	 * @param out	the OutputStream to write to
	 */
	public StreamPiper(InputStream in, OutputStream out) {
		this.input = in;
		this.output = out;
	}
	
	
	/**
	 * @return the bufferSize
	 */
	public int getBufferSize() {
		return bufferSize;
	}


	/**
	 * @param bufferSize the bufferSize to set
	 */
	public StreamPiper setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
		return this;
	}


	/**
	 * @return the closeInputStream
	 */
	public boolean isCloseInputStream() {
		return closeInputStream;
	}


	/**
	 * @param closeInputStream the closeInputStream to set
	 */
	public StreamPiper setCloseInputStream(boolean closeInputStream) {
		this.closeInputStream = closeInputStream;
		return this;
	}


	/**
	 * @return the closeOutputStream
	 */
	public boolean isCloseOutputStream() {
		return closeOutputStream;
	}


	/**
	 * @param closeOutputStream the closeOutputStream to set
	 */
	public StreamPiper setCloseOutputStream(boolean closeOutputStream) {
		this.closeOutputStream = closeOutputStream;
		return this;
	}


	@Override
	public void run() {
		log.trace("{} started", this);
		byte[] buffer = new byte[bufferSize];
		
		try {
			int size = 0;
			while( (size = input.read(buffer)) > 0) {
				output.write(buffer, 0, size);
			}
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			if (closeInputStream) {
				try {
					input.close();
				} catch (Exception ex) {
					log.error("Error closing InputStream");
				}
			}
			if (closeOutputStream) {
				try {
					output.close();
				} catch (Exception ex) {
					log.error("Error closing OutputStream");
				}
			}
		}

		
		log.trace("{} finished", this);
	}

}
