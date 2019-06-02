/**
 * FleetLog
 * May 23, 2019 9:49:35 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@SpringBootApplication(scanBasePackages = { "com.deepakdaneva.fleetlog" })
public class FleetLogApplication extends SpringBootServletInitializer {
	String CURRENT_ENVIRONMENT = "dev";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, CURRENT_ENVIRONMENT);
		return application.sources(FleetLogApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FleetLogApplication.class, args);
	}

}
