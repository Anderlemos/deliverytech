package com.deliverytech.delivery_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@Configuration
public class H2Config {

    @Bean
    public ServletRegistrationBean<?> h2servletRegistration() {
        ServletRegistrationBean<?> registrationBean =
                new ServletRegistrationBean<>(new org.h2.server.web.JakartaWebServlet());
        registrationBean.addUrlMappings("/h2-console/*");
        return registrationBean;
    }
}
