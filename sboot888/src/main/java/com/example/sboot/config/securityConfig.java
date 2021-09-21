package com.example.sboot.config;

import com.example.sboot.config.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class securityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    @Autowired
    private LoginSuccessHandler successUserHandler;

    public securityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("index").permitAll()
                .and()
                .formLogin()
             .loginPage("/login")
              .loginProcessingUrl("/login")
                .successHandler(successUserHandler)
                .failureUrl("/login?error=true")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/exception");

        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login")
                .and().csrf().disable();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
