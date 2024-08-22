package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blog.security.CustomUserDetailsService;
import com.blog.security.JwtAuthenticationEntryPoint;
import com.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

//	write securityFilterChain and securityFilterChain in securityConfig
//	create CustoUserDetailsService implementingf UserdetailaService
//	override methods in User class by implementing UserDetails inteface
//	create classes from package com.blog.security
//	

	private static final String[] AUTH_WHITELIST = { "/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs.yaml",
			"/swagger-ui/**", "/swagger-ui.html", "/api/users/" };

	@Autowired
	private CustomUserDetailsService customUserDetailService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.requestMatchers(AUTH_WHITELIST)
						.permitAll()
						.requestMatchers(HttpMethod.GET)
						.permitAll().anyRequest().permitAll())

				.exceptionHandling()
				.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)// exception get commence
																								// method will run
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/*
	 * The AuthenticationManager calls the loadUserByUsername method in the
	 * CustomUserDetailsService to fetch the user's details from the database. In
	 * CustomUserDetailsService, the UserRepo is used to find the user by their
	 * email (used as the username). If the user is found, a User object (which
	 * implements UserDetails) is returned. If not, a UsernameNotFoundException is
	 * thrown. The AuthenticationManager compares the password provided by the user
	 * (in the HTTP Basic Authorization header) with the hashed password stored in
	 * the database. The comparison is done using the BCryptPasswordEncoder
	 * (configured in the passwordEncoder bean).
	 */

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);

		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());

		return auth.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
