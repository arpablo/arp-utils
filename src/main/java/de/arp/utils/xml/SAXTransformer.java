/**
 * 
 */
package de.arp.utils.xml;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.ContentHandler;

/**
 * @author arp
 *
 */
public class SAXTransformer {

	public static final String XALAN_FACTORY = "org.apache.xalan.processor.TransformerFactoryImpl";
	public static final String SAXON_HE_FACTORY = "net.sf.saxon.TransformerFactoryImpl";
	public static final String SAXON_PE_FACTORY = "com.saxonica.config.ProfessionalTransformerFactory"; 
	public static final String SAXON_EE_FACTORY = "com.saxonica.config.EnterpriseTransformerFactory";	
	
	private SAXTransformerFactory stf;
	
	/**
	 * Return a new Xalan-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static SAXTransformer newXalanSAXTransformer() {
		return new SAXTransformer(XALAN_FACTORY);
	}

	/**
	 * Return a new SAXON HE-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static SAXTransformer newSaxon_HESAXTransformer() {
		return new SAXTransformer(SAXON_HE_FACTORY);
	}
	
	/**
	 * Return a new SAXON PE-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static SAXTransformer newSaxon_PESAXTransformer() {
		return new SAXTransformer(SAXON_PE_FACTORY);
	}
	
	/**
	 * Return a new SAXON EE-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static SAXTransformer newSaxon_EESAXTransformer() {
		return new SAXTransformer(SAXON_EE_FACTORY);
	}
	

	/**
	 * Default Constructor
	 */
	public SAXTransformer() {
		stf = (SAXTransformerFactory) TransformerFactory.newInstance();
	}
	
	/**
	 * Constructor
	 * This constructor used the contextClassLoader of the current thread
	 * @param className the name of the transformer class
	 */
	public SAXTransformer(String className) {
		this(className, Thread.currentThread().getContextClassLoader());
	}
	
	/**
	 * Constructor
	 * @param className	the name of the tarnsformer class
	 * @param cl the classloader to use
	 */
	public SAXTransformer(String className, ClassLoader cl) {
		stf = (SAXTransformerFactory) TransformerFactory.newInstance(className, cl);
	}
	
	/**
	 * Constructor
	 * @param factory the SAX Transformer factory to use
	 */
	public SAXTransformer(SAXTransformerFactory factory) {
		this.stf = factory;
	}
	
	/**
	 * Return the SAXTransformerFactory of this instance
	 * @return a SAXTransformerFactory
	 */
	public SAXTransformerFactory getSAXTransformerFactory() {
		return stf;
	}

}
