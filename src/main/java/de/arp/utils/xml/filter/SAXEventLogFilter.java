/**
 * 
 */
package de.arp.utils.xml.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * @author arp
 *
 */
public class SAXEventLogFilter extends XMLFilterImpl {

	public static Logger logger = LoggerFactory.getLogger(SAXEventLogFilter.class);

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setParent(org.xml.sax.XMLReader)
	 */
	@Override
	public void setParent(XMLReader parent) {
		logger.debug("setParent(parent");
		super.setParent(parent);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#getParent()
	 */
	@Override
	public XMLReader getParent() {
		logger.debug("getParent()");
		return super.getParent();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setFeature(java.lang.String, boolean)
	 */
	@Override
	public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
		logger.debug("setFeature({},{}", name, value);
		super.setFeature(name, value);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#getFeature(java.lang.String)
	 */
	@Override
	public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
		logger.debug("getFeature({})", name);
		return super.getFeature(name);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
		logger.debug("setProperty({},{}", name, value);
		super.setProperty(name, value);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#getProperty(java.lang.String)
	 */
	@Override
	public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
		logger.debug("getProperty({}", name);
		return super.getProperty(name);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setEntityResolver(org.xml.sax.EntityResolver)
	 */
	@Override
	public void setEntityResolver(EntityResolver resolver) {
		logger.debug("setEntityResolver({}", resolver == null? "null" : resolver.getClass().getName());
		super.setEntityResolver(resolver);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#getEntityResolver()
	 */
	@Override
	public EntityResolver getEntityResolver() {
		logger.debug("getEntityResolver()");
		return super.getEntityResolver();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setDTDHandler(org.xml.sax.DTDHandler)
	 */
	@Override
	public void setDTDHandler(DTDHandler handler) {
		logger.debug("setDTDHandler(handler)");
		super.setDTDHandler(handler);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#getDTDHandler()
	 */
	@Override
	public DTDHandler getDTDHandler() {
		logger.debug("getDTDHandler");
		return super.getDTDHandler();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setContentHandler(org.xml.sax.ContentHandler)
	 */
	@Override
	public void setContentHandler(ContentHandler handler) {
		logger.debug("setContentHandler(handler)");
		super.setContentHandler(handler);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#getContentHandler()
	 */
	@Override
	public ContentHandler getContentHandler() {
		logger.debug("getContentHandler()");
		return super.getContentHandler();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setErrorHandler(org.xml.sax.ErrorHandler)
	 */
	@Override
	public void setErrorHandler(ErrorHandler handler) {
		// TODO Auto-generated method stub
		super.setErrorHandler(handler);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#getErrorHandler()
	 */
	@Override
	public ErrorHandler getErrorHandler() {
		// TODO Auto-generated method stub
		return super.getErrorHandler();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#parse(org.xml.sax.InputSource)
	 */
	@Override
	public void parse(InputSource input) throws SAXException, IOException {
		// TODO Auto-generated method stub
		super.parse(input);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#parse(java.lang.String)
	 */
	@Override
	public void parse(String systemId) throws SAXException, IOException {
		// TODO Auto-generated method stub
		super.parse(systemId);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#resolveEntity(java.lang.String, java.lang.String)
	 */
	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		// TODO Auto-generated method stub
		return super.resolveEntity(publicId, systemId);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#notationDecl(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		// TODO Auto-generated method stub
		super.notationDecl(name, publicId, systemId);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#unparsedEntityDecl(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.unparsedEntityDecl(name, publicId, systemId, notationName);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#setDocumentLocator(org.xml.sax.Locator)
	 */
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		super.setDocumentLocator(locator);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub
		super.startPrefixMapping(prefix, uri);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#endPrefixMapping(java.lang.String)
	 */
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		super.endPrefixMapping(prefix);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, atts);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#ignorableWhitespace(char[], int, int)
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.ignorableWhitespace(ch, start, length);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#processingInstruction(java.lang.String, java.lang.String)
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		super.processingInstruction(target, data);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#skippedEntity(java.lang.String)
	 */
	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		super.skippedEntity(name);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#warning(org.xml.sax.SAXParseException)
	 */
	@Override
	public void warning(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.warning(e);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#error(org.xml.sax.SAXParseException)
	 */
	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.error(e);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#fatalError(org.xml.sax.SAXParseException)
	 */
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.fatalError(e);
	}
	
	
}
