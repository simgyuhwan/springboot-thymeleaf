package com.practice.board.config;

import com.practice.board.config.security.entrypoint.CustomAuthenticationEntryPoint;
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
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
            .csrf().disable();

        http.userDetailsService(userDetailsService);

        http.authorizeHttpRequests()
                .antMatchers("/admin/**", "/admin")
                .hasRole("ADMIN");

        http
            .authorizeHttpRequests((authorize) -> {
                try {
                    authorize
                        .antMatchers("/", "/css/**").permitAll()
                        .antMatchers("/board/post").authenticated()
                        .antMatchers("/board/**", "/user/**", "/board/images/**").permitAll()
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

        // 인증 실패시 핸들링
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());



        return http.build();
    }
}
