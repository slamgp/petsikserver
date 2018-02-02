package com.aep.config;

import com.aep.config.security.SecurityConfig;
import com.aep.model.user.Role;
import com.aep.model.user.User;
import com.aep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan({"com.aep.controller", "com.aep.config"})
@EnableWebMvc
@Import({SecurityConfig.class})
public class Config implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
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
    public Set<User> usersDao() {
        Set<User> users = new HashSet<User>();
        Set<String> roles = new HashSet<String>();
        roles.add(Role.USER);
        users.add(new User(1, "aaa@gmail.com", "111", roles));
        return users;
    }

    @Bean
    public UserService userServcie() {
        return new UserService(usersDao());
    }
}
