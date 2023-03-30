package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.security.UsDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;
    private final UsDetailsService usDetailsService;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UsDetailsService usDetailsService) {
        this.successUserHandler = successUserHandler;
        this.usDetailsService = usDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.userDetailsService(usDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/page/login","/error","/page/registration").permitAll()
                .antMatchers("/admin/**","/user/**").hasRole(Role.ADMIN.getAuthority())
                .antMatchers("/user/**").hasRole(Role.USER.getAuthority())
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                .formLogin().loginPage("/page/login").loginProcessingUrl("/logger").successHandler(successUserHandler).permitAll()
                .failureUrl("/page/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/page/login");
    }

}