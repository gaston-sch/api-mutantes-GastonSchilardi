package com.example.mutantes.repository;

import com.example.mutantes.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    // Busca si ya analizamos este ADN antes
    Optional<DnaRecord> findByDnaHash(String dnaHash);

    // Cuenta cuántos mutantes o humanos hay (para /stats)
    long countByIsMutant(boolean isMutant);
}