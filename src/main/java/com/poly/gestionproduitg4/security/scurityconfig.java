package com.poly.gestionproduitg4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class scurityconfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        return  new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("ADMIN","USER").build());


    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(form->form.permitAll());
        httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/user/**").hasRole("USER"));
        httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/admin/**").hasRole("ADMIN"));
        httpSecurity.exceptionHandling(exception->exception.accessDeniedPage("/errorPage"));
        httpSecurity.authorizeHttpRequests(authorize-> authorize.anyRequest().authenticated());
        return httpSecurity.build();
    }
}
