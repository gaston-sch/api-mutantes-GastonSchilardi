package com.example.mutantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DetectorMutantesApplication { // ¡El nombre ahora coincide con el archivo!

    public static void main(String[] args) {
        SpringApplication.run(DetectorMutantesApplication.class, args); // También actualizamos esto
    }

}