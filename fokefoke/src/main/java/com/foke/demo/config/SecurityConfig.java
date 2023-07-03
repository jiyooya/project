package com.foke.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
       .authorizeHttpRequests(
               authorize -> authorize
               	   .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                   .requestMatchers("/**").permitAll()
                   .requestMatchers("/login/**").permitAll()
           )  
       .csrf(cors -> cors
    		   .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
        )
       .headers(h -> h
               .addHeaderWriter(new XFrameOptionsHeaderWriter(
               XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
        )
       .formLogin(fLogin -> fLogin
               .loginPage("/login/loginform")
               .defaultSuccessUrl("/")         
        )
       .logout(lo -> lo
               .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
               .logoutSuccessUrl("/")
               .invalidateHttpSession(true)
        );


       return http.build();
   }

   @Bean
   PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   @Bean
   AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
}
