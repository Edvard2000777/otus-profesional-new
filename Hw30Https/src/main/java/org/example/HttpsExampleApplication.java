package org.example;

import org.apache.catalina.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HttpsExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpsExampleApplication.class, args);

    }
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    private org.apache.catalina.connector.Connector createSslConnector() {
        org.apache.catalina.connector.Connector connector = new org.apache.catalina.connector.Connector("org.apache.coyote.http11.Http11NioProtocol");
        Ssl ssl = new Ssl();
        ssl.setKeyAlias("tomcat");
        ssl.setKeyStoreType("PKCS12");
        ssl.setKeyStorePassword("your_keystore_password");
        ssl.setKeyStore("classpath:keystore.p12");
        connector.setScheme("https");
        connector.setSecure(true);
        connector.setPort(8443);
        connector.setService((Service) ssl);
        return connector;
    }
}