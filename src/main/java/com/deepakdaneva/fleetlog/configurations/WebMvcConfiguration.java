/**
 * FleetLog
 * May 1, 2019 11:05:46 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Value("${app.staticfiles.paths}")
	String appStaticFilesPaths;

	/*
	 * Configuring the paths in our application where static files exists
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
		String[] staticFilesLocations = appStaticFilesPaths.split(",");
		for (int i = 0; i < staticFilesLocations.length; i++) {
			staticFilesLocations[i] = "classpath:" + staticFilesLocations[i];
		}
		resourceHandlerRegistry.addResourceHandler("/**").addResourceLocations(staticFilesLocations);
	}

	/*
	 * Since there are no controller for path "/" it should show some content with
	 * below code index.html will be rendered on context path.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
		viewControllerRegistry.addViewController("/").setViewName("forward:/index.html");
	}

}
