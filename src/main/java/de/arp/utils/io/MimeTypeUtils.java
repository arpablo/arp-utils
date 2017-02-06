/**
 * 
 */
package de.arp.utils.io;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.activation.MimetypesFileTypeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides utility methods for handling MimeTypes
 * @author arp
 *
 */
public class MimeTypeUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(MimeTypeUtils.class);
	
	private static final Map<String, String> EXTENSIONSMAP;
	private static final MimetypesFileTypeMap MIMETYPESMAP = new MimetypesFileTypeMap();
	
	
	static {
		Map<String, String> extensionMappings = new HashMap<String, String>();
		extensionMappings.put("doc","application/msword");
		extensionMappings.put("dot","application/msword");
		extensionMappings.put("docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		extensionMappings.put("dotx","application/vnd.openxmlformats-officedocument.wordprocessingml.template");
		extensionMappings.put("docm","application/vnd.ms-word.document.macroEnabled.12");
		extensionMappings.put("dotm","application/vnd.ms-word.template.macroEnabled.12");
		extensionMappings.put("xls","application/vnd.ms-excel");
		extensionMappings.put("xlt","application/vnd.ms-excel");
		extensionMappings.put("xla","application/vnd.ms-excel");
		extensionMappings.put("xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		extensionMappings.put("xltx","application/vnd.openxmlformats-officedocument.spreadsheetml.template");
		extensionMappings.put("xlsm","application/vnd.ms-excel.sheet.macroEnabled.12");
		extensionMappings.put("xltm","application/vnd.ms-excel.template.macroEnabled.12");
		extensionMappings.put("xlam","application/vnd.ms-excel.addin.macroEnabled.12");
		extensionMappings.put("xlsb","application/vnd.ms-excel.sheet.binary.macroEnabled.12");
		extensionMappings.put("ppt","application/vnd.ms-powerpoint");
		extensionMappings.put("pot","application/vnd.ms-powerpoint");
		extensionMappings.put("pps","application/vnd.ms-powerpoint");
		extensionMappings.put("ppa","application/vnd.ms-powerpoint");
		extensionMappings.put("pptx","application/vnd.openxmlformats-officedocument.presentationml.presentation");
		extensionMappings.put("potx","application/vnd.openxmlformats-officedocument.presentationml.template");
		extensionMappings.put("ppsx","application/vnd.openxmlformats-officedocument.presentationml.slideshow");
		extensionMappings.put("ppam","application/vnd.ms-powerpoint.addin.macroEnabled.12");
		extensionMappings.put("pptm","application/vnd.ms-powerpoint.presentation.macroEnabled.12");
		extensionMappings.put("potm","application/vnd.ms-powerpoint.template.macroEnabled.12");
		extensionMappings.put("ppsm","application/vnd.ms-powerpoint.slideshow.macroEnabled.12");
		
		extensionMappings.put("xml","text/xml");
		extensionMappings.put("pdf","application/pdf");
		extensionMappings.put("gif","image/gif");
		extensionMappings.put("jpg","image/jpg");
		extensionMappings.put("png","image/png");
		extensionMappings.put("tif", "image/tiff");
		extensionMappings.put("tiff", "image/tiff");
		extensionMappings.put("mp4","video/mp4");
		extensionMappings.put("mp3","audio/mp3");
		
		EXTENSIONSMAP = Collections.unmodifiableMap(extensionMappings);
	}

	/**
	 * Default Constructor to disable instantiation
	 */
	public MimeTypeUtils() {
		
	}
	
	/**
	 * Return a MimeType for a filename
	 * @param fileName	the filename to check
	 * @return a MimeType
	 * @throws MimeTypeParseException
	 */
	public static MimeType getMimeTypeForFilename(final String fileName) throws MimeTypeParseException {
		if (fileName == null || !fileName.contains(".")) {
			throw new IllegalArgumentException("Filename is NULL or contains no suffix");
		}
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (EXTENSIONSMAP.containsKey(extension)) {
			return new MimeType(EXTENSIONSMAP.get(extension));
		}
		//Example: imgf35409e6ee885ae90a9dd569570d1b12.vnd.openxmlformats-officedocument.presentationml.presentation
		for (final String mimeTypeString : EXTENSIONSMAP.values()) {
			final MimeType mimeType = new MimeType(mimeTypeString);
			if (fileName.endsWith(mimeType.getSubType())) {
				return mimeType;
			}
		}

		final MimeType ret =  new MimeType(MIMETYPESMAP.getContentType(fileName));
		LOG.debug("Determining mime-type as: " + ret.toString() + " for filename: " + fileName);
		return ret;
	}


}
