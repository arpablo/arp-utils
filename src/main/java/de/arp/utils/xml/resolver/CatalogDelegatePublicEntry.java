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
public class CatalogDelegatePublicEntry implements XMLCatalogEntry {

	private String publicStartString;
	private String catalog;
	
	
	/**
	 * Constructor
	 */
	public CatalogDelegatePublicEntry() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Constructor
	 * @param publicStartString
	 * @param catalog
	 */
	public CatalogDelegatePublicEntry(String publicStartString, String catalog) {
		this.publicStartString = publicStartString;
		this.catalog = catalog;
	}


	/* (non-Javadoc)
	 * @see de.arp.utils.xml.resolver.XMLCatalogEntry#getEntryForResolver()
	 */
	@Override
	public CatalogEntry getEntryForResolver() throws CatalogException {
		Vector<String> v = new Vector<String>();
		v.add(publicStartString);
		v.add(catalog);
		CatalogEntry ret = new CatalogEntry(Catalog.DELEGATE_PUBLIC, v);
		return ret;
	}


	/**
	 * @return the publicStartString
	 */
	public String getPublicStartString() {
		return publicStartString;
	}


	/**
	 * @param publicStartString the publicStartString to set
	 */
	public void setPublicStartString(String publicStartString) {
		this.publicStartString = publicStartString;
	}


	/**
	 * @return the catalog
	 */
	public String getCatalog() {
		return catalog;
	}


	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catalog == null) ? 0 : catalog.hashCode());
		result = prime * result + ((publicStartString == null) ? 0 : publicStartString.hashCode());
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
		CatalogDelegatePublicEntry other = (CatalogDelegatePublicEntry) obj;
		if (catalog == null) {
			if (other.catalog != null)
				return false;
		} else if (!catalog.equals(other.catalog))
			return false;
		if (publicStartString == null) {
			if (other.publicStartString != null)
				return false;
		} else if (!publicStartString.equals(other.publicStartString))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CatalogDelegatePublicEntry [publicStartString=%s, catalog=%s]", publicStartString,
				catalog);
	}

	
}
