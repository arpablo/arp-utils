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
public class CatalogDelegateSystemEntry implements XMLCatalogEntry {

	private String systemStartString;
	private String catalog;
	
	
	/**
	 * Constructor
	 */
	public CatalogDelegateSystemEntry() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Constructor
	 * @param publicStartString
	 * @param catalog
	 */
	public CatalogDelegateSystemEntry(String publicStartString, String catalog) {
		this.systemStartString = publicStartString;
		this.catalog = catalog;
	}


	/* (non-Javadoc)
	 * @see de.arp.utils.xml.resolver.XMLCatalogEntry#getEntryForResolver()
	 */
	@Override
	public CatalogEntry getEntryForResolver() throws CatalogException {
		Vector<String> v = new Vector<String>();
		v.add(systemStartString);
		v.add(catalog);
		CatalogEntry ret = new CatalogEntry(Catalog.DELEGATE_PUBLIC, v);
		return ret;
	}




	/**
	 * @return the systemStartString
	 */
	public String getSystemStartString() {
		return systemStartString;
	}


	/**
	 * @param systemStartString the systemStartString to set
	 */
	public void setSystemStartString(String systemStartString) {
		this.systemStartString = systemStartString;
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
		result = prime * result + ((systemStartString == null) ? 0 : systemStartString.hashCode());
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
		CatalogDelegateSystemEntry other = (CatalogDelegateSystemEntry) obj;
		if (catalog == null) {
			if (other.catalog != null)
				return false;
		} else if (!catalog.equals(other.catalog))
			return false;
		if (systemStartString == null) {
			if (other.systemStartString != null)
				return false;
		} else if (!systemStartString.equals(other.systemStartString))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CatalogDelegateSystemEntry [systemStartString=%s, catalog=%s]", systemStartString,
				catalog);
	}

	
}
