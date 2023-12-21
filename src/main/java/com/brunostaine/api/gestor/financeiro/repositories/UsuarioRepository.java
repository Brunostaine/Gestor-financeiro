package com.brunostaine.api.gestor.financeiro.repositories;

import com.brunostaine.api.gestor.financeiro.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);


}
