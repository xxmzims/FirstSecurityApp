package ru.ugrinovich.FirstSecurityApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ugrinovich.FirstSecurityApp.services.PersonDetailsService;


// в этом классе настраивается наша конфигурация Security.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private final PersonDetailsService personDetailsService;

    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // передаем наш AuthProvider в специальный класс,
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService);
    }
    @Bean
    public PasswordEncoder getpasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
