package com.aep.config;

import com.aep.config.security.SecurityConfig;
import com.aep.model.user.Role;
import com.aep.model.user.User;
import com.aep.service.validator.NewUserDataValidator;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan({"com.aep.controller", "com.aep.config", "com.aep.service"})
@EnableWebMvc
@Import({SecurityConfig.class})
public class Config implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public NewUserDataValidator newUserDataValidator() {
        return new NewUserDataValidator();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public Set<User> usersDao() {
        Set<User> users = new HashSet<User>();
        Set<String> roles = new HashSet<String>();
        roles.add(Role.USER);
        users.add(new User(1, "111@gmail.com", passwordEncoder().encode("111"), roles));
        users.add(new User(2, "222@gmail.com", passwordEncoder().encode("222"), roles));
        return users;
    }


    @Bean
    public BasicAuthenticationEntryPoint basicAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint basicAuthenticationEntryPoint =  new BasicAuthenticationEntryPoint();
        basicAuthenticationEntryPoint.setRealmName("petsik");
        return basicAuthenticationEntryPoint;
    }
}
