package com.example.ip_etfbl_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig{

    private final AuthenticationProvider authenticationProvider;
    private final AuthorizationFilter authorizationFilter;

    public WebSecurityConfig(AuthenticationProvider authenticationProvider, AuthorizationFilter authorizationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable().authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE, "/articles/delete/{id}").authenticated()
                .requestMatchers(HttpMethod.POST, "/articles/create").authenticated()
                .requestMatchers(HttpMethod.PUT, "/articles/update/{id}").authenticated()
                .requestMatchers("/auth/**", "/articles/all", "/articles/sold/**", "/articles/active/**", "/articles/info/**", "/articles/type/**", "/article-types/**", "/users/**", "/attribute/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

}
