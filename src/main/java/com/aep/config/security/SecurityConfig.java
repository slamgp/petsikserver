package com.aep.config.security;


import com.aep.model.user.Role;
import com.aep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    BasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

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
                .antMatchers("/rest").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/outh").permitAll()
                .antMatchers(HttpMethod.GET, "/outh").permitAll()
                .antMatchers("/").permitAll()
                .and();
               // .formLogin();
        http.csrf().disable();
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //disable session
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}