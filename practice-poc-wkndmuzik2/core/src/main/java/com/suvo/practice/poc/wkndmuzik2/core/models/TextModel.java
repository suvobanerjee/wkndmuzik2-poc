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
resourceType = "wkndmuzik2/components/text",
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name="jackson",extensions="json")


public class TextModel {

	public static final Logger log = LoggerFactory.getLogger(TextModel.class);
	
	@ValueMapValue
	String text;
	
	@ValueMapValue
	String textIsRich;
	
	@SlingObject
	Resource currentRes;
	
	private Map<String, Object> textMap = new HashMap<String, Object>();
	
	
	@PostConstruct
	private void init()
	{
		log.debug("Inside text model:: ");
		
		try {
			
			textMap.put(ProjectContstants.MOLECULE_NAME, ProjectContstants.TEXT_COMP);
			
			if(text != null)
			{
				textMap.put(ProjectContstants.TEXT_COMP, text);
				log.debug("Text-> "+text);
			}
			if(textIsRich != null)
			{
				textMap.put(ProjectContstants.TEXT_IS_RICH_TEXT_COMP, textIsRich);
			}
			
		} catch (Exception e) {
			log.error("Exception in Text model ",e);
		}
	}
	
	
	public Map<String, Object> getTextMap()
	{
		return textMap;
	}
}
