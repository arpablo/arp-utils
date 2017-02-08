/**
 * 
 */
package de.arp.utils.xml.resolver;

import java.util.Vector;

import org.apache.xml.resolver.Catalog;
import org.apache.xml.resolver.CatalogEntry;
import org.apache.xml.resolver.CatalogException;

/**
 * This class implements a PUBLIC entry in an
 * XML catalog
 * @author arp
 *
 */
public class PublicEntry implements XMLCatalogEntry {

	private String publicId;
	private String systemId;
	
	
	/**
	 * Default constructor
	 */
	public PublicEntry() {
		
	}
	
	/**
	 * Constructor
	 * @param publicId
	 * @param systemId
	 */
	public PublicEntry(String publicId, String systemId) {
		this.publicId = publicId;
		this.systemId = systemId;
	}

	/* (non-Javadoc)
	 * @see de.arp.utils.xml.resolver.XMLCatalogEntry#getEntryForResolver()
	 */
	@Override
	public CatalogEntry getEntryForResolver() throws CatalogException {
		Vector<String> v = new Vector<String>();
		v.add(publicId);
		v.add(systemId);
		CatalogEntry ret = new CatalogEntry(Catalog.PUBLIC, v);
		return ret;
	}
	
	/**
	 * @return the publicId
	 */
	public String getPublicId() {
		return publicId;
	}
	/**
	 * @param publicId the publicId to set
	 */
	public void setPublicId(String publicId) {
		this.publicId = publicId;
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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publicId == null) ? 0 : publicId.hashCode());
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
		PublicEntry other = (PublicEntry) obj;
		if (publicId == null) {
			if (other.publicId != null)
				return false;
		} else if (!publicId.equals(other.publicId))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("PublicEntry [publicId=%s, systemId=%s]", publicId, systemId);
	}
	
	
}
