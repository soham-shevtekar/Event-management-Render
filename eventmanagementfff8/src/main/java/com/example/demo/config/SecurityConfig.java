	package com.example.demo.config;
	
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.core.userdetails.User;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.provisioning.InMemoryUserDetailsManager;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
	
	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
	
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")  // Only ADMIN can access /admin
	                .requestMatchers(new AntPathRequestMatcher("/user/**")).hasAnyRole("USER", "ADMIN")  // USER and ADMIN can access /user
	                .anyRequest().authenticated()
	            )
	            .formLogin()
	            .and()
	            .logout();
	
	        return http.build();
	    }
	
	    @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails admin = User.withUsername("admin")
	                .password("{noop}admin")  // NoOpPasswordEncoder for plain text passwords
	                .roles("ADMIN")
	                .build();
	                
	        UserDetails user = User.withUsername("user")
	                .password("{noop}user")
	                .roles("USER")
	                .build();
	                
	        return new InMemoryUserDetailsManager(admin, user);
	    }
	}
