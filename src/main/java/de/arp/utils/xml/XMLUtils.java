/**
 * 
 */
package de.arp.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * This class iplements utility methods for dealing with XML
 * 
 * @author arp
 *
 */
public class XMLUtils {

	/**
	 * Write the content of a DOM element to the provided outputstream
	 * 
	 * @param element
	 * @param out
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public static void elementToStream(Element element, OutputStream out)
			throws TransformerConfigurationException, TransformerException {
		DOMSource source = new DOMSource(element);
		StreamResult result = new StreamResult(out);
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		transformer.transform(source, result);
	}

	/**
	 * Return an InputSource for a given URI
	 * 
	 * @param uri the URI
	 * @return an InputSource
	 */
	public static InputSource getInputSourceFromURI(String uri) {
		InputSource ret = new InputSource(uri);
		ret.setSystemId(uri);
		return ret;
	}

	/**
	 * Convert a Source to an InputStream
	 * @param source the source to convert
	 * @return an InputStream
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public static InputSource sourceToInputSource(Source source) 
			throws TransformerConfigurationException, TransformerException {
	      if (source instanceof SAXSource) {
	          return ((SAXSource) source).getInputSource();
	      } else if (source instanceof DOMSource) {
	          ByteArrayOutputStream baos = new ByteArrayOutputStream();
	          Node node = ((DOMSource) source).getNode();
	          if (node instanceof Document) {
	              node = ((Document) node).getDocumentElement();
	          }
	          elementToStream((Element) node, baos);
	          InputSource is = new InputSource(source.getSystemId());
	          is.setByteStream(new ByteArrayInputStream(baos.toByteArray()));
	          return is;
	      } else if (source instanceof StreamSource) {
	          StreamSource ss = (StreamSource) source;
	          InputSource is = new InputSource(ss.getSystemId());
	          is.setByteStream(ss.getInputStream());
	          is.setCharacterStream(ss.getReader());
	          is.setPublicId(ss.getPublicId());
	          return is;
	      } else {
	          return getInputSourceFromURI(source.getSystemId());
	      }
	}

}
