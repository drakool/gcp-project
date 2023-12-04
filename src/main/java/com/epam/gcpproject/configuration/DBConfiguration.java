/**
 * 
 */
package com.epam.gcpproject.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author hany
 *
 */

@ConfigurationPropertiesScan 
@PropertySource("classpath:application-${envTarget:dev}.properties")
@ConfigurationProperties(prefix = "db")
public class DBConfiguration {

}
