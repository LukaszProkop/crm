package com.crm.hbdbweb2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CrmSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(securityDataSource);

        //############################################################//
        //############### --> For Testing Purpose <-- ################//
        //############################################################//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}user").roles("EMPLOYEE")
//                .and().withUser("lukasz").password("{noop}lukasz").roles("MANAGER", "EMPLOYEE")
//                .and().withUser("admin").password("{noop}admin").roles("ADMIN", "MANAGER", "EMPLOYEE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/customer/list").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                .antMatchers("/customer/**").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .and()
                .formLogin().loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and().csrf().disable();
    }
}
