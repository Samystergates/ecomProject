package com.project.ecom1.config;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

	@Autowired
	private DispatcherConfig dispatcherConfig;

	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(flowRegistry()).build();
	}

	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder().setValidator(getValidator()).setViewFactoryCreator(viewFactoryCreator())
				.build();
	}

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder().setBasePath("/WEB-INF/views/flows")
				.setFlowBuilderServices(flowBuilderServices()).addFlowLocationPattern("/**/*-flow.xml").build();
	}

	@Bean
	public FlowHandlerAdapter flowAdapter() {
		FlowHandlerAdapter flowAdapter = new FlowHandlerAdapter();
		flowAdapter.setFlowExecutor(flowExecutor());
		return flowAdapter;
	}

	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setFlowRegistry(flowRegistry());
		handlerMapping.setOrder(-1);
		return handlerMapping;
	}

	@Bean
	public MvcViewFactoryCreator viewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(Collections.singletonList(this.dispatcherConfig.viewResolver()));
		return factoryCreator;
	}

	@Bean
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		return validator;
	}

}
