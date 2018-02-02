package com.aep.config.security;


import com.aep.model.user.User;
import com.aep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  // @Autowired
 //   UserService userService;
/*
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return userService;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }
*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/rest").hasAnyRole("user", "admin", "lucky_user")
                .antMatchers("/").permitAll()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");

    }
}