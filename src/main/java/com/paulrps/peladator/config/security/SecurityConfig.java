package com.paulrps.peladator.config.security;

import com.paulrps.peladator.config.exceptions.ApiExceptionService;
import com.paulrps.peladator.services.TokenService;
import com.paulrps.peladator.services.UserService;
import com.paulrps.peladator.services.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private AuthService authService;

  @Autowired private TokenService tokenService;

  @Autowired private UserService userService;

  @Autowired private SpringSecuritedPaths permitedPathsConfig;

  @Autowired private ApiExceptionService apiExceptionService;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder getEncoder() {
    return new BCryptPasswordEncoder();
  }

  // AUTHENTICATION CONFIGURARTIONS
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authService).passwordEncoder(getEncoder());
  }

  @Bean
  CorsFilter myCors() {
    return new CorsFilter();
  }

  // AUTHORIZATION CONFIGURATIONS
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(myCors(), SessionManagementFilter.class)
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(
            HttpMethod.OPTIONS, permitedPathsConfig.getPermitedPathsByMethod(HttpMethod.OPTIONS))
        .permitAll()
        .antMatchers(permitedPathsConfig.getAllPermitedPaths())
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(
            new AuthByTokenFilter(
                tokenService, userService, permitedPathsConfig, apiExceptionService),
            UsernamePasswordAuthenticationFilter.class);
  }

  // STATIC FILES (imgs, .js) CONFIGURATIONS
  @Override
  public void configure(WebSecurity web) {}
}
