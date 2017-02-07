/**
 * 
 */
package de.arp.utils.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * This class implements static methods for compressing and
 * decompressing strings
 * @author arp
 *
 */
public class CompressUtils {

	/**
	 * Compress a string returning a Base64 encoded String
	 * @param string	the String to compress
	 * @return	a string
	 * @throws IOException
	 */
	public static String gzipCompressBase64(String string) throws IOException {
		return Base64.getEncoder().encodeToString(gzipCompress(string));
	}


	/**
	 * Compress a String using GZip
	 * @param string the String to compress
	 * @return a byte[]
	 * @throws IOException
	 */
	public static byte[] gzipCompress(String string) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
		GZIPOutputStream gos = new GZIPOutputStream(os);
		gos.write(string.getBytes());
		gos.close();
		byte[] compressed = os.toByteArray();
		os.close();
		return compressed;
	}
	
	/**
	 * Decompress a Base64 encoded compressed String
	 * @param compressed	the String to decompress
	 * @return a string
	 * @throws IOException
	 */
	public static String gzipDecompressBase64(String compressed) throws IOException {
		return gzipDecompress(Base64.getDecoder().decode(compressed));
	}


	/**
	 * Decompress an encoded compressed string
	 * @param compressed	the string to decompress
	 * @param decoder		the decoder to use
	 * @return a string
	 * @throws IOException
	 */
	public static String gzipDecompress(byte[] compressed) throws IOException {
		final int BUFFER_SIZE = 32;

		ByteArrayInputStream is = new ByteArrayInputStream(compressed);
		GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
		StringBuilder ret = new StringBuilder();
		byte[] data = new byte[BUFFER_SIZE];
		int bytesRead;
		while ((bytesRead = gis.read(data)) != -1) {
			ret.append(new String(data, 0, bytesRead));
		}
		gis.close();
		is.close();
		return ret.toString();
	}
	
}
