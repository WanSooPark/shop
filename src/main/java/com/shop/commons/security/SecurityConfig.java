package com.shop.commons.security;

import com.shop.services.service.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/members/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("email")
//                .failureUrl("/members/login/error")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
//                .logoutSuccessUrl("/")

        http.formLogin()
                .disable();

        http.csrf();
//                .csrfTokenRepository(sessionCsrfRepository());

        http.authorizeRequests(request -> {
            request.antMatchers("/admin/**")
                    .permitAll()
                    .anyRequest().permitAll();
        })
//                .mvcMatchers("/css/**", "/js/**", "/img/**")
//                .permitAll()
//                .mvcMatchers("/", "/members/**", "/item/**", "/images/**")
//                .permitAll()
//                .mvcMatchers("/admin/**")
//                .permitAll()
        ;

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

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