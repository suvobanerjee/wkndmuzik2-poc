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
		resourceType = "wkndmuzik2/components/navigation",
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)

@Exporter(name="jackson",extensions="json")

public class NavigationModel {
	
	public static final Logger log = LoggerFactory.getLogger(NavigationModel.class);
	
	@ValueMapValue
	protected String navigationRoot;
	
	@ValueMapValue
	protected String structureStart;
	
	@ValueMapValue
	protected String collectAllPages;
	
	@ValueMapValue
	protected String structureDepth;
	
	private Map<String, Object> navigationMap = new HashMap<String, Object>();
	
	@PostConstruct
	private void init()
	{
		log.debug("Inside navigationModel init ");
		
		try
		{
			if(navigationRoot != null)
			{
				navigationMap.put(ProjectContstants.NAVIGATION_ROOT_NAV_COMP, navigationRoot);
			}
			if(structureStart != null)
			{
				navigationMap.put(ProjectContstants.STRUCTURE_START_NAV_COMP, structureStart);
			}
			if(structureDepth != null)
			{
				navigationMap.put(ProjectContstants.STRUCTURE_DEPTH_NAV_COMP, structureDepth);
			}
			
			navigationMap.put(ProjectContstants.MOLECULE_NAME, ProjectContstants.NAVIGATION_COMP);
			
		}
		catch (Exception e) {
			log.error("Exception in navigationModel ",e);
		}
	}
	
	
	public Map<String, Object> getNavigationMap() {
        return navigationMap;
    }
	

}
