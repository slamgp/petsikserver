package com.aep.config.security;


import com.aep.model.user.Role;
import com.aep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return userService;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/rest").hasAnyRole(Role.ADMIN, Role.LUCKY_USER, Role.USER)
                .antMatchers("/").permitAll()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");

    }
}