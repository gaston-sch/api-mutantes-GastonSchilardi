package com.example.mutantes.service;

import com.example.mutantes.entity.DnaRecord;
import com.example.mutantes.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class MutantService {

    private final MutantDetector mutantDetector;
    private final DnaRecordRepository dnaRecordRepository;

    public MutantService(MutantDetector mutantDetector, DnaRecordRepository dnaRecordRepository) {
        this.mutantDetector = mutantDetector;
        this.dnaRecordRepository = dnaRecordRepository;
    }

    public boolean analyzeDna(String[] dna) {
        // 1. Calculamos Hash
        String dnaHash = Arrays.toString(dna);

        // 2. Buscamos en BD
        Optional<DnaRecord> existingRecord = dnaRecordRepository.findByDnaHash(dnaHash);

        if (existingRecord.isPresent()) {
            return existingRecord.get().isMutant();
        }

        // 3. Analizamos con el algoritmo
        boolean isMutant = mutantDetector.isMutant(dna);

        // 4. Guardamos
        DnaRecord newRecord = new DnaRecord(dnaHash, isMutant);
        dnaRecordRepository.save(newRecord);

        return isMutant;
    }
}