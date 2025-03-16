package com.apache.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.RestConfiguration;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.stereotype.Component;

@Component
public class ConfigureCamelContext implements CamelContextConfiguration {

    @Override
    public void beforeApplicationStart(CamelContext camelContext) {

        RestConfiguration restConfiguration = new RestConfiguration();
        restConfiguration.setApiComponent("jetty");
        restConfiguration.setApiHost("localhost");
        restConfiguration.setPort(8080);
        restConfiguration.setBindingMode(RestConfiguration.RestBindingMode.json);

        camelContext.setRestConfiguration(restConfiguration);
    }

    @Override
    public void afterApplicationStart(CamelContext camelContext) {
    }
}
