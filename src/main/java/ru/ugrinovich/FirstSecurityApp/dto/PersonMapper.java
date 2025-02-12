package ru.ugrinovich.FirstSecurityApp.dto;

import org.mapstruct.Mapper;
import ru.ugrinovich.FirstSecurityApp.models.Person;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toPersonDTO(Person person);

    AuthenticationDTO toAuthenticationDTO(Person person);

    List<PersonDTO> toPersonDTOList(List<Person> people);

    List<AuthenticationDTO> toAuthenticationDTO(List<Person> people);

    Person fromPersonCreateDTO(PersonDTO personDTO);

    Person fromPersonCreateDTO(AuthenticationDTO authenticationDTO);
}
