package com.company.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("fuad").password("12345").roles("ADMIN")// memoryde saxlayir
//                .and().withUser("samira").password("1234").roles("USER");
        auth.userDetailsService(myUserDetailsService);// database de
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {// authorization, csrf arxada token generasiya edir
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/user/**").permitAll()
                .antMatchers("/patient/**", "doctor/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/transaction").hasRole("ADMIN").anyRequest().authenticated().and().httpBasic();// formLogin,and().logout()  de istif ede bilerik.
    }

    @Bean
    public PasswordEncoder getPassworEncoder() {// passwordu encode etmirik heleki
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public PasswordEncoder getPassworEncoder2() {// sifreleyib saxlayir databesde
        return new BCryptPasswordEncoder();
    }
}
