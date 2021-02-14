package com.suvo.practice.poc.wkndmuzik2.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.scripting.jsp.taglib.GetResourceTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.wcm.core.components.models.Container;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suvo.practice.poc.wkndmuzik2.core.utils.ProjectContstants;
import com.suvo.practice.poc.wkndmuzik2.core.utils.Utils;

@Model(adaptables = {SlingHttpServletRequest.class},
		resourceType = "wkndmuzik2/components/page",
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)

@Exporter(name="jackson",extensions="json")


public class PageExporter {
	

	public static final Logger log = LoggerFactory.getLogger(PageExporter.class);
	
	/*
	 * @Self private Resource resource;
	 */
	
	@SlingObject
	private SlingHttpServletRequest req;
	
	@ValueMapValue(name = "text")
	private String pageHeading;
	
	@ValueMapValue
	@Named("cq:template")
	private String templatePath;
	
	@SlingObject
	Resource currentRes;
	
	//private Container container;
	
	private Map<String, Object> pageObject = new HashMap<String, Object>();
	
	@PostConstruct
	private void init()
	{
		log.debug("Inside init method !!!");
		log.debug("page heading: "+pageHeading);
		
		try 
		{
			templateJsonGenerator();
			pageBodyJsonGenerator();
			
		}
		catch(Exception e)
		{
			log.error("Exception in Page Exporter ",e);
		}
		
	}

	private void templateJsonGenerator() {
		pageObject.put(ProjectContstants.PAGE_HEADING_PAGE_COMP,pageHeading);
		pageObject.put(ProjectContstants.TEMPLATE_PATH_PAGE_COMP, templatePath);

		ResourceResolver resolver = req.getResourceResolver();
		Resource res = resolver.getResource(templatePath);
		pageObject.put("resPath", res.getPath());
		
		if(res.hasChildren())
		{
			Resource structureRes = res.getChild("structure/jcr:content");
			
			log.debug("structureRes: "+structureRes.getPath());
			
			Iterator<Resource> structureChildList = structureRes.listChildren();
			while(structureChildList.hasNext())
			{
				Resource structureChildRes = structureChildList.next();
				String structureChildListName = structureChildRes.getName();
				log.debug("structureChildList= "+structureChildListName);
				if(structureChildListName.equalsIgnoreCase(ProjectContstants.ROOT))
				{
					Iterator<Resource> rootList = structureChildRes.listChildren();
					while(rootList.hasNext())
					{
						Resource rootChildRes = rootList.next();
						log.debug("rootListChildRes: "+rootChildRes.getPath());
						if(rootChildRes.getName().contains(ProjectContstants.EXPERIENCE_FRAGMENT))
						{
							ValueMap rootChildResValueMap = rootChildRes.getValueMap();
							String mapKeyName = rootChildResValueMap.get("id").toString();
							String fragVarPath = rootChildResValueMap.get("fragmentVariationPath").toString();
							if(fragVarPath != null)
							{
								fragVarPath = fragVarPath + "/jcr:content/root";
								Resource fragVarRes = resolver.getResource(fragVarPath);
								log.debug("Utils Innermap: "+ Utils.assignModels(fragVarRes));
								
								pageObject.put(mapKeyName, Utils.assignModels(fragVarRes));
								log.debug("fragVarRes path: "+fragVarRes.getPath());
								
							}
							log.debug("mapKeyName: "+mapKeyName);
							//pageObject.put(mapKeyName, "TBD");
									
						}
					}
				}
			}
			 
		}
	}
	
	
	
	private void pageBodyJsonGenerator()
	{
		List<Object> moleculeArr = new ArrayList<Object>();
		Map<String, Object> pageInnerMap = new HashMap<String, Object>();
		try {
			log.debug("currentRes in pageExporter: "+currentRes.getPath());
			
			Resource pageRootRes = currentRes.getChild("root");
			log.debug("pageRootRes: "+pageRootRes.getPath());
			
			Iterator<Resource> pageChildListIterator = pageRootRes.listChildren();
			while(pageChildListIterator.hasNext())
			{
				Resource pageChildRes = pageChildListIterator.next();
				Utils.modelsClassification(pageChildRes, pageInnerMap);
			}
			
			moleculeArr.add(pageInnerMap);
			pageObject.put(ProjectContstants.MOLECULES, moleculeArr);
			
		} catch (Exception e) {
			log.error("Exception in pageBodyJsonGEnerator: ",e);
		}
	}

	/*
	 * @JsonProperty(value = "goodbye-world") public String goodbyeWorld() { return
	 * "Goodbye World"; }
	 */
	 
	/*
	 * @JsonProperty(value = "pageHeading") public String getPageHeading() { return
	 * pageHeading; }
	 */
	 
	 @JsonProperty(value = "page")
	    public Map<String, Object> getPageObject() {
	        return pageObject;
	    }
	 
	 
}
