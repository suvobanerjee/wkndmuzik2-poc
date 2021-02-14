package com.suvo.practice.poc.wkndmuzik2.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.wcm.core.components.models.ExperienceFragment;

@Model(adaptables = SlingHttpServletRequest.class,
		//adapters = ExperienceFragment.class,
		resourceType = "wkndmuzik2/components/experiencefragment",
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ExperienceFragModel {
	
	public static final Logger log = LoggerFactory.getLogger(ExperienceFragModel.class);
	
	/*
	 * @Self @Via(type = ResourceSuperType.class) private ExperienceFragment
	 * xFragment;
	 */
	
	  @ValueMapValue 
	  private String id;
	 
	
	@PostConstruct
	public void init()
	{
		try 
		{
			//log.debug("Xfragment: "+xFragment.getLocalizedFragmentVariationPath());
			log.debug("id:: "+id);
		}
		catch(Exception e)
		{
			log.error("Exception in xfRagment Model: ",e);
		}
	}
		
	/*
	 * public String getId() { return id; }
	 */
	

}
