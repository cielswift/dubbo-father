//package com.ciel.provider.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override //授权规则
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/security/s1/**").hasAnyRole("user")
//                .antMatchers("/security/s2/**").hasAnyRole("consumer")
//                .antMatchers("/security/s3/**").hasAnyRole("provider");
//
//        http.formLogin().usernameParameter("name").passwordParameter("password")
//                .loginPage("/security/log").loginProcessingUrl("/security/login");
//
////                .usernameParameter("name").passwordParameter("password")
////                .loginPage("/security/log");
//
//        http.logout().logoutSuccessUrl("/security/index");
//        http.rememberMe();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//    @Override //认证规则
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("夏培鑫").password(new BCryptPasswordEncoder().encode("123")).roles("user","consumer")
//                .and()
//                .withUser("刘学文").password(new BCryptPasswordEncoder().encode("123")).roles("user", "provider");
//    }
//
//}
