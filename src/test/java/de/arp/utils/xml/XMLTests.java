/**
 * 
 */
package de.arp.utils.xml;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;
import org.junit.Assert;

/**
 * @author arp
 *
 */
public class XMLTests {

	@Test
	public void testIdentityTransformer() {
		Source src = new StreamSource(getClass().getResourceAsStream("/test1.xml"));
		ChainedFilterTransformer t = ChainedFilterTransformer.newSaxon_HETransformer();
		try {
			t.transform(src,  new StreamResult(System.out));
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void testStyleTransformer() {
		Source src = new StreamSource(getClass().getResourceAsStream("/test1.xml"));
		ChainedFilterTransformer t = ChainedFilterTransformer.newSaxon_HETransformer();
		try {
			Source style = new StreamSource(getClass().getResourceAsStream("/skipTest1.xsl"));
			t.addStyle(style);
			t.transform(src,  new StreamResult(System.out));
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void testUppercaseFilter() {
		Source src = new StreamSource(getClass().getResourceAsStream("/test1.xml"));
		ChainedFilterTransformer t = ChainedFilterTransformer.newSaxon_HETransformer();
		t.addFilter(new ElementNamesToUpperCaseFilter());
		try {
			t.transform(src,  new StreamResult(System.out));
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void testUppercaseLowercaseFilter() {
		Source src = new StreamSource(getClass().getResourceAsStream("/test1.xml"));
		ChainedFilterTransformer t = ChainedFilterTransformer.newSaxon_HETransformer();
		t.addFilter(new ElementNamesToUpperCaseFilter());
		t.addFilter(new ElementNamesToLowerCaseFilter());
		try {
			t.transform(src,  new StreamResult(System.out));
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void testUppercaseLowercaseStyleFilter() {
		Source src = new StreamSource(getClass().getResourceAsStream("/test1.xml"));
		ChainedFilterTransformer t = ChainedFilterTransformer.newSaxon_HETransformer();
		t.addFilter(new ElementNamesToUpperCaseFilter());
		t.addFilter(new ElementNamesToLowerCaseFilter());
		try {
			Source style = new StreamSource(getClass().getResourceAsStream("/skipTest1.xsl"));
			t.addStyle(style);
			t.transform(src,  new StreamResult(System.out));
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

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

	public class ElementNamesToLowerCaseFilter extends XMLFilterImpl {

		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.XMLFilterImpl#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			super.startElement(uri.toLowerCase(), localName.toLowerCase(), qName.toLowerCase(), atts);
		}

		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.XMLFilterImpl#endElement(java.lang.String, java.lang.String, java.lang.String)
		 */
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri.toLowerCase(), localName.toLowerCase(), qName.toLowerCase());
		}
		
	}

}

