package com.brunostaine.api.gestor.financeiro.services;

import com.brunostaine.api.gestor.financeiro.entities.Meta;
import com.brunostaine.api.gestor.financeiro.entities.enums.Status;
import com.brunostaine.api.gestor.financeiro.exceptions.EntityNotFoundException;
import com.brunostaine.api.gestor.financeiro.repositories.MetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MetaService {

    private final MetaRepository metaRepository;

    @Transactional
    public Meta salvar(Meta meta) {

        return metaRepository.save(meta);
    }

    @Transactional(readOnly = true)
    public List<Meta> buscarTodas() {
        return metaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Meta buscarPorId(UUID id) {
        return metaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Meta id=%s não encontrado", id))
        );
    }

    @Transactional
    public Meta editarMeta(UUID id, String titulo, Status status) {
        Meta meta = buscarPorId(id);
        meta.setTitle(titulo);
        meta.setStatus(status);
        return meta;
    }

    @Transactional
    public void excluirMeta(UUID id) {
        try {
            metaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(String.format("Meta com ID %s não encontrada", id));
        } catch (DataAccessException ex) {
            throw new RuntimeException("Erro ao excluir a meta", ex);
        }
    }
}
