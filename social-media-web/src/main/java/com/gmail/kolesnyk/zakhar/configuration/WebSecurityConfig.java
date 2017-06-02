package com.gmail.kolesnyk.zakhar.configuration;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.format.TextStyle;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionLocaleResolver localeResolver;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and().formLogin().loginPage("/login").permitAll()
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .and().rememberMe().rememberMeParameter("_spring_security_remember_me")
                .rememberMeCookieName("rememberMe")
                .and().logout().logoutUrl("/logout");
        http.sessionManagement().maximumSessions(100).sessionRegistry(sessionRegistry());
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, authentication) -> {
            request.setAttribute("errorAuth", messageSource.getMessage("errorAuth", null, localeResolver.resolveLocale(request)));
            request.getRequestDispatcher("/login").forward(request, response);
        };
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            request.getSession().setAttribute("textStyleFull", TextStyle.FULL);
            request.getSession().setAttribute("locale", localeResolver.resolveLocale(request));
            request.getRequestDispatcher("/index").forward(request, response);
        };
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
    public DaoAuthenticationProvider daoauthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return loginOrEmail -> {
            try {
                User user = userService.getUserByLoginOrEmail(loginOrEmail);
                user.setUsername(loginOrEmail);
                return (UserDetails) user;
            } catch (Exception e) {
                e.printStackTrace();
                throw new UsernameNotFoundException("cant find user by: " + loginOrEmail);
            }
        };
    }
}

