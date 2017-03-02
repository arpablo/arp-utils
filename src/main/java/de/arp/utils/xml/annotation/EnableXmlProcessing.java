/**
 * 
 */
package de.arp.utils.xml.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * <p>Enable XML processing. This annotation</p> 
 * 
 * <p>Example</p>
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableXmlProcessing(xsltProcessor=XslProcessor.SAXON_HE, catalogType=CatalogType.TEXT, catalogUrl="http://test.com/catalog.cat")
 * public class AppConfig {
 * ...
 * }
 * </pre>
 * @author arp
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Import(XmlConfiguration.class)
public @interface EnableXmlProcessing {

	enum XslProcessor {DEFAULT, SAXON_HE, SAXON_PE, SAXON_EE, XALAN };
	enum CatalogType {TEXT, XML};
	
	XslProcessor xslProcessor() default XslProcessor.DEFAULT;
	CatalogType catalogType() default CatalogType.XML;
	String catalogUrl() default "";
	
}
