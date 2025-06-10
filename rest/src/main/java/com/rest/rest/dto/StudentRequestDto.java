package com.rest.rest.dto;

public record StudentRequestDto(
    String firstName,
    String lastName,
    int age,
    String email,
    int schoolId
) {

}
