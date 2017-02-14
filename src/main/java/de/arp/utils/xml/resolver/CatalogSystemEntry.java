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
public class CatalogSystemEntry implements XMLCatalogEntry {

	private String systemId;
	private String fsi;
	
	/**
	 * Default Constructor
	 */
	public CatalogSystemEntry() {
		
	}
	
	/**
	 * Constructor
	 * @param systemId
	 * @param fsi
	 */
	public CatalogSystemEntry(String systemId, String fsi) {
		this.systemId = systemId;
		this.fsi = fsi;
	}
	
	
	/* (non-Javadoc)
	 * @see de.arp.utils.xml.resolver.XMLCatalogEntry#getEntryForResolver()
	 */
	@Override
	public CatalogEntry getEntryForResolver() throws CatalogException {
		Vector<String> v = new Vector<String>();
		v.add(systemId);
		v.add(fsi);
		CatalogEntry ret = new CatalogEntry(Catalog.SYSTEM, v);
		return ret;
	}

	/**
	 * @return the systemId
	 */
	public String getSystemId() {
		return systemId;
	}
	/**
	 * @param systemId the systemId to set
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	/**
	 * @return the fsi
	 */
	public String getFsi() {
		return fsi;
	}
	/**
	 * @param fsi the fsi to set
	 */
	public void setFsi(String fsi) {
		this.fsi = fsi;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
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
		CatalogSystemEntry other = (CatalogSystemEntry) obj;
		if (systemId == null) {
			if (other.systemId != null)
				return false;
		} else if (!systemId.equals(other.systemId))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SystemEntry [systemId=%s, fsi=%s]", systemId, fsi);
	}
	
	
}
