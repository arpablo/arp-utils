/**
 * 
 */
package de.arp.utils.xml.resolver;

import java.util.Vector;

import org.apache.xml.resolver.Catalog;
import org.apache.xml.resolver.CatalogException;

/**
 * @author arp
 *
 */
public class CatalogNG extends Catalog {

	/**
	 * Constructor
	 */
	public CatalogNG() {
		setupReaders();
		try {
			loadSystemCatalogs();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Return the catalog entries
	 * @return a Vector of CatalogEntry instances
	 */
	@SuppressWarnings("rawtypes")
	public Vector getCatalogEntries() {
		return catalogEntries;
	}
	
	/**
	 * Add an entry to this catalog
	 * @param entry
	 * @throws CatalogException
	 */
	public void addEntry(XMLCatalogEntry entry) throws CatalogException {
		this.addEntry(entry.getEntryForResolver());
	}
}
