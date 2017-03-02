/**
 * 
 */
package de.arp.utils.xml.annotation;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;

import org.apache.xml.resolver.tools.CatalogResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

/**
 * This is a configuration class used by the EnambelXmlProcessing annotation
 * @author arp
 *
 */
@Configuration
public class XmlConfiguration implements ImportAware {

	public static final String XALAN_FACTORY = "org.apache.xalan.processor.TransformerFactoryImpl";
	public static final String SAXON_HE_FACTORY = "net.sf.saxon.TransformerFactoryImpl";
	public static final String SAXON_PE_FACTORY = "com.saxonica.config.ProfessionalTransformerFactory"; 
	public static final String SAXON_EE_FACTORY = "com.saxonica.config.EnterpriseTransformerFactory";	
	
	@Autowired
	private ApplicationContext ctx;
	
	private EnableXmlProcessing.XslProcessor processor = EnableXmlProcessing.XslProcessor.SAXON_HE;
	private EnableXmlProcessing.CatalogType catalogType = EnableXmlProcessing.CatalogType.XML;
	
	private String catalogUrl;
	
	@Bean
	public SAXTransformerFactory saxTransformerFactory() {
		SAXTransformerFactory ret = null;
		if (processor == EnableXmlProcessing.XslProcessor.SAXON_HE) {
			ret = (SAXTransformerFactory) TransformerFactory.newInstance(SAXON_HE_FACTORY, Thread.currentThread().getContextClassLoader());
		} else if (processor == EnableXmlProcessing.XslProcessor.SAXON_PE) {
			ret = (SAXTransformerFactory) TransformerFactory.newInstance(SAXON_PE_FACTORY, Thread.currentThread().getContextClassLoader());
		} else if (processor == EnableXmlProcessing.XslProcessor.SAXON_EE) {
			ret = (SAXTransformerFactory) TransformerFactory.newInstance(SAXON_EE_FACTORY, Thread.currentThread().getContextClassLoader());
		} else if (processor == EnableXmlProcessing.XslProcessor.XALAN) {
			ret = (SAXTransformerFactory) TransformerFactory.newInstance(XALAN_FACTORY, Thread.currentThread().getContextClassLoader());		
		} else {
			ret = (SAXTransformerFactory) TransformerFactory.newInstance();
		}
		return ret;
	}

	@Bean
	public CatalogResolver catalogResolver() {
		CatalogResolver resolver = new CatalogResolver();
		if (catalogUrl != null && catalogUrl.length() > 0) {
			try {
				Resource res = ctx.getResource(catalogUrl);
				resolver.getCatalog().parseCatalog(getMimeTypeForCatalogType(catalogType), res.getInputStream());
			} catch (Exception ex) {
				throw new RuntimeException("Failed to read catalog-file " + catalogUrl, ex);
			}
		}
		return resolver;
	}

	private String getMimeTypeForCatalogType(EnableXmlProcessing.CatalogType catalogType) {
		if (catalogType == EnableXmlProcessing.CatalogType.TEXT) {
			return "text/plain";
		}
		return "application/xml";
	}
	
	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		AnnotationAttributes atts = AnnotationAttributes.fromMap(importMetadata.getAnnotationAttributes(
				EnableXmlProcessing.class.getName(), false));
		Assert.notNull(atts,
				"@EnableXmlProcessing is not present on importing class " + importMetadata.getClassName());
		if (atts.containsKey("xslProcessor")) {
			processor = atts.getEnum("xslProcessor");
		}
		if (atts.containsKey("catalogType")) {
			catalogType = atts.getEnum("catalogType");
		}
		if (atts.containsKey("catalogUrl")) {
			catalogUrl = atts.getString("catalogUrl");
		}
	}
}
