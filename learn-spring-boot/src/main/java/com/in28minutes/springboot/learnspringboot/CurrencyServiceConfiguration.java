package com.in28minutes.springboot.learnspringboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This can be used if you have a very big configuration in the application.properties
 * The values from the properties file can be fetched in here and be used anywhere in the application.
 *
 currency-service.url=
 currency-service.username=
 currency-service.key=
 */
@ConfigurationProperties(prefix = "currency-service")
@Component // we want Spring to manage this Configuration class that is why we added the @Component Annotation
public class CurrencyServiceConfiguration {
    private String url;
    private String username;
    private String key;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
