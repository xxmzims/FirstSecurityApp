package ru.ugrinovich.FirstSecurityApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PersonDTO(
    @NotEmpty(message = "Имя неи должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    String username,

    @Min(value = 1900, message = "Год рождение должен быть больше, чем " + "1900")
    int yearOfBirth,

    String password){
}
