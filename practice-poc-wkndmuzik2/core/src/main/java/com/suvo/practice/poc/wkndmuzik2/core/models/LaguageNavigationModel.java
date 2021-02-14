package com.suvo.practice.poc.wkndmuzik2.core.models;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suvo.practice.poc.wkndmuzik2.core.utils.ProjectContstants;

@Model(adaptables = {Resource.class},
resourceType = "wkndmuzik2/components/languagenavigation",
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name="jackson",extensions="json")

public class LaguageNavigationModel {

	public static final Logger log = LoggerFactory.getLogger(LaguageNavigationModel.class);
	
	@ValueMapValue
	protected String navigationRoot;
	
	@ValueMapValue
	protected String structureDepth;
	
	@ValueMapValue
	protected String id;
	
	private Map<String, Object> langNavigationMap = new HashMap<String, Object>();
	
	@PostConstruct
	private void init()
	{
		log.debug("Inside LaguageNavigationModel init ");
		
		try {
			
			if(navigationRoot != null)
			{
				langNavigationMap.put(ProjectContstants.NAVIGATION_ROOT_NAV_COMP, navigationRoot);
			}
			if(structureDepth != null)
			{
				langNavigationMap.put(ProjectContstants.STRUCTURE_DEPTH_NAV_COMP, structureDepth);
			}
			if(id != null)
			{
				langNavigationMap.put(ProjectContstants.ID_COMP, id);
			}
			
			langNavigationMap.put(ProjectContstants.MOLECULE_NAME, ProjectContstants.LANGUAGE_NAVIGATION_COMP);
			
		} catch (Exception e) {
			log.error("Inside LaguageNavigationModel init ",e);
		}
	}
	
	public Map<String, Object> getLangNavigationMap()
	{
		return langNavigationMap;
	}
}
