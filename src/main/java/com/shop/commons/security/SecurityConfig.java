package com.shop.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/img/**", "/admin/**", "/css/**", "/images/**", "/js/**",
                        "/favicon.ico", "/resources/**", "/error", "/admin/error");
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin")
                .failureUrl("/admin/login/error")
                .and()
                .logout()
                .logoutSuccessUrl("/")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
        ;

        http.csrf()
                .disable();

        http.antMatcher("/admin/**")
                .authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**", "/admin/css/**", "/admin/js/**", "/admin/img/**")
                .permitAll()
                .mvcMatchers("/admin", "/admin/login", "/admin/notice","/admin/notice/**", "/admin/item", "/admin/item/**", "/admin/category", "/admin/category/**", "/admin/topic", "/admin/topic/**", "/admin/banner" , "/admin/banner/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        ;

        return http.build();
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityFilterChain adminApiFilterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin")
                .failureUrl("/admin/login/error")
                .and()
                .logout()
                .logoutSuccessUrl("/")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
        ;

        http.csrf()
                .disable();

        http.antMatcher("/api/admin/**")
                .authorizeRequests()
                .mvcMatchers("/api/admin/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        ;

        return http.build();
    }

    @Bean
    public SecurityFilterChain serviceFilterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login/error")
                .and()
                .logout()
                .logoutSuccessUrl("/")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
        ;

        http.csrf()
                .disable();

        http.antMatcher("/**")
                .authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**")
                .permitAll()
                .mvcMatchers("/", "/login", "/login/**", "/sign/up", "/index")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/item", "/item/**", "/category/**", "/topic/**", "/banner/**", "/notice/**")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/items", "/items/**", "/categories", "/categories/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public HttpSessionCsrfTokenRepository sessionCsrfRepository() {
//        HttpSessionCsrfTokenRepository csrfRepository = new HttpSessionCsrfTokenRepository();
//
//        csrfRepository.setHeaderName("X-CSRF-TOKEN");
//        csrfRepository.setParameterName("_csrf");
//        csrfRepository.setSessionAttributeName("CSRF_TOKEN");
//
//        return csrfRepository;
//    }
//
//    @Bean
//    public CookieCsrfTokenRepository cookieCsrfRepository() {
//        CookieCsrfTokenRepository csrfRepository = new CookieCsrfTokenRepository();
//
//        csrfRepository.setCookieHttpOnly(false);
//        csrfRepository.setHeaderName("X-CSRF-TOKEN");
//        csrfRepository.setParameterName("_csrf");
//        csrfRepository.setCookieName("XSRF-TOKEN");
//        //csrfRepository.setCookiePath("..."); // 기본값: request.getContextPath()
//
//        return csrfRepository;
//    }
}