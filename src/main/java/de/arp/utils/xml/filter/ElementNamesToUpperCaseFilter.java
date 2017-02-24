/**
 * 
 */
package de.arp.utils.xml.filter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * @author arp
 *
 */
public class ElementNamesToUpperCaseFilter extends XMLFilterImpl {

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		super.startElement(uri.toUpperCase(), localName.toUpperCase(), qName.toUpperCase(), atts);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLFilterImpl#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri.toUpperCase(), localName.toUpperCase(), qName.toUpperCase());
	}

}
