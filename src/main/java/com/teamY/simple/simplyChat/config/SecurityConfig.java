package com.teamY.simple.simplyChat.config;

import com.teamY.simple.simplyChat.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/hello", "/loginPage", "/login", "/image").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin().loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .usernameParameter("nickname") //view에서 들어오는 아이디 파라미터명 명시
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                        response.sendRedirect("/main");
//                        //사용자 요청페이지 저장
//                        RequestCache requestCache = new HttpSessionRequestCache();
//                        SavedRequest savedRequest = requestCache.getRequest(request, response);
//
//                        if(savedRequest == null) { //요청페이지가 없으면 보낼 url
//                            response.sendRedirect("/hello");
//                        } else { //요청 페이지가 있으면 보낼 url
//                            response.sendRedirect(savedRequest.getRedirectUrl());
//                        }
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

                        //로그인 실패 예외 발생 시 처리
                        if (exception instanceof UsernameNotFoundException) { //DB에 일치하는 email이 없는 경우
                            request.setAttribute("loginFailMessage", "아이디 또는 비밀번호를 잘못 입력하였습니다.");
                            log.info(request.getAttribute("loginFailMessage").toString());
                        } else if (exception instanceof BadCredentialsException) { //비밀번호가 틀린 경우
                            request.setAttribute("loginFailMessage", "아이디 또는 비밀번호를 잘못 입력하였습니다.");
                            log.info(request.getAttribute("loginFailMessage").toString());
                        } else if (exception instanceof DisabledException) { //Ban 처리된 회원인 경우
                            request.setAttribute("loginFailMessage", "활동정지 된 계정입니다.");
                            log.info(request.getAttribute("loginFailMessage").toString());
                        } else if (exception instanceof AccountExpiredException) { //isMember 1인 경우
                            request.setAttribute("loginFailMessage", "탈퇴처리 된 계정입니다.");
                            log.info(request.getAttribute("loginFailMessage").toString());
                        } else if (exception instanceof SessionAuthenticationException) {
                            request.setAttribute("loginFailMessage", "생성 가능한 세션 개수를 초과하였습니다. 브라우저에서 로그아웃 후 다시 시도해주세요.");
                        }

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/loginPage");
                        dispatcher.forward(request, response);
                    }
                });

        //RememberMeAuthenticationFilter가 작동하는 조건
        //1. authentication(인증이 성공한 사용자의 정보를 담은 인증객체)이 null인 경우(security context안에 authentication이 존재하지 않는 경우(즉, session이 끊겼거나 만료된 경우)),
        //2. form 인증 당시 remember Me 기능을 활성화하여 rememberMe 쿠키를 받은 경우에 작동
        //쿠키에 JSESSIONID와 remember-me 토큰이 저장됨. remember-me 체크하지 않을 경우 JSESSIONID삭제하면 다시 로그인해아하나, remember-me 쿠키를 사용할 경우 JSESSIONID 삭제해도 재인증 필요x
        http
                .rememberMe()
                .rememberMeParameter("remember-me") //form에서 rememberMe 기능 사용 여부를 체크할 때 받을 파라미터명과 동일해야 함
                .tokenValiditySeconds(3600) //rememberMe 토큰 만료 기간
                .alwaysRemember(false) //remember Me 기능이 활성화되지 않아도 항상 실행
                .userDetailsService(userDetailsService); //rememberMe 인증 시 인증 계정 조회를 위해 필요

        http
                .sessionManagement() //세션 관리
                .sessionFixation().changeSessionId() //인증이 필요할 때마다 새로운 세션을 만들어 쿠키 조작을 방지
                .maximumSessions(3) //최대 세션 개수
                .expiredUrl("/expiredUrl") //session 만료 시 이동 페이지
                .maxSessionsPreventsLogin(true); //false : 이전에 로그인한 세션 만료, true : 나중에 로그인 시도하는 세션 생성 불가(로그인 불가)


        http
                .logout()
                .logoutUrl("/logout") //로그아웃 처리 url
                .invalidateHttpSession(true) //세션비우기
                .deleteCookies("JSESSIONID", "remember-me") //로그아웃 시 Tomcat이 발급한 세션 유지 쿠키, 자동 로그인을 위한 remember-me 쿠키 삭제(remeberme 사용 했을 때 필요)
                .logoutSuccessUrl("/loginPage"); //로그아웃 후 이동할 페이지
    }
}
