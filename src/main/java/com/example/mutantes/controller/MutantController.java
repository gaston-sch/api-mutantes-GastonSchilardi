package com.example.mutantes.controller;

import com.example.mutantes.dto.DnaRequest;
import com.example.mutantes.dto.StatsResponse;
import com.example.mutantes.service.MutantService;
import com.example.mutantes.service.StatsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class MutantController {

    private final MutantService mutantService;
    private final StatsService statsService;

    public MutantController(MutantService mutantService, StatsService statsService) {
        this.mutantService = mutantService;
        this.statsService = statsService;
    }

    // Definimos la ruta explícitamente aquí
    @PostMapping("/mutant")
    public ResponseEntity<Void> checkMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.analyzeDna(dnaRequest.getDna());

        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    // Y definimos la otra ruta aquí
    @GetMapping("/stats")
    public StatsResponse getStats() {
        return statsService.getStats();
    }
}