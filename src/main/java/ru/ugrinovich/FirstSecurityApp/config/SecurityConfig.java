package ru.ugrinovich.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.ugrinovich.FirstSecurityApp.services.PersonDetailsService;


// в этом классе настраивается наша конфигурация Security.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.
                        requestMatchers("/auth/login", "/error")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.
                        loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/hello", true)
                        .failureUrl("/auth/login?error"));

        return http.build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getpasswordEncoder());
    }
    @Bean
    public PasswordEncoder getpasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
