package com.saber.demojavaee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonRequestDto {
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "nationalCode is required")
    @Size(min = 10,max = 10,message = "nationalCode must be 10 digits")
    private String nationalCode;
    @NotNull(message = "age is required")
    private Integer age;
    @NotBlank(message = "mobile is required")
    @Size(min = 11,max = 11,message = "mobile must be 11 digits")
    @Pattern(regexp = "09\\d{9}")
    private String mobile;
    @NotBlank(message = "email is required")
    private String email;
}