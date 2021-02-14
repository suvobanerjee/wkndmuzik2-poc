package com.suvo.practice.poc.wkndmuzik2.core.models;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.wcm.core.components.models.Image;
import com.day.cq.wcm.api.Page;
import com.suvo.practice.poc.wkndmuzik2.core.utils.ProjectContstants;

@Model(adaptables = {Resource.class},
		resourceType = "wkndmuzik2/components/image",
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)

@Exporter(name="jackson",extensions="json")

public class ImageModel {

	
	public static final Logger log = LoggerFactory.getLogger(ImageModel.class);
	
	@SlingObject
	Resource currentRes;
	
	/*
	 * @ScriptVariable private Page currentPage;
	 * 
	 * @Self
	 * 
	 * @Via(type = ResourceSuperType.class) private Image image;
	 * 
	 * 
	 */
	
	@ValueMapValue 
	protected String fileReference;

	@ValueMapValue 
	protected String altValueFromDAM;
	
	@ValueMapValue 
	protected String displayPopupTitle;
	
	@ValueMapValue @Optional
	protected String moleculeName;
	 

	
	private Map<String, Object> imageMap = new HashMap<String, Object>();
	
	@PostConstruct
	private void init()
	{
		log.debug("Inside image init: "+currentRes.getPath());

		
		try {
			
			if(!moleculeName.isEmpty())
			  { 
				  imageMap.put(ProjectContstants.MOLECULE_NAME, moleculeName); 
			  } 
			  else
			  { 
				  imageMap.put(ProjectContstants.MOLECULE_NAME,ProjectContstants.IMAGE_COMP); 
			  }
			 
			if(!altValueFromDAM.isEmpty())
			{
				imageMap.put(ProjectContstants.ALT_VALUE_FROM_DAM_IMAGE_COMP, Boolean.parseBoolean(altValueFromDAM));
			}
			if(!displayPopupTitle.isEmpty())
			{
				imageMap.put(ProjectContstants.DISPLAY_POPUP_TITLE_IMAGE_COMP,Boolean.parseBoolean(displayPopupTitle));
			}
			if(!fileReference.isEmpty())
			{
				imageMap.put(ProjectContstants.FILE_REFERENCE_IMAGE_COMP, fileReference);
			}
			
		} catch (Exception e) {
			log.error("Exception in image model: ",e);
		}
		  
	}
	
	
	 public Map<String, Object> getImageMap() {
	        return imageMap;
	    }
	 
}
