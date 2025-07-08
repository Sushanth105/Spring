package com.rest.rest.student;

public record StudentResponseDto(
    Integer id,
    String firstName,
    String lastName,
    Integer age,
    String email,
    Integer schoolId
) {

}
