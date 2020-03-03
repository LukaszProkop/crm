package com.sda.eventservice.config;


import com.sda.eventservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersonService userService;

    public PersonService getUserService() {
        return userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(getUserService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("adam@adam.pl")
                .password(passwordEncoder().encode("adam1"))
                .roles("USER", "ORGANIZER", "ADMIN");
        auth
                .authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**", "/", "/registration", "/access-denied", "/showDetails", "/search", "/terms").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN", "ORGANIZER")
                .antMatchers("/organizer/**").hasAnyRole("ORGANIZER", "ADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-process")
                .permitAll()
                .and()
                .logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/access-denied");


    }
}
