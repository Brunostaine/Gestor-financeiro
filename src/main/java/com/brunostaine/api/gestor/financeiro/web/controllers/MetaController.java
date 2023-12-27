package com.brunostaine.api.gestor.financeiro.web.controllers;

import com.brunostaine.api.gestor.financeiro.config.jwt.JwtUserDetails;
import com.brunostaine.api.gestor.financeiro.entities.Meta;
import com.brunostaine.api.gestor.financeiro.services.MetaService;
import com.brunostaine.api.gestor.financeiro.services.UsuarioService;
import com.brunostaine.api.gestor.financeiro.web.dtos.MetaCreateDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.MetaResponseDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.mapper.MetaMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Tag(name = "Metas", description = "Recurso para trabalhar com metas")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/metas")
public class MetaController {

    private final MetaService metaService;
    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<MetaResponseDTO> create(@Valid @RequestBody MetaCreateDTO dto,
                                                  @AuthenticationPrincipal JwtUserDetails userDetails
                                                  ) {
        Meta meta = MetaMapper.toMeta(dto);
        meta.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        metaService.salvar(meta);
        return ResponseEntity.status(201).body(MetaMapper.toDto(meta));

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
