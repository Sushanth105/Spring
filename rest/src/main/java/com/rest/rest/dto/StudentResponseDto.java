package com.rest.rest.dto;

public record StudentResponseDto(
    Integer id,
    String firstName,
    String lastName,
    int age,
    String email,
    int schoolId
) {

}
