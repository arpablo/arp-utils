/**
 * 
 */
package de.arp.utils.xml;

import java.util.ArrayList;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author arp
 *
 */
public class ChainedFilterTransformer extends SAXTransformer {

	private ArrayList<XMLFilter> filters = new ArrayList<XMLFilter>();

	/**
	 * Return a new Xalan-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static ChainedFilterTransformer newXalanTransformer() {
		return new ChainedFilterTransformer(XALAN_FACTORY);
	}

	/**
	 * Return a new SAXON HE-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static ChainedFilterTransformer newSaxon_HETransformer() {
		return new ChainedFilterTransformer(SAXON_HE_FACTORY);
	}
	
	/**
	 * Return a new SAXON PE-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static ChainedFilterTransformer newSaxon_PETransformer() {
		return new ChainedFilterTransformer(SAXON_PE_FACTORY);
	}
	
	/**
	 * Return a new SAXON EE-based SAXTransformer
	 * @return a SAXTransformer
	 */
	public static ChainedFilterTransformer newSaxon_EETransformer() {
		return new ChainedFilterTransformer(SAXON_EE_FACTORY);
	}
	
	
	/**
	 * Default Constructor
	 */
	public ChainedFilterTransformer() {
		super();
	}

	/**
	 * Constructor
	 * @param factory
	 */
	public ChainedFilterTransformer(SAXTransformerFactory factory) {
		super(factory);
	}

	/**
	 * Constructor
	 * @param className
	 */
	public ChainedFilterTransformer(String className) {
		super(className);
	}
	
	/**
	 * Constructor
	 * @param className
	 * @param cl
	 */
	public ChainedFilterTransformer(String className, ClassLoader cl) {
		super(className, cl);
	}

	/**
	 * Clear the filters of this chain
	 */
	public void clearFilters() {
		this.filters.clear();
	}
	
	/**
	 * Add a filter to this chain
	 * @param filter the filter to add
	 */
	public void addFilter(XMLFilter filter) {
		this.filters.add(filter);
	}

	/**
	 * Add a stylesheet to this chain
	 * @param source the source of the stylesheet
	 * @throws TransformerConfigurationException
	 */
	public void addStyle(Source source) throws TransformerConfigurationException {
		this.filters.add(getSAXTransformerFactory().newXMLFilter(source));
	}
	
	/**
	 * Add a style that is created using the given templates
	 * @param template
	 * @throws TransformerConfigurationException
	 */
	public void addStyle(Templates template) throws TransformerConfigurationException {
		this.filters.add(getSAXTransformerFactory().newXMLFilter(template));
	}
	
	/* (non-Javadoc)
	 * @see de.arp.utils.xml.SAXTransformer#transform(javax.xml.transform.Source, javax.xml.transform.Result)
	 */
	@Override
	public void transform(Source source, Result result) throws SAXException, TransformerException  {
		this.transform(getSAXTransformerFactory().newTransformer(), source, result);
	}

	/* (non-Javadoc)
	 * @see de.arp.utils.xml.SAXTransformer#transform(javax.xml.transform.Transformer, javax.xml.transform.Source, javax.xml.transform.Result)
	 */
	@Override
	public void transform(Transformer t, Source source, Result result) throws SAXException, TransformerException {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();

			XMLReader lastFilter = reader;
			for (XMLFilter filter : filters) {
				filter.setParent(lastFilter);
				lastFilter = filter;
			}
			t.transform(new SAXSource(lastFilter, XMLUtils.sourceToInputSource(source)), result);
		} catch (TransformerConfigurationException ex) {
			throw new TransformerException(ex.getMessage(), ex);
		}
	}
	
}
