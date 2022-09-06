package com.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
                .ignoringAntMatchers("/saveMsg")
                .ignoringAntMatchers("/public/**")
                .ignoringAntMatchers("/api/**")
                .ignoringAntMatchers("/data-api/**")
                .ignoringAntMatchers("/eazyschool/actuator/**")
                .and()
            .authorizeHttpRequests(
                (authz) -> {
                    try {
                        authz
                            .mvcMatchers("/api/**").authenticated()
                            .mvcMatchers("/data-api/**").authenticated()
                            .mvcMatchers("/dashboard").authenticated()
                            .mvcMatchers("/displayProfile").authenticated()
                            .mvcMatchers("/updateProfile").authenticated()
                            .mvcMatchers("/eazyschool/actuator/**").hasRole("ADMIN")
                            .mvcMatchers("/displayMessages").hasRole("ADMIN")
                            .mvcMatchers("/home").permitAll()
                            .mvcMatchers("/holidays/**").permitAll()
                            .mvcMatchers("/contact").permitAll()
                            .mvcMatchers("/saveMsg").permitAll()
                            .mvcMatchers("/courses").permitAll()
                            .mvcMatchers("/about").permitAll()
                            .mvcMatchers("/login").permitAll()
                            .mvcMatchers("/public/**").permitAll()
                            .mvcMatchers("/admin/**").hasRole("ADMIN")
                            .mvcMatchers("/student/**").hasRole("STUDENT")
                            .and().formLogin().loginPage("/login")
                            .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                            .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true ).permitAll()
                            .and().httpBasic();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
