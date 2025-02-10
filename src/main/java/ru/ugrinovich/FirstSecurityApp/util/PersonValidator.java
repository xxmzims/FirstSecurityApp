package ru.ugrinovich.FirstSecurityApp.util;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ugrinovich.FirstSecurityApp.models.Person;
import ru.ugrinovich.FirstSecurityApp.services.PersonDetailsService;
import ru.ugrinovich.FirstSecurityApp.services.PersonService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;
    private final PersonDetailsService personDetailsService;

    public PersonValidator(PersonService personService, PersonDetailsService personDetailsService) {
        this.personService = personService;
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> optionalPerson = personService.findByUsername(person.getUsername());

        if(optionalPerson.isPresent()){
            errors.rejectValue("username", "","this username is already exist");
        }
    }
}
