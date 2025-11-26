package com.example.mutantes.service;

import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class MutantDetector {

    // Longitud de la secuencia a encontrar
    private static final int SEQUENCE_LENGTH = 4;
    // Patrón para validar que solo vengan letras permitidas (A, T, C, G)
    private static final Pattern VALID_PATTERN = Pattern.compile("^[ATCG]+$");

    public boolean isMutant(String[] dna) {
        // 1. Validaciones básicas (Nulos o vacíos)
        if (dna == null || dna.length == 0) return false;

        int n = dna.length;

        // 2. Validación de matriz cuadrada y caracteres válidos
        // Optimización: Convertimos a char[][] una sola vez para acceso rápido
        char[][] matrix = new char[n][n];

        for (int i = 0; i < n; i++) {
            if (dna[i] == null || dna[i].length() != n) return false; // No es NxN
            if (!VALID_PATTERN.matcher(dna[i]).matches()) return false; // Letras inválidas
            matrix[i] = dna[i].toCharArray();
        }

        int sequenceCount = 0;

        // 3. Recorremos la matriz buscando secuencias
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                // Buscamos en las 4 direcciones posibles, PERO solo si hay espacio

                // Horizontal (Hacia la derecha)
                if (col <= n - SEQUENCE_LENGTH) {
                    if (checkHorizontal(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true; // ¡Early Termination! Ya es mutante
                    }
                }

                // Vertical (Hacia abajo)
                if (row <= n - SEQUENCE_LENGTH) {
                    if (checkVertical(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }

                // Diagonal Principal (↘)
                if (row <= n - SEQUENCE_LENGTH && col <= n - SEQUENCE_LENGTH) {
                    if (checkDiagonalDescending(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }

                // Diagonal Inversa (↗)
                if (row >= SEQUENCE_LENGTH - 1 && col <= n - SEQUENCE_LENGTH) {
                    if (checkDiagonalAscending(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }
            }
        }

        return false; // Si llegamos aquí, no encontramos más de 1 secuencia
    }

    // Métodos auxiliares para comparar caracteres directamente (Más rápido que loops)

    private boolean checkHorizontal(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row][col + 1] == base &&
                matrix[row][col + 2] == base &&
                matrix[row][col + 3] == base;
    }

    private boolean checkVertical(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row + 1][col] == base &&
                matrix[row + 2][col] == base &&
                matrix[row + 3][col] == base;
    }

    private boolean checkDiagonalDescending(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row + 1][col + 1] == base &&
                matrix[row + 2][col + 2] == base &&
                matrix[row + 3][col + 3] == base;
    }

    private boolean checkDiagonalAscending(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row - 1][col + 1] == base &&
                matrix[row - 2][col + 2] == base &&
                matrix[row - 3][col + 3] == base;
    }
}