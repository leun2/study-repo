    package com.leun.auth.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf((csrf) -> csrf. disable())
                .headers((headers) -> headers
                    .frameOptions((frameOptions) -> frameOptions.disable())
            );

            return http.build();
        }

        @Bean
        public InMemoryUserDetailsManager createUserDetailsManager() {
            UserDetails userDetails1 = createUser("lee","1234");
            UserDetails userDetails2 = createUser("kim","1234");

            return new InMemoryUserDetailsManager(userDetails1, userDetails2);
        }

        private UserDetails createUser(String username, String password) {

            UserDetails userDetails = User.builder()
                .passwordEncoder(input -> passwordEncoder().encode(input))
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();

            return userDetails;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
