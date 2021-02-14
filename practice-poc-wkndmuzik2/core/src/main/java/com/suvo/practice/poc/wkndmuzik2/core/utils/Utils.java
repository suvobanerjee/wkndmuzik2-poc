package com.suvo.practice.poc.wkndmuzik2.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suvo.practice.poc.wkndmuzik2.core.models.ContainerModel;
import com.suvo.practice.poc.wkndmuzik2.core.models.ImageModel;
import com.suvo.practice.poc.wkndmuzik2.core.models.LaguageNavigationModel;
import com.suvo.practice.poc.wkndmuzik2.core.models.ListItemModel;
import com.suvo.practice.poc.wkndmuzik2.core.models.NavigationModel;
import com.suvo.practice.poc.wkndmuzik2.core.models.TextModel;
import com.suvo.practice.poc.wkndmuzik2.core.models.Title;


public class Utils {
	
	
	public static final Logger log = LoggerFactory.getLogger(Utils.class);
	
	public static Map<String, Object> assignModels(Resource res)
	{
		Map<String, Object> innerMap = new HashMap<String, Object>();
		
		log.debug("Utils: "+res.getPath());
		
		if(StringUtils.contains(res.getPath(), ProjectContstants.HEADER_JSON))
		{
			Iterator<Resource> headerChildResIterator = res.listChildren();
			
			while(headerChildResIterator.hasNext())
			{
				Resource headerChildRes = headerChildResIterator.next();
				log.debug("headerChildRes:-> "+headerChildRes.getName());
				
				modelsClassification(headerChildRes, innerMap);
			}
		}
		else if(StringUtils.contains(res.getPath(), ProjectContstants.FOOTER_JSON))
		{
			Iterator<Resource> footerChildResIterator = res.listChildren();
			List<Object> footerArr = new ArrayList<Object>();
			while(footerChildResIterator.hasNext())
			{
				Map<String, Object> footerMap = new HashMap<String, Object>();
				Resource footerChildRes = footerChildResIterator.next();
				log.debug("footerChildRes:-> "+footerChildRes.getName());
				
				modelsClassification(footerChildRes, footerMap);
				footerArr.add(footerMap);
			}
			innerMap.put(ProjectContstants.MOLECULE, footerArr);
			
		}
		
		return innerMap;
	}
	
	public static void modelsClassification(Resource res, Map<String, Object> innerMap)
	{
		if(StringUtils.contains(res.getName(), ProjectContstants.IMAGE_COMP))
		{
			log.debug("in image::::"+res.adaptTo(ImageModel.class).getImageMap());
			innerMap.put(ProjectContstants.IMAGE_COMP, res.adaptTo(ImageModel.class).getImageMap());
		}
		if(StringUtils.equals(res.getName(), ProjectContstants.NAVIGATION_COMP))
		{
			log.debug("in Nav::::");
			innerMap.put(ProjectContstants.NAVIGATION_COMP, res.adaptTo(NavigationModel.class).getNavigationMap());
			//innerMap.put(ProjectContstants.MOLECULE, headerChildRes.adaptTo(NavigationModel.class).getNavigationMap());
		}
		if(StringUtils.equals(res.getName(), ProjectContstants.LANGUAGE_NAVIGATION_COMP))
		{
			log.debug("in lang Nav::::");
			//log.debug("getLangNavigationMap: "+headerChildRes.adaptTo(LaguageNavigationModel.class).getLangNavigationMap());
			innerMap.put(ProjectContstants.LANGUAGE_NAVIGATION_COMP, res.adaptTo(LaguageNavigationModel.class).getLangNavigationMap());
		}
		if(StringUtils.contains(res.getName(), ProjectContstants.CONTAINER_COMP))
		{
			log.debug("in container::::");
			//log.debug("getLangNavigationMap: "+headerChildRes.adaptTo(LaguageNavigationModel.class).getLangNavigationMap());
			//innerMap.put(ProjectContstants.CONTAINER_COMP, res.adaptTo(ContainerModel.class).getContainerMap());
			innerMap.put(ProjectContstants.MOLECULE, res.adaptTo(ContainerModel.class).getContainerMap());
		}
		if(StringUtils.contains(res.getName(), ProjectContstants.TITLE_COMP))
		{
			log.debug("in title::::");
			//log.debug("getLangNavigationMap: "+headerChildRes.adaptTo(LaguageNavigationModel.class).getLangNavigationMap());
			//innerMap.put(ProjectContstants.TITLE_COMP, res.adaptTo(Title.class).getTitleMap());
			innerMap.put(ProjectContstants.MOLECULE, res.adaptTo(Title.class).getTitleMap());
		}
		if(StringUtils.contains(res.getName(), ProjectContstants.TEXT_COMP))
		{
			log.debug("in text model::::");
			//log.debug("getLangNavigationMap: "+headerChildRes.adaptTo(LaguageNavigationModel.class).getLangNavigationMap());
			innerMap.put(ProjectContstants.MOLECULE, res.adaptTo(TextModel.class).getTextMap());
		}
		if(StringUtils.contains(res.getName(), ProjectContstants.LISTITEM_COMP))
		{
			log.debug("in listItem model::::");
			//log.debug("getLangNavigationMap: "+headerChildRes.adaptTo(LaguageNavigationModel.class).getLangNavigationMap());
			innerMap.put(ProjectContstants.MOLECULE, res.adaptTo(ListItemModel.class).getListItemMap());
		}
	}
	

}
