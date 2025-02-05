package ru.ugrinovich.FirstSecurityApp.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import ru.ugrinovich.FirstSecurityApp.security.AuthProviderImpl;


// в этом классе настраивается наша конфигурация Security.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private  final AuthProviderImpl authProvider;

    // передаем в конструктор класс AuthProvider, этот класс отвечает за проведение аутентификации
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    // передаем наш AuthProvider в специальный класс,
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);
    }
}
