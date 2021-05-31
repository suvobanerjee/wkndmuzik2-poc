package com.suvo.practice.poc.wkndmuzik2.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name="jackson",extensions="json")

public class MultiFieldModel {
	
	@ValueMapValue
	private String description;

	public String getDescription() {
		return description;
	}
	
	
	

}
