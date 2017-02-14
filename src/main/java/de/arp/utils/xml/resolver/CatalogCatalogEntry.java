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
public class CatalogCatalogEntry implements XMLCatalogEntry {

	private String value;
	
	
	/**
	 * Constructor
	 */
	public CatalogCatalogEntry() {
	}


	/**
	 * Constructor
	 * @param value
	 */
	public CatalogCatalogEntry(String value) {
		this.value = value;
	}


	/* (non-Javadoc)
	 * @see de.arp.utils.xml.resolver.XMLCatalogEntry#getEntryForResolver()
	 */
	@Override
	public CatalogEntry getEntryForResolver() throws CatalogException {
		Vector<String> v = new Vector<String>();
		v.add(value);
		CatalogEntry ret = new CatalogEntry(Catalog.CATALOG, v);
		return ret;
	}


	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		CatalogCatalogEntry other = (CatalogCatalogEntry) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CatalogCatalogEntry [value=%s]", value);
	}

	
}
