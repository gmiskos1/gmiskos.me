package com.gmiskos.me.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.gmiskos.me.security.ApplicationUserRole.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // enable annotations preauthorize
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers("/", "index","/*.{js, html, css}").permitAll()
                .antMatchers("/api/**").hasAnyRole(ADMIN.name(), MEMBER.name())
//                .antMatchers(HttpMethod.DELETE, "/admin/api/**").hasAuthority(BLOG_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/admin/api/**").hasAuthority(BLOG_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/admin/api/**").hasAuthority(BLOG_WRITE.getPermission())
//                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), MEMBER.name())
                .anyRequest()
                .authenticated()
                .and()
                //.httpBasic();
                .formLogin().loginPage("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails gmiskos = User.builder()
                .username("gmiskos")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                //.roles(MEMBER.name()) // ROLE_ADMIN
                .authorities(MEMBER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                gmiskos,
                linda
        );

    }
}
