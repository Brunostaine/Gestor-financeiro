package com.brunostaine.api.gestor.financeiro.services;

import com.brunostaine.api.gestor.financeiro.entities.Financa;
import com.brunostaine.api.gestor.financeiro.entities.enums.Categoria;
import com.brunostaine.api.gestor.financeiro.entities.enums.Tipo;
import com.brunostaine.api.gestor.financeiro.exceptions.CategoriaInvalidException;
import com.brunostaine.api.gestor.financeiro.exceptions.EntityNotFoundException;
import com.brunostaine.api.gestor.financeiro.exceptions.TipoInvalidException;
import com.brunostaine.api.gestor.financeiro.exceptions.ValorInvalidException;
import com.brunostaine.api.gestor.financeiro.repositories.FinancaRepository;
import com.brunostaine.api.gestor.financeiro.repositories.UsuarioRepository;
import com.brunostaine.api.gestor.financeiro.repositories.projection.FinancaProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FinancaService {

    private final FinancaRepository financaRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Financa salvar(Financa financa) {
        if (financa.getCategoria() == null) {
            throw new CategoriaInvalidException("Categoria inválida ou nula.");
        }
        if (financa.getTipo() == null) {
            throw new TipoInvalidException("Tipo inválido ou nulo.");
        }
        if (financa.getValor() == null) {
            throw new ValorInvalidException("Valor inválido ou nulo.");
        }
        return financaRepository.save(financa);
    }


    @Transactional(readOnly = true)
    public Financa buscarPorId(UUID id) {
        return financaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Financa id=%s não encontrado", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<FinancaProjection> buscarTodosPorUsuarioId(UUID id, Pageable pageable) {
        return financaRepository.findAllByUsuarioId(id, pageable);
    }

    @Transactional
    public Financa editarFinancaPorId(UUID id, String tipo, BigDecimal valor, String categoria, String descricao) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UUID usuarioIdAutenticado = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username)).getId();

        UUID usuarioIdByFinancaId = financaRepository.findUsuarioIdByFinancaId(id);

        if (!Objects.equals(usuarioIdAutenticado, usuarioIdByFinancaId)) {
            log.info("Usuario autenticado: {}", usuarioIdAutenticado);
            log.info("usuario id: {}", usuarioIdByFinancaId);
            log.warn("Acesso negado para editar esta finança.");
            throw new AccessDeniedException("Você não tem permissão para editar está finança");
        }

        Financa financa = buscarPorId(id);
        financa.setTipo(Tipo.valueOf(tipo));
        financa.setValor(valor);
        financa.setCategoria(Categoria.valueOf(categoria));
        financa.setDescricao(descricao);
        return financa;
    }

    public void deletarFinancaPorId(UUID id) throws AccessDeniedException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UUID usuarioIdAutenticado = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username)).getId();

        UUID usuarioIdByFinancaId = financaRepository.findUsuarioIdByFinancaId(id);

        if (!Objects.equals(usuarioIdAutenticado, usuarioIdByFinancaId)) {
            log.info("Usuario autenticado: {}", usuarioIdAutenticado);
            log.info("usuario id: {}", usuarioIdByFinancaId);
            log.warn("Acesso negado para deletar esta finança.");
            throw new AccessDeniedException("Você não tem permissão para deletar está finança");
        }

        financaRepository.deleteById(id);
    }
}
