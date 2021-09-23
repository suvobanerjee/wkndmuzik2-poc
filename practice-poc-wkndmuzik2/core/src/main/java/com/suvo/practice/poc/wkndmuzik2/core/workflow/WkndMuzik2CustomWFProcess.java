package com.suvo.practice.poc.wkndmuzik2.core.workflow;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.suvo.practice.poc.wkndmuzik2.core.models.PageExporter;

@Component(
		service = WorkflowProcess.class,
		immediate = true,
		property = {
				"process.label"+ "= Wkndmuzik2 workflow custom process step" 
		}
			)
public class WkndMuzik2CustomWFProcess implements WorkflowProcess{

	public static final Logger log = LoggerFactory.getLogger(WkndMuzik2CustomWFProcess.class);
	
	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		
		try {
			WorkflowData wfData = item.getWorkflowData();
			
			if(wfData.getPayload().equals("JCR_PATH"))
			{
				Session jcrSession = session.adaptTo(Session.class);
				String path = wfData.getPayload().toString()+"/jcr:content";
				
				Node node = (Node) jcrSession.getItem(path);
				
				String[] processArguments = args.get("PROCESS_ARGS","string").toString().split(",");
				
				for(String arguments : processArguments)
				{
					String[] processArgs = arguments.split(":");
					String prop = processArgs[0];
					String value = processArgs[1];
					
					if(node != null)
					{
						node.setProperty(prop, value);
					}
				}
				jcrSession.save();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
