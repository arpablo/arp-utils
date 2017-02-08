/**
 * 
 */
package de.arp.utils.xml;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;

import org.xml.sax.SAXException;

/**
 * @author arp
 *
 */
public abstract class SAXTransformer {

	public static final String XALAN_FACTORY = "org.apache.xalan.processor.TransformerFactoryImpl";
	public static final String SAXON_HE_FACTORY = "net.sf.saxon.TransformerFactoryImpl";
	public static final String SAXON_PE_FACTORY = "com.saxonica.config.ProfessionalTransformerFactory"; 
	public static final String SAXON_EE_FACTORY = "com.saxonica.config.EnterpriseTransformerFactory";	
	
	private SAXTransformerFactory stf;
	
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

	
	/**
	 * Transform the given source to the given result
	 * @param source	the Source to transform
	 * @param result	the result to transform to
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public abstract void transform(Source source, Result result) throws SAXException, TransformerException;

	/**
	 * Transform the given source to the given result using the provided transformer
	 * @param t			the transformer to use
	 * @param source	the Source to transform
	 * @param result	the result to transform to
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public abstract void transform(Transformer t, Source source, Result result) throws SAXException, TransformerException;

}
