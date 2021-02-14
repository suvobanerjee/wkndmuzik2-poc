package com.suvo.practice.poc.wkndmuzik2.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.wcm.core.components.models.Container;
import com.suvo.practice.poc.wkndmuzik2.core.utils.ProjectContstants;
import com.suvo.practice.poc.wkndmuzik2.core.utils.Utils;;

@Model(adaptables = Resource.class,
		resourceType = "wkndmuzik2/components/container",
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)

@Exporter(name="jackson",extensions="json")

public class ContainerModel {
	
	public static final Logger log = LoggerFactory.getLogger(ContainerModel.class);
	
	@SlingObject
	Resource currentRes;
	
	@ValueMapValue
	String layout;
	
	@ValueMapValue
	String backgroundColor;
	
	@ValueMapValue
	String id;
	
	private Map<String, Object> containerMap = new HashMap<String, Object>();
	private List<Object> containerArr = new ArrayList<Object>();
	
	
	@PostConstruct
	private void init()
	{
		log.debug("Inside Container model init");
		
		try {
			log.debug("currentRes: "+currentRes.getPath());
			
			containerMap.put(ProjectContstants.MOLECULE_NAME,ProjectContstants.CONTAINER_COMP);
			if(layout != null)
			{
				containerMap.put(ProjectContstants.LAYOUT_CONTAINER_COMP, layout);
			}
			if(backgroundColor != null)
			{
				containerMap.put(ProjectContstants.BACKGROUND_COLOR_CONTAINER_COMP, backgroundColor);
			}
			
			if(currentRes.hasChildren())
			{
					Iterator<Resource> containerChildIterator = currentRes.listChildren();
					
					while(containerChildIterator.hasNext())
					{
						Map<String, Object> containerinnerMap = new HashMap<String, Object>();
						//Map<String, Object> containerArrMap = new HashMap<String, Object>();
						
						Resource containerChildRes = containerChildIterator.next();
						log.debug("containerChildRes: "+containerChildRes.getPath());
						log.debug("containerChildRes Name: "+containerChildRes.getName());
						if(!containerChildRes.getPath().contains(ProjectContstants.RESPONSIVE))
						{
							Utils.modelsClassification(containerChildRes, containerinnerMap);
							//containerArrMap.put(ProjectContstants.MOLECULE, containerinnerMap);
							log.debug("containerinnerMap ->> "+containerinnerMap);
							containerArr.add(containerinnerMap);
						}
						else
						{
							Iterator<Resource> responsiveResIterator = containerChildRes.listChildren();
							while(responsiveResIterator.hasNext())
							{
								Resource responsiveRes = responsiveResIterator.next();
								log.debug("responsiveRes "+ responsiveRes.getPath());
								if(responsiveRes != null)
								{
									ValueMap responsiveResVal = responsiveRes.getValueMap();
									if(responsiveResVal.containsKey(ProjectContstants.OFFSET))
									{
										containerMap.put(ProjectContstants.OFFSET, responsiveResVal.get(ProjectContstants.OFFSET));
									}
									if(responsiveResVal.containsKey(ProjectContstants.WIDTH))
									{
										containerMap.put(ProjectContstants.WIDTH, responsiveResVal.get(ProjectContstants.WIDTH));
									}
								
								}
							}
						}
						
					}
	
	
					/*
					 * if(!containerinnerMap.isEmpty()) {
					 * log.debug("containerinnerMap size "+containerinnerMap.size() );
					 * for(Map.Entry<String, Object> moleculeMapEntry :
					 * containerinnerMap.entrySet()) {
					 * containerArrMap.put(ProjectContstants.MOLECULE, moleculeMapEntry); } }
					 */
					
					//containerArr.add(containerArrMap);
					log.debug("Container Arr: "+containerArr);
					containerMap.put(ProjectContstants.MOLECULES,containerArr);
			}
		} 
		catch (Exception e) {
			log.error("Exception in ContainerModel ",e);
		}
	}
	
	
	public Map<String, Object> getContainerMap()
	{
		return containerMap;
	}
	
	
}
