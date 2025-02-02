package com.pharmacy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pharmacy.security.CustomAccessDeniedHandler;
import com.pharmacy.security.CustomerAuthenticationProvider;
import com.pharmacy.security.JwtAuthenticationFilter;
import com.pharmacy.security.OtpAuthenticationProvider;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	
	@Bean 
    public AuthenticationProvider otpAuthenticationProvider() {
    	return new OtpAuthenticationProvider();
	}
    
	 @Bean
	 public AccessDeniedHandler customAccessDeniedHandler() {
	      return new CustomAccessDeniedHandler(); // Ensure that the custom handler is defined as a bean
	   }

    @Bean 
    public AuthenticationProvider customAuthenticationProvider() {
    	return new CustomerAuthenticationProvider();
    }
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
   

    // Defining the SecurityFilterChain instead of using WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	 
        	.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/register/**").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler()) // Add the access denied handler
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
            // Add JWT filter here (e.g., .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class))
        return http.build();
    }

    // Configuring the AuthenticationManager to use the custom authentication providers
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        		.authenticationProvider(otpAuthenticationProvider())
        		.authenticationProvider(customAuthenticationProvider())
                
               
                .build();
    }

    

}
