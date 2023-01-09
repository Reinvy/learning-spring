package com.learning.learningspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SupplierDTO {

    private Long id;

    @NotEmpty(message = "Name is required!")
    private String name;

    @NotEmpty(message = "Address is required!")
    private String address;

    @NotEmpty(message = "Email is required!")
    @Email(message = "Email is not valid!")
    private String email;
}
