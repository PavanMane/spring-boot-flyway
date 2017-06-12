package org.p1.flyway.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;


/**
 * Currently spring framework does not include flyway locations from 'spring.profiles.include'
 * This custom class collates all the flyway.locations from the active profiles and executes it.
 * @author pavanmane
 *
 */
@Component
public class CustomFlywayMigrationStrategy implements FlywayMigrationStrategy {

	@Autowired
	private Environment env;
	
	
	@Override
	public void migrate(Flyway flyway) {
		ConfigurableEnvironment configurableEnv = null;
		if(env instanceof ConfigurableEnvironment) {
			configurableEnv = (ConfigurableEnvironment) env;
			String[] existing_locations = flyway.getLocations();
			MutablePropertySources propertySources = configurableEnv.getPropertySources();
			Set<String> flywayLocations = new TreeSet<>();
			if(propertySources != null) {
				Iterator<PropertySource<?>> iterator = propertySources.iterator();
				while(iterator.hasNext()) {
					PropertySource<?> propertySource = iterator.next();
					// pickup only application configuration property sources. 
					// example of <propertysourceobj>.getName() 'applicationConfig: [classpath:/application-common.properties]' 
					if (StringUtils.contains(propertySource.getName(), "applicationConfig")) {
						String[] activeProfiles = configurableEnv.getActiveProfiles();
						for (String profile : activeProfiles) {
							String filterProfiles = "application" + ("default".equalsIgnoreCase(profile) ? "" : "-" + profile) + ".properties";
							if (StringUtils.contains(propertySource.getName(), filterProfiles)) {
								Object source = propertySource.getSource();
								if (source instanceof Properties) {
									String property = ((Properties) source).getProperty("flyway.locations");
									String[] flyway_locations = StringUtils.split(property, ",");
									if(flyway_locations != null && flyway_locations.length > 0) {
										for (String flywayLoc: flyway_locations) {
											flywayLocations.add(flywayLoc.trim());
										}
									}
								}
							}
						}
					}
				}
			}
			List<String> final_locs = new ArrayList<>(flywayLocations.size() + existing_locations.length);
			for (String loc : flywayLocations) {
				if (!ArrayUtils.contains(existing_locations, loc)) {
					final_locs.add(loc);
				}
			}
			flyway.setLocations(final_locs.toArray(new String [final_locs.size()]));
		}
		flyway.migrate();
	}

}
