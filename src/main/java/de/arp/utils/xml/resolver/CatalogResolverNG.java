/**
 * 
 */
package de.arp.utils.xml.resolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;

import org.apache.xml.resolver.helpers.FileURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * This class is based on the XMLResolver implementation by the Apache group.
 * 
 * @author arp
 *
 */
public class CatalogResolverNG implements EntityResolver, URIResolver {

	private static Logger logger = LoggerFactory.getLogger(CatalogResolverNG.class);

	private CatalogNG catalog = new CatalogNG();

	
	/**
	 * @return the catalog
	 */
	public CatalogNG getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(CatalogNG catalog) {
		this.catalog = catalog;
	}

	/**
	 * Return the resolved entity for the given identifiers
	 * 
	 * @param publicId
	 * @param systemId
	 * @return
	 */
	public String getResolvedEntity(String publicId, String systemId) {
		String resolved = null;

		if (catalog == null) {
			logger.debug("Catalog resolution attempted with null catalog; ignored");
			return null;
		}

		if (systemId != null) {
			try {
				resolved = catalog.resolveSystem(systemId);
			} catch (MalformedURLException me) {
				logger.trace("Malformed URL exception trying to resolve '{}'", publicId);
				resolved = null;
			} catch (IOException ie) {
				logger.trace("I/O exception trying to resolve '{}'", publicId);
				resolved = null;
			}
		}

		if (resolved == null) {
			if (publicId != null) {
				try {
					resolved = catalog.resolvePublic(publicId, systemId);
				} catch (MalformedURLException me) {
					logger.trace("Malformed URL exception trying to resolve '{}'", publicId);
				} catch (IOException ie) {
					logger.trace("I/O exception trying to resolve '{}'", publicId);
				}
			}

			if (resolved != null) {
				logger.debug("Resolved public '{}' to '{}'", publicId, resolved);
			}
		} else {
			logger.debug("Resolved system '{}' to '{}'", systemId, resolved);
		}

		return resolved;
	}

	@Override
	public Source resolve(String href, String base) throws TransformerException {
		String uri = href;
		int hashPos = href.indexOf("#");
		if (hashPos >= 0) {
			uri = href.substring(0, hashPos);
		}

		String result = null;

		try {
			result = catalog.resolveURI(href);
		} catch (Exception e) {
			// nop;
		}

		if (result == null) {
			try {
				URL url = null;

				if (base == null) {
					url = new URL(uri);
					result = url.toString();
				} else {
					URL baseURL = new URL(base);
					url = (href.length() == 0 ? baseURL : new URL(baseURL, uri));
					result = url.toString();
				}
			} catch (java.net.MalformedURLException mue) {
				// try to make an absolute URI from the current base
				String absBase = makeAbsolute(base);
				if (!absBase.equals(base)) {
					// don't bother if the absBase isn't different!
					return resolve(href, absBase);
				} else {
					throw new TransformerException("Malformed URL " + href + "(base " + base + ")", mue);
				}
			}
		}

		logger.debug("Resolved URI '{}' to '{}'", href, result);

		SAXSource source = new SAXSource();
		source.setInputSource(new InputSource(result));
		setEntityResolver(source);
		return source;
	}

	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		String resolved = getResolvedEntity(publicId, systemId);

		if (resolved != null) {
			try {
				InputSource iSource = new InputSource(resolved);
				iSource.setPublicId(publicId);
				URL url = new URL(resolved);
				InputStream iStream = url.openStream();
				iSource.setByteStream(iStream);

				return iSource;
			} catch (Exception e) {
				logger.info("Failed to create InputSource (" + e.toString() + ") for '{}'", resolved);
				return null;
			}
		}

		return null;
	}

	/**
	 * <p>
	 * Establish an entityResolver for newly resolved URIs.
	 * </p>
	 *
	 * <p>
	 * This is called from the URIResolver to set an EntityResolver on the SAX
	 * parser to be used for new XML documents that are encountered as a result
	 * of the document() function, xsl:import, or xsl:include. This is done
	 * because the XSLT processor calls out to the SAXParserFactory itself to
	 * create a new SAXParser to parse the new document. The new parser does not
	 * automatically inherit the EntityResolver of the original (although
	 * arguably it should). See below:
	 * </p>
	 *
	 * <tt>"If an application wants to set the ErrorHandler or
	 * EntityResolver for an XMLReader used during a transformation,
	 * it should use a URIResolver to return the SAXSource which
	 * provides (with getXMLReader) a reference to the XMLReader"</tt>
	 *
	 * <p>
	 * ...quoted from page 118 of the Java API for XML Processing 1.1
	 * specification
	 * </p>
	 *
	 */
	private void setEntityResolver(SAXSource source) throws TransformerException {
		XMLReader reader = source.getXMLReader();
		if (reader == null) {
			SAXParserFactory spFactory = SAXParserFactory.newInstance();
			spFactory.setNamespaceAware(true);
			try {
				reader = spFactory.newSAXParser().getXMLReader();
			} catch (ParserConfigurationException ex) {
				throw new TransformerException(ex);
			} catch (SAXException ex) {
				throw new TransformerException(ex);
			}
		}
		reader.setEntityResolver(this);
		source.setXMLReader(reader);
	}

	/** Attempt to construct an absolute URI */
	private String makeAbsolute(String uri) {
		if (uri == null) {
			uri = "";
		}

		try {
			URL url = new URL(uri);
			return url.toString();
		} catch (MalformedURLException mue) {
			try {
				URL fileURL = FileURL.makeURL(uri);
				return fileURL.toString();
			} catch (MalformedURLException mue2) {
				// bail
				return uri;
			}
		}
	}

}
