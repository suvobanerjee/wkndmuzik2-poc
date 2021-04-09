package com.suvo.practice.poc.wkndmuzik2.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class PageExporterTest {

	
	private PageExporter exporter;
	private SlingHttpServletRequest req;
	
	
	@BeforeEach
	 public void setup(AemContext context) throws Exception
	 {
		req = context.request();
		exporter = req.adaptTo(PageExporter.class);
	 }
	
	@Test
	void testGetPageObject() {
		
		Map<String, Object> pageObject = exporter.getPageObject();
		assertNotNull(pageObject);
	}

}
