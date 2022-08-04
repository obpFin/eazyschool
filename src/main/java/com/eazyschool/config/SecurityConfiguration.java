package com.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(
                (authz) -> {
    /*              try {
                      authz.anyRequest().permitAll().and().httpBasic().and().formLogin();
                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }*/
                    try {
                        authz
                            .mvcMatchers("/home").permitAll()
                            .mvcMatchers("/holidays/**").permitAll()
                            .mvcMatchers("/contact").permitAll()
                            .mvcMatchers("/saveMsg").permitAll()
                            .mvcMatchers("/courses").permitAll()
                            .mvcMatchers("about").permitAll()
                            .and().formLogin().and().httpBasic();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
        );
        return http.build();
    }

}

