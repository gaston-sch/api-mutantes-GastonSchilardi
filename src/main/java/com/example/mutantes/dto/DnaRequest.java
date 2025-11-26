package com.example.mutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequest {
    @NotNull(message = "El ADN no puede ser null")
    @NotEmpty(message = "El ADN no puede estar vacío")
    private String[] dna;
}