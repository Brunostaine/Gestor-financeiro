package com.brunostaine.api.gestor.financeiro.repositories;

import com.brunostaine.api.gestor.financeiro.entities.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MetaRepository extends JpaRepository<Meta, UUID> {
}
