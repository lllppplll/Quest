package com.website.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		Class<?> arr[] = {ViewResolverConfiguration.class};
		return arr;  
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		String arr[] = {"/"};
		return arr;
	}

}
