package com.practice.board.config;

import com.practice.board.config.security.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final UserDetailsService userDetailsService;

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
            .csrf().disable();

        http.userDetailsService(userDetailsService);

        http
            .authorizeHttpRequests((authorize) -> {
                try {
                    authorize
                        .antMatchers("/", "/css/**").permitAll()
                        .antMatchers("/board/post").hasAnyRole("USER","MANAGER","ADMIN")
                        .antMatchers("/board/**", "/signUp", "/signIn").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                    .and()
                        .formLogin()
                            .loginPage("/signIn").defaultSuccessUrl("/board")
                            .loginProcessingUrl("/signIn").defaultSuccessUrl("/board")
                    .and()
                        .logout()
                            .logoutUrl("/logout").logoutSuccessUrl("/board");
                } catch (Exception e) {
                   log.error("security formLogin error = {}", e.getMessage());
                }
            });
        return http.build();
    }



}
