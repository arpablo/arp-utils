/**
 * 
 */
package de.arp.utils.xml.resolver;

import org.apache.xml.resolver.CatalogEntry;
import org.apache.xml.resolver.CatalogException;

/**
 * @author arp
 *
 */
public interface XMLCatalogEntry {

	/**
	 * Return a CatalogEntry that can be used by the Apache XML resolver
	 * @return a CatalogEntry
	 */
	public CatalogEntry getEntryForResolver() throws CatalogException;
}
