package com.leun.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->
                auth.anyRequest().authenticated()
            ).sessionManagement(
                session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).httpBasic(
                Customizer.withDefaults()
            ).csrf(AbstractHttpConfigurer::disable)
            .headers(headers ->
                headers.frameOptions(Customizer. withDefaults()));

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var user = User.withUsername("lee")
//            .password("{noop}1234")
            .password("1234")
            .passwordEncoder(password ->
                passwordEncoder().encode(password))
            .roles("USER")
            .build();

        var admin = User.withUsername("admin")
//            .password("{noop}1234")
            .password("1234")
            .passwordEncoder(password ->
                passwordEncoder().encode(password))
            .roles("ADMIN", "USER")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                    .allowedMethods("*")
//                    .allowedOrigins("http://localhost:3000");
//            }
//        };
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
