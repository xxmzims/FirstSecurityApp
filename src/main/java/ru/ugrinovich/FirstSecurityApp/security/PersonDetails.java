package ru.ugrinovich.FirstSecurityApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ugrinovich.FirstSecurityApp.models.Person;

import java.util.Collection;
import java.util.Collections;

// класс обертка над сущностью
//реализуем UserDetails для стандартиризации класса UserDetails со всей нужной информацией для аутентификации
public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    // аккаунт не устарел
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // аккаунт не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // пароль не устарел
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // аккаунт включен
    @Override
    public boolean isEnabled() {
        return true;
    }

    // список прав пользователя
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    public Person getPerson() {
        return person;
    }
}
