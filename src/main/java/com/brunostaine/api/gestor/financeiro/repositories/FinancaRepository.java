package com.brunostaine.api.gestor.financeiro.repositories;

import com.brunostaine.api.gestor.financeiro.entities.Financa;
import com.brunostaine.api.gestor.financeiro.repositories.projection.FinancaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinancaRepository extends JpaRepository<Financa, UUID> {
    Page<FinancaProjection> findAllByUsuarioId(UUID id, Pageable pageable);
}
