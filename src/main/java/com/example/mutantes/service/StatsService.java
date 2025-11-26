package com.example.mutantes.service;

import com.example.mutantes.dto.StatsResponse;
import com.example.mutantes.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final DnaRecordRepository dnaRecordRepository;

    public StatsService(DnaRecordRepository dnaRecordRepository) {
        this.dnaRecordRepository = dnaRecordRepository;
    }

    public StatsResponse getStats() {
        long countMutant = dnaRecordRepository.countByIsMutant(true);
        long countHuman = dnaRecordRepository.countByIsMutant(false);

        double ratio = 0;
        if (countHuman > 0) {
            ratio = (double) countMutant / countHuman;
        } else if (countMutant > 0) {
            ratio = countMutant;
        }

        return new StatsResponse(countMutant, countHuman, ratio);
    }
}