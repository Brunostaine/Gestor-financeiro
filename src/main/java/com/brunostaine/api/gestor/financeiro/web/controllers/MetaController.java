package com.brunostaine.api.gestor.financeiro.web.controllers;

import com.brunostaine.api.gestor.financeiro.entities.Meta;
import com.brunostaine.api.gestor.financeiro.services.MetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/metas")
public class MetaController {

    private final MetaService metaService;

    @PostMapping
    public ResponseEntity<Meta> create(@Valid @RequestBody Meta meta) {
        Meta novaMeta = metaService.salvar(meta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMeta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meta> getById(@PathVariable UUID id) {
        Meta meta = metaService.buscarPorId(id);
        return ResponseEntity.ok(meta);
    }

    @GetMapping
    public ResponseEntity<List<Meta>> getAll() {
        List<Meta> metas = metaService.buscarTodas();
        return ResponseEntity.ok(metas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meta> update(@PathVariable UUID id, @Valid @RequestBody Meta meta) {
        Meta updateMeta = metaService.editarMeta(id, meta.getTitle(), meta.getStatus());
        return ResponseEntity.ok(updateMeta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        metaService.excluirMeta(id);
        return ResponseEntity.noContent().build();
    }
}
