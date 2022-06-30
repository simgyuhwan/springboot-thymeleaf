package com.practice.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

import javax.naming.AuthenticationException;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> {
                authorize
                        .antMatchers("/board/**", "/signUp", "/signIn").permitAll()
                        .antMatchers("/", "/css/**").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
            });
        return http.build();
    }
}
