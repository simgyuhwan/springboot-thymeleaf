package com.practice.board.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        return http.requestMatchers(matchers -> matchers
                        .antMatchers("/resources/**"))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .requestCache(RequestCacheConfigurer::disable)
                .securityContext(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .build();
    }

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
                        .antMatchers("/board/**", "/user/**").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                    .and()
                            .formLogin((formLogin)->
                            {
                                try {
                                    formLogin.loginPage("/user/signIn")
                                            .usernameParameter("userId")
                                            .passwordParameter("userPw")
                                            .failureUrl("/user/signIn/error")
                                            .defaultSuccessUrl("/board")
                                            .and()
                                            .logout()
                                            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                                            .logoutSuccessUrl("/board");
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });
                } catch (Exception e) {
                   log.error("security formLogin error = {}", e.getMessage());
                }
            });
        return http.build();
    }
}
