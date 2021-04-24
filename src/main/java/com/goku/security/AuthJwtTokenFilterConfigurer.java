package com.goku.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthJwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private AuthJwtTokenProvider authJwtTokenProvider;

    public AuthJwtTokenFilterConfigurer(AuthJwtTokenProvider authJwtTokenProvider) {
        this.authJwtTokenProvider = authJwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthJwtTokenFilter customFilter = new AuthJwtTokenFilter(authJwtTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
