package com.suvo.practice.poc.wkndmuzik2.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/formServlet")
@ServiceDescription("Demo Form Servlet")
public class FormServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	
	public static final Logger log = LoggerFactory.getLogger(FormServlet.class);
	
	/*
	 * @Override protected void doGet(SlingHttpServletRequest req,
	 * SlingHttpServletResponse res) throws ServletException,IOException { final
	 * Resource resource = req.getResource();
	 * 
	 * log.debug("Inside Get method:::"); res.setContentType("text/plain");
	 * res.getWriter().write("Hello From Get \n"); res.getWriter().write("\nPath = "
	 * + resource.getPath());
	 * res.getWriter().write("\nName: "+req.getParameter("name"));
	 * res.getWriter().write("\nId: "+req.getParameter("customerId"));
	 * 
	 * }
	 */
	
	
	protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse res) throws ServletException,IOException
	{
		final Resource resource = req.getResource();
		
		log.debug("Inside Post method:::");
		res.setContentType("text/plain");
		res.getWriter().write("Hello \n");
        res.getWriter().write("\nPath = " + resource.getPath());
        res.getWriter().write("\nName: "+req.getParameter("name"));
        res.getWriter().write("\nId: "+req.getParameter("customerId"));
        
	}

}
