package com.test;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

public class ApacheProp {

	public static void main(String[] args) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("test.properties");
			config.setAutoSave(true);
			String name = config.getString("name");

			System.out.println(name);

			
			config.addProperty("age", "333");
			config.setProperty("age2", "23");
			

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void test() {

		CompositeConfiguration config = new CompositeConfiguration();
		config.addConfiguration(new SystemConfiguration());
		try {
			config.addConfiguration(new PropertiesConfiguration("application.properties"));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		// Parameters params = new Parameters();
		// FileBasedConfigurationBuilder<Configuration> builder = new
		// FileBasedConfigurationBuilder<Configuration>(PropertiesConfiguration.class,
		// null,
		// true).configure(params.properties().setFile(fileToSaveChangesIn));
		//
		// Configuration cc = new
		// CompositeConfiguration(builder.getConfiguration());
		// cc.setProperty("newProperty", "new value");
		//
		// builder.save();
	}

}
