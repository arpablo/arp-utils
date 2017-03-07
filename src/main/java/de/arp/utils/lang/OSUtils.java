/**
 * 
 */
package de.arp.utils.lang;

/**
 * This class contains some methods for dealing with operating system environments
 * @author arp
 *
 */
public class OSUtils {

	/**
	 * Return the name of the operating system
	 * @return
	 */
	public static final String getOSName() {
		return System.getProperty("os.name");
	}

	/**
	 * Return true, if the operating system is windows
	 * @return
	 */
	public static final boolean isOSWindows() {
		return getOSName() == null ? false : getOSName().startsWith("Windows");
	}
	
	
}
