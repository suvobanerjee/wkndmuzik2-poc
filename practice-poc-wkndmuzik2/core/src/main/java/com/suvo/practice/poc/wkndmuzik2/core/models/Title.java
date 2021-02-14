package com.suvo.practice.poc.wkndmuzik2.core.models;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suvo.practice.poc.wkndmuzik2.core.utils.ProjectContstants;

@Model(adaptables = Resource.class,
resourceType = "wkndmuzik2/components/title",
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name="jackson",extensions="json")


public class Title {

	public static final Logger log = LoggerFactory.getLogger(Title.class);
	
	@ValueMapValue
	String id;
	
	@ValueMapValue(name = "jcr:title")
	String title;
	
	@ValueMapValue
	String type;
	
	@SlingObject
	Resource currentRes;
	
	private Map<String, Object> titleMap = new HashMap<String, Object>();
	
	@PostConstruct
	private void init()
	{
		log.debug("Inside Title Model ");
		
		try {
			
			log.debug("Current res in Title: "+currentRes.getPath());
			
			titleMap.put(ProjectContstants.MOLECULE_NAME, ProjectContstants.TITLE_COMP);
			
			if(id != null)
			{
				titleMap.put(ProjectContstants.ID_COMP, id);
			}
			if(type != null)
			{
				titleMap.put(ProjectContstants.TYPE_TITLE_COMP, type);
			}
			if(title != null)
			{
				titleMap.put(ProjectContstants.TITLE_COMP, title);
			}
			
		} catch (Exception e) {
			log.error("Exception in Title Model :",e);
		}
	}
	
	
	public Map<String, Object> getTitleMap()
	{
		return titleMap;
	}
	
}
