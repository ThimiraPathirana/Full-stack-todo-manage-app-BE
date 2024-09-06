package com.fullstack.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class Configuration {

//    /**
//     * Step 1
//     * Bypass basic security - start
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.build();
//    }
//    /** Bypass basic security - end */

//    /**
//     * Step 2
//     * Add authorization for every user - start
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(customizer -> customizer.disable());
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        return httpSecurity.build();
//    }
//    /** Add authorization for every user - end */

//    /**
//     * Step 3
//     * Add authorization with form login- start
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(customizer -> customizer.disable());
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
//        return httpSecurity.build();
//    }
//    /** Add authorization with form login - end */

//    /**
//     * Step 4
//     * Add authorization with form login (Get same session id) - start
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(customizer -> customizer.disable());
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }
//    /** Add authorization with form login - end */

    /**
     * Step 5
     * Get session id when call every time - start
     * SessionCreationPolicy.STATELESS - return different session id
     * SessionCreationPolicy.ALWAYS - return same session id
     * SessionCreationPolicy.NEVER - first & second are different, after second return same session id
     * SessionCreationPolicy.IF_REQUIRED - return same session id
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER));
        return httpSecurity.build();
    }
    /** Add authorization with form login - end */
}
