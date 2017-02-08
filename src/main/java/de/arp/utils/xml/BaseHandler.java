/**
 * 
 */
package de.arp.utils.xml;

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/**
 * This class implements the ContentHandler, LexicalHandler and DTDHandler interfaces.
 * It delegates to the embedded handlers
 * @author arp
 *
 */
public class BaseHandler implements ContentHandler, LexicalHandler, DTDHandler {

	private ContentHandler ch;
	private LexicalHandler lh;
	private DTDHandler dh;
	
	/**
	 * Default constructor
	 */
	public BaseHandler() {
		
	}
	
	/**
	 * Constructor
	 * @param ch	the content handler to use
	 */
	public BaseHandler(ContentHandler ch) {
		this(ch, null, null);
		if (ch instanceof LexicalHandler) {
			setLexicalHandler((LexicalHandler) ch);
		}
		if (ch instanceof DTDHandler) {
			setDTDHandler((DTDHandler) ch);
		}
	}

	/**
	 * Constructor
	 * @param ch	the content handler to use
	 * @param lh	the lexical handler to use
	 */
	public BaseHandler(ContentHandler ch, LexicalHandler lh) {
		this(ch, lh, null);
		if (ch instanceof DTDHandler) {
			setDTDHandler((DTDHandler) ch);
		}
	}

	/**
	 * Constructor
	 * @param ch	the content handler to use
	 * @param lh	the lexical handler to use
	 * @param dh	the DTD handler to use
	 */
	public BaseHandler(ContentHandler ch, LexicalHandler lh, DTDHandler dh) {
		this.ch = ch;
		this.lh = lh;
		this.dh = dh;
	}
	
	/**
	 * Constructor
	 * @param th	the TransformerHandler to use
	 */
	public BaseHandler(TransformerHandler th) {
		this(th, th, th);
	}
	
	/**
	 * @return the ContentHandler
	 */
	public ContentHandler getContentHandler() {
		return ch;
	}

	/**
	 * @param ch the ContentHandler to set
	 */
	public void setContentHandler(ContentHandler ch) {
		this.ch = ch;
	}

	/**
	 * @return the LexicalHandler
	 */
	public LexicalHandler getLexicalHandler() {
		return lh;
	}

	/**
	 * @param lh the LexicalHandler to set
	 */
	public void setLexicalHandler(LexicalHandler lh) {
		this.lh = lh;
	}

	/**
	 * @return the DTDHandler
	 */
	public DTDHandler getDTDHandler() {
		return dh;
	}

	/**
	 * @param dh the DTDHandler to set
	 */
	public void setDTDHandler(DTDHandler dh) {
		this.dh = dh;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	@Override
	public void setDocumentLocator(Locator locator) {
		if (ch != null) {
			ch.setDocumentLocator(locator);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		if (ch != null) {
			ch.startDocument();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		if (ch != null) {
			ch.endDocument();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		if (ch != null) {
			ch.startPrefixMapping(prefix, uri);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		if (ch != null) {
			ch.endPrefixMapping(prefix);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (ch != null) {
			ch.startElement(uri, localName, qName, atts);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (ch != null) {
			ch.endElement(uri, localName, qName);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (this.ch != null) {
			this.ch.characters(ch, start, length);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		if (this.ch != null) {
			this.ch.ignorableWhitespace(ch, start, length);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		if (this.ch != null) {
			this.ch.processingInstruction(target, data);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	@Override
	public void skippedEntity(String name) throws SAXException {
		if (this.ch != null) {
			this.ch.skippedEntity(name);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ext.LexicalHandler#startDTD(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException {
		if (this.lh != null) {
			this.lh.startDTD(name, publicId, systemId);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ext.LexicalHandler#endDTD()
	 */
	@Override
	public void endDTD() throws SAXException {
		if (this.lh != null) {
			this.lh.endDTD();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ext.LexicalHandler#startEntity(java.lang.String)
	 */
	@Override
	public void startEntity(String name) throws SAXException {
		if (this.lh != null) {
			this.lh.startEntity(name);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ext.LexicalHandler#endEntity(java.lang.String)
	 */
	@Override
	public void endEntity(String name) throws SAXException {
		if (this.lh != null) {
			this.lh.endEntity(name);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ext.LexicalHandler#startCDATA()
	 */
	@Override
	public void startCDATA() throws SAXException {
		if (this.lh != null) {
			this.lh.startCDATA();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ext.LexicalHandler#endCDATA()
	 */
	@Override
	public void endCDATA() throws SAXException {
		if (this.lh != null) {
			this.lh.endCDATA();
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ext.LexicalHandler#comment(char[], int, int)
	 */
	@Override
	public void comment(char[] ch, int start, int length) throws SAXException {
		if (this.lh != null) {
			this.lh.comment(ch, start, length);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.DTDHandler#notationDecl(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		if (this.dh != null) {
			this.dh.notationDecl(name, publicId, systemId);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.DTDHandler#unparsedEntityDecl(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)
			throws SAXException {
		if (this.dh != null) {
			this.dh.unparsedEntityDecl(name, publicId, systemId, notationName);
		}
	}

}
