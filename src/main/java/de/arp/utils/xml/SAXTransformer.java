/**
 * 
 */
package de.arp.utils.xml;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * @author arp
 *
 */
public class SAXTransformer {

	private SAXTransformerFactory stf;
	
	public static final String SAXON_HE_FACTORY = "net.sf.saxon.TransformerFactoryImpl";
	public static final String SAXON_PE_FACTORY = "com.saxonica.config.ProfessionalTransformerFactory"; 
	public static final String SAXON_EE_FACTORY = "com.saxonica.config.EnterpriseTransformerFactory";	
	
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
	 * Cosntructor
	 * @param className	the name of the tarnsformer class
	 * @param cl the classloader to use
	 */
	public SAXTransformer(String className, ClassLoader cl) {
		stf = (SAXTransformerFactory) TransformerFactory.newInstance(className, cl);
	}
	
}
