package com.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DetailsService detailsService;

    @Autowired
    public void configureFlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(detailsService);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
    }
}
