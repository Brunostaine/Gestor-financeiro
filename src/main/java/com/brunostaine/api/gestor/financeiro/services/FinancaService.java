package com.brunostaine.api.gestor.financeiro.services;

import com.brunostaine.api.gestor.financeiro.entities.Financa;
import com.brunostaine.api.gestor.financeiro.exceptions.CategoriaInvalidException;
import com.brunostaine.api.gestor.financeiro.exceptions.EntityNotFoundException;
import com.brunostaine.api.gestor.financeiro.exceptions.TipoInvalidException;
import com.brunostaine.api.gestor.financeiro.exceptions.ValorInvalidException;
import com.brunostaine.api.gestor.financeiro.repositories.FinancaRepository;
import com.brunostaine.api.gestor.financeiro.repositories.projection.FinancaProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FinancaService {

    private final FinancaRepository financaRepository;

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
}
