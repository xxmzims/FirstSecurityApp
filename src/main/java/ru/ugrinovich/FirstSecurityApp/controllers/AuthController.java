package ru.ugrinovich.FirstSecurityApp.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.FirstSecurityApp.dto.PersonDTO;
import ru.ugrinovich.FirstSecurityApp.dto.PersonMapper;
import ru.ugrinovich.FirstSecurityApp.models.Person;
import ru.ugrinovich.FirstSecurityApp.security.JWTUtil;
import ru.ugrinovich.FirstSecurityApp.services.RegistrationService;
import ru.ugrinovich.FirstSecurityApp.util.PersonValidator;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final PersonMapper personMapper;

    public AuthController(RegistrationService registrationService, PersonValidator personValidator, JWTUtil jwtUtil, PersonMapper personMapper) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;

        this.personMapper = personMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){

        return "auth/registration";
    }
    @PostMapping("/registration")
    public ResponseEntity<String> performRegistration(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        Person person = personMapper.fromPersonCreateDTO(personDTO);
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().toString());

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }
}

