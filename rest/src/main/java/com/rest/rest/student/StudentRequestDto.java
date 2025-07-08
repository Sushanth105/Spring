package com.rest.rest.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentRequestDto(
    @NotEmpty
    String firstName,
    String lastName,
    @NotNull
    Integer age,
    @NotEmpty
    @Email
    String email,
    @NotNull
    Integer schoolId
) {

}
