package com.tracker.spring.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication().dataSource(dataSource);
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("admin").password("12345").roles("ADMIN"))
                .withUser(userBuilder.username("user").password("54321").roles("STUDENT"));
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").hasAnyRole("ADMIN", "HR")
//                .and().formLogin().permitAll();
//    }
}
