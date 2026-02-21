package com.epam.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures automatic redirection from HTTP (port 8080)
 * to HTTPS (port 8443) in a Spring Boot application.
 * <p>
 * Registers an additional Tomcat connector that listens
 * for HTTP requests and redirects them to the secure port.
 * <p>
 * Works only with embedded Tomcat.
 */
@Configuration
public class HttpToHttpsRedirectConfig {

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory =
                new TomcatServletWebServerFactory();

        factory.addAdditionalTomcatConnectors(httpConnector());
        return factory;
    }

    private Connector httpConnector() {
        Connector connector =
                new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}
