package ru.ugrinovich.FirstSecurityApp.services;

import org.springframework.stereotype.Service;
import ru.ugrinovich.FirstSecurityApp.models.Person;
import ru.ugrinovich.FirstSecurityApp.repositories.PeopleRepository;

import java.util.Optional;

@Service
public class PersonService {
    private final PeopleRepository peopleRepository;

    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(String name){
        return peopleRepository.findByUsername(name);
    }
}
