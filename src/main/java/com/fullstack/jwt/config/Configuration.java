package com.fullstack.jwt.config;

import com.fullstack.jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class Configuration {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

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

//    /**
//     * Step 5
//     * Get session id when call every time - start
//     * SessionCreationPolicy.STATELESS - return different session id
//     * SessionCreationPolicy.ALWAYS - return same session id
//     * SessionCreationPolicy.NEVER - first & second are different, after second return same session id
//     * SessionCreationPolicy.IF_REQUIRED - return same session id
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(customizer -> customizer.disable());
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER));
//        return httpSecurity.build();
//    }
//    /** Add authorization with form login - end */

//    /**
//     * Step 6
//     * User builder pattern - start
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.
//                csrf(customizer -> customizer.disable())
//                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
//                .build();
//    }
//    /** User builder pattern - end */

//    /**
//     * Step 6
//     * Log from given user without encode password - start
//     */
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        provider.setUserDetailsService(jwtUserDetailsService);
//        return provider;
//    }
//
//    /** Log from given user without encode password - end */

    /**
     * Step 7
     * Log from given user with BCrypt password encode - start
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(jwtUserDetailsService);
        return provider;
    }

    /** Log from given user with BCrypt password encode - end */

//    /**
//     * Step 8
//     * Give access only for 'register,login' api without authenticate - start
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.
//                csrf(customizer -> customizer.disable())
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("register", "login")
//                        .permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
//                .build();
//    }
//    /** User builder pattern - end */

    /**
     * Step 8
     * Use AuthenticationManager to verify login user - start
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    /** Use AuthenticationManager to verify login user - end */

    /**
     * Step 9
     * Verify the generated token for other api - start
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.
                csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("register", "login")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /** User builder pattern - end */
}
