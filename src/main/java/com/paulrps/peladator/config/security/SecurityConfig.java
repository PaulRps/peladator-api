package com.paulrps.peladator.config.security;

import com.paulrps.peladator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //AUTHENTICATION CONFIGURARTIONS
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //AUTHORIZATION CONFIGURATIONS
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/player").permitAll() eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJwZWxhZGF0b3IgQVBJIiwic3ViIjoiMSIsImlhdCI6MTU3NjE0OTA1MiwiZXhwIjoxNTc2MjM1NDUyfQ.TVj-fdUzHu7t_KESQK4F5uq2dYjNzuN_9HfFPn7DWR8
//        TODO: remover depois 4 linhas
        .antMatchers(HttpMethod.GET, "/player**").permitAll()
        .antMatchers(HttpMethod.POST, "/player**").permitAll()
        .antMatchers(HttpMethod.GET, "/player/**").permitAll()
        .antMatchers(HttpMethod.POST, "/player/**").permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/player/**").permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/player**").permitAll()
        .antMatchers(HttpMethod.DELETE, "/player/**").permitAll()
        .antMatchers(HttpMethod.DELETE, "/player").permitAll()

        .antMatchers(HttpMethod.POST, "/auth").permitAll()
//        .anyRequest().authenticated()
//        .and().formLogin();
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AuthByTokenFilter(tokenService, userService), UsernamePasswordAuthenticationFilter.class);
    }

    //STATIC FILES (imgs, .js) CONFIGURATIONS
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
