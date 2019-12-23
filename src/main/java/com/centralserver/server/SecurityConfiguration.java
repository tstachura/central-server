package com.centralserver.server;

import com.centralserver.encryption.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@Import(Encoders.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private static final String ERROR_PATTERN = "/error/**";

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
//                .antMatchers(ERROR_PATTERN).permitAll()
                .antMatchers("/").permitAll();
//                .antMatchers("/ui/**").permitAll()
//                .antMatchers("/index.html").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/oauth/token/revokeById/**").permitAll()
//                .anyRequest().authenticated();
        http.httpBasic().disable();
    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(ERROR_PATTERN).permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/ui/**").permitAll()
//                .antMatchers("/index.html").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/oauth/token/revokeById/**").permitAll()
//                .anyRequest().authenticated();
//        http.httpBasic().disable();
//    }

}

