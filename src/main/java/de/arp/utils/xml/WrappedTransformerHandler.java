/**
 * 
 */
package de.arp.utils.xml;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * @author arp
 *
 */
public class WrappedTransformerHandler implements TransformerHandler {

	TransformerHandler th;

	/**
	 * Constructor
	 * @param th	the TransformerHandler to use
	 */
	public WrappedTransformerHandler(TransformerHandler th) {
		this.th = th;
	}
	
	@Override
	public void setDocumentLocator(Locator locator) {
		if (this.th != null) {
			this.th.setDocumentLocator(locator);
		}
	}

	@Override
	public void startDocument() throws SAXException {
		if (this.th != null) {
			th.startDocument();
		}
	}

	@Override
	public void endDocument() throws SAXException {
		if (this.th != null) {
			th.endDocument();
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		if (this.th != null) {
			th.startPrefixMapping(prefix, uri);
		}
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		if (this.th != null) {
			th.endPrefixMapping(prefix);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (this.th != null) {
			th.startElement(uri, localName, qName, atts);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (this.th != null) {
			th.endElement(uri, localName, qName);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (this.th != null) {
			th.characters(ch, start, length);
		}
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		if (this.th != null) {
			th.ignorableWhitespace(ch, start, length);
		}
	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		if (this.th != null) {
			th.processingInstruction(target, data);
		}
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		if (this.th != null) {
			th.skippedEntity(name);
		}
	}

	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException {
		if (this.th != null) {
			th.startDTD(name, publicId, systemId);
		}
	}

	@Override
	public void endDTD() throws SAXException {
		if (this.th != null) {
			th.endDTD();
		}
	}

	@Override
	public void startEntity(String name) throws SAXException {
		if (this.th != null) {
			th.startEntity(name);
		}
	}

	@Override
	public void endEntity(String name) throws SAXException {
		if (this.th != null) {
			th.endEntity(name);
		}
	}

	@Override
	public void startCDATA() throws SAXException {
		th.startCDATA();
	}

	@Override
	public void endCDATA() throws SAXException {
		th.endCDATA();
	}

	@Override
	public void comment(char[] ch, int start, int length) throws SAXException {
		th.comment(ch, start, length);
	}

	@Override
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		th.notationDecl(name, publicId, systemId);
	}

	@Override
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)
			throws SAXException {
		if (this.th != null) {
			th.unparsedEntityDecl(name, publicId, systemId, notationName);
		}
	}

	@Override
	public void setResult(Result result) throws IllegalArgumentException {
		if (this.th != null) {
			th.setResult(result);
		}
	}

	@Override
	public void setSystemId(String systemID) {
		if (this.th != null) {
			th.setSystemId(systemID);
		}
	}

	@Override
	public String getSystemId() {
		if (this.th != null) {
			return th.getSystemId();
		}
		return null;
	}

	@Override
	public Transformer getTransformer() {
		if (this.th != null) {
			return th.getTransformer();
		}
		return null;
	}
}
