package com.control.building.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class BasicConfigurations {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
    
	@Bean
    public ResourceBundleMessageSource messageSource() {

        var source = new ResourceBundleMessageSource();
        source.setBasenames("error_messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}
