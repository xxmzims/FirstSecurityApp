package ru.ugrinovich.FirstSecurityApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthenticationDTO (

    @NotEmpty(message = "Имя неи должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    String username,

    String password){
}
