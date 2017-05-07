package com.gmail.kolesnyk.zakhar.configuration;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and().formLogin().loginPage("/login").permitAll()
                .successHandler(successHandler())
//                .defaultSuccessUrl("/index", false)
                .and().logout().logoutUrl("/logout");
        http.sessionManagement().maximumSessions(100).sessionRegistry(sessionRegistry());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> request.getRequestDispatcher("/index").forward(request,response);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoauthenticationProvider());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            private final Md5PasswordEncoder md5 = new Md5PasswordEncoder();

            @Override
            public String encode(CharSequence rawPassword) {
                return md5.encodePassword(rawPassword.toString(), 1);
            }

            @Override
            @SuppressWarnings("PMD")
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return (md5.encodePassword(rawPassword.toString(), 1)).equals(encodedPassword);
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider daoauthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return loginOrEmail -> {
            try {
                User user = userService.getUserByLoginOrEmailAndPassword(loginOrEmail);
                user.setUsername(loginOrEmail);
                return (UserDetails) user;
            } catch (Exception e) {
                e.printStackTrace();
                throw new UsernameNotFoundException("cant find user by: " + loginOrEmail);
            }
        };
    }
}

