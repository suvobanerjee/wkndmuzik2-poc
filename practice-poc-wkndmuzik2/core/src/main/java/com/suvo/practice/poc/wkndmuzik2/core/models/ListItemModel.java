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

@Model(adaptables = {Resource.class},
resourceType = "wkndmuzik2/components/image",
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name="jackson",extensions="json")


public class ListItemModel {
	
	
	public static final Logger log = LoggerFactory.getLogger(ListItemModel.class);
	
	@SlingObject
	Resource currentRes;
	
	@ValueMapValue 
	protected String text;
	
	@ValueMapValue
	protected String length;
	
	private Map<String, Object> listItemMap = new HashMap<String, Object>();

	@PostConstruct
	private void init()
	{
		log.debug("Inside listItem init: "+currentRes.getPath());
		
		try {
			
			listItemMap.put(ProjectContstants.MOLECULE_NAME, ProjectContstants.LISTITEM_COMP);
			
			if( text != null)
			{
				listItemMap.put(ProjectContstants.TEXT_COMP, text);
			}
			
			if(length != null)
			{
				listItemMap.put(ProjectContstants.LENGTH_LISTITEM_COMP, length);
			}
			
		} catch (Exception e) {
			log.error("Exception in ListItem model ",e);
		}
	}
	
	
	 public Map<String, Object> getListItemMap() {
	        return listItemMap;
	    }

}
