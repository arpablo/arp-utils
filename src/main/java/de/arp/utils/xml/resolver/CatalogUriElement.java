/**
 * 
 */
package de.arp.utils.xml.resolver;

import java.util.Vector;

import org.apache.xml.resolver.Catalog;
import org.apache.xml.resolver.CatalogEntry;
import org.apache.xml.resolver.CatalogException;

/**
 * @author arp
 *
 */
public class CatalogUriElement implements XMLCatalogEntry {

	private String name;
	private String uri;
	
	
	/**
	 * Constructor
	 */
	public CatalogUriElement() {
	}


	/**
	 * Constructor
	 * @param name
	 * @param uri
	 */
	public CatalogUriElement(String name, String uri) {
		this.name = name;
		this.uri = uri;
	}


	/* (non-Javadoc)
	 * @see de.arp.utils.xml.resolver.XMLCatalogEntry#getEntryForResolver()
	 */
	@Override
	public CatalogEntry getEntryForResolver() throws CatalogException {
		Vector<String> v = new Vector<String>();
		v.add(name);
		v.add(uri);
		CatalogEntry ret = new CatalogEntry(Catalog.URI, v);
		return ret;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}


	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogUriElement other = (CatalogUriElement) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CatalogUriElement [name=%s, uri=%s]", name, uri);
	}

	
	
}
