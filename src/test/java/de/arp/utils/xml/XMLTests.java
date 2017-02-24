/**
 * 
 */
package de.arp.utils.xml;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.Test;

import de.arp.utils.xml.filter.ElementNamesToLowerCaseFilter;
import de.arp.utils.xml.filter.ElementNamesToUpperCaseFilter;

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


}

