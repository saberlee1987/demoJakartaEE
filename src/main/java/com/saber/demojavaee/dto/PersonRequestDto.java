package com.saber.demojavaee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonRequestDto {
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "nationalCode is required")
    private String nationalCode;
    @NotNull(message = "age is required")
    private Integer age;
    @NotBlank(message = "mobile is required")
    private String mobile;
    @NotBlank(message = "email is required")
    private String email;
}