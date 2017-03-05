package br.com.meutransporte.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.NullRequestCache;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and().requestCache().requestCache(new NullRequestCache())
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //TODO: Utilizar JDBC
        auth.inMemoryAuthentication()
                .withUser("user").password("user").authorities("ROLE_USER")
                .and()
                .withUser("admin").password("admin").authorities("ROLE_USER", "ROLE_ADMIN");
    }
}