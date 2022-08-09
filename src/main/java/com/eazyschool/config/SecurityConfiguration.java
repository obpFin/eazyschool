package com.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
                .ignoringAntMatchers("/h2-console/**").and()
            .authorizeHttpRequests(
                (authz) -> {
    /*              try {
                      authz.anyRequest().permitAll().and().httpBasic().and().formLogin();
                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }*/
                    try {
                        authz
                            .mvcMatchers("/dashboard").authenticated()
                            .mvcMatchers("/displayMessages").hasRole("ADMIN")
                            .mvcMatchers("/home").permitAll()
                            .mvcMatchers("/holidays/**").permitAll()
                            .mvcMatchers("/contact").permitAll()
                            .mvcMatchers("/saveMsg").permitAll()
                            .mvcMatchers("/courses").permitAll()
                            .mvcMatchers("/about").permitAll()
                            .mvcMatchers("/login").permitAll()
                            .antMatchers("/h2-console/**").permitAll()
                            .and().formLogin().loginPage("/login")
                            .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                            .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true ).permitAll()
                            .and().httpBasic();

                        http.headers().frameOptions().disable();    // Not safe for production
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
        );
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        ArrayList<UserDetails> users = new ArrayList<>();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();
        users.add(user);
        users.add(adminUser);
        return new InMemoryUserDetailsManager(users);
    }

}
