package com.practice.board.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.naming.AuthenticationException;
import javax.validation.constraints.Size;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }


    // 기본 웹 접근 설정
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> {
                try {
                    authorize
                        .antMatchers("/board/**", "/signUp", "/signIn").permitAll()
                        .antMatchers("/", "/css/**").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                    .and()
                        .formLogin()
                        .loginPage("/signIn")
                        .defaultSuccessUrl("/board")
                    .and()
                        .logout();
                } catch (Exception e) {
                   log.error("security formLogin error = {}", e.getMessage());
                }
            });
        return http.build();
    }



}
