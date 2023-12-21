package com.brunostaine.api.gestor.financeiro.services;

import com.brunostaine.api.gestor.financeiro.entities.Usuario;
import com.brunostaine.api.gestor.financeiro.exceptions.EmailUniqueViolationException;
import com.brunostaine.api.gestor.financeiro.exceptions.EntityNotFoundException;
import com.brunostaine.api.gestor.financeiro.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        var emailExists = usuarioRepository.findByEmail(usuario.getEmail());
        if(emailExists.isPresent()) {
            throw new EmailUniqueViolationException(String.format("E-mail: '%s' já cadastrado", usuario.getEmail()));
        } else {
            return usuarioRepository.save(usuario);
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
        );
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

}
