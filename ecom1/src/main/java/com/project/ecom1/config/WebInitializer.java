package com.project.ecom1.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(DispatcherConfig.class, WebFlowConfig.class, WebSecurity.class);

		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
		context.setServletContext(servletContext);

		ConfigurableWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.setParent(context);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);

		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		ServletRegistration.Dynamic registration = servletContext.addServlet("myDs", dispatcherServlet);
		registration.setMultipartConfig(new MultipartConfigElement("", 20848820, 418018841, 1048576));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}

}
