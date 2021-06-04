package com.example.login_register_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebServiceConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/login", "/signup", "/logout").permitAll()
                .antMatchers("/home", "/{id}/delete", "/edit","/{id}/edit", "/search")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/admin","/admin/memberName={name}",
                        "/admin/searchName={name}").access("hasRole('ROLE_ADMIN')")
                .and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check").loginPage("/login")
                .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/home")
                .failureUrl("/login?error=true").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}