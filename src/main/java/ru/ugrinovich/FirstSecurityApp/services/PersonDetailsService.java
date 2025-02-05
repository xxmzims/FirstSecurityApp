package ru.ugrinovich.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ugrinovich.FirstSecurityApp.models.Person;
import ru.ugrinovich.FirstSecurityApp.repositories.PeopleRepository;
import ru.ugrinovich.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    // ищем в БД пользователя по имени, если находим то создаем класс PersonDetails, если нет бросаем исключение
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personOptional =  peopleRepository.findByUsername(username);

        if (personOptional.isEmpty()){
            throw new UsernameNotFoundException("User not Found!");
        }
        return new PersonDetails(personOptional.get());
    }
}
