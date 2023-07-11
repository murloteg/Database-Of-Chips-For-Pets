package ru.nsu.sberlab.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] ENDPOINTS_WHITELIST = {
            "/",
            "/login",
            "/registration",
            "/pet/find",
            "/pet",
            "/user"
    };

    public static final String DEFAULT_SUCCESS_URL = "/";
    public static final String LOGIN_URL = "/login";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers(ENDPOINTS_WHITELIST)
                        .permitAll()
                        .requestMatchers("/pet/privileged-list")
                        .hasAnyRole("PRIVILEGED_ACCESS", "ADMIN")
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .loginProcessingUrl(LOGIN_URL)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL))
                .logout(logout -> logout
                        .logoutSuccessUrl(DEFAULT_SUCCESS_URL));
        return httpSecurity.build();
    }
}