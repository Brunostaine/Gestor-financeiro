package com.brunostaine.api.gestor.financeiro.repositories;

import com.brunostaine.api.gestor.financeiro.entities.Financa;
import com.brunostaine.api.gestor.financeiro.repositories.projection.FinancaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface FinancaRepository extends JpaRepository<Financa, UUID> {
    Page<FinancaProjection> findAllByUsuarioId(UUID id, Pageable pageable);
    @Query("SELECT f.usuario.id FROM Financa f WHERE f.id = :financaId")
    UUID findUsuarioIdByFinancaId(@Param("financaId") UUID financaId);
}
