package com.brunostaine.api.gestor.financeiro.web.controllers;

import com.brunostaine.api.gestor.financeiro.config.jwt.JwtUserDetails;
import com.brunostaine.api.gestor.financeiro.entities.Financa;
import com.brunostaine.api.gestor.financeiro.repositories.projection.FinancaProjection;
import com.brunostaine.api.gestor.financeiro.services.FinancaService;
import com.brunostaine.api.gestor.financeiro.services.UsuarioService;
import com.brunostaine.api.gestor.financeiro.web.dtos.FinancaCreateDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.FinancaResponseDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.PageableDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.mapper.FinancaMapper;
import com.brunostaine.api.gestor.financeiro.web.dtos.mapper.PageableMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/financas")
public class FinancaController {

    private final FinancaService financaService;
    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<FinancaResponseDTO> create(@Valid @RequestBody FinancaCreateDTO dto,
                                                     @AuthenticationPrincipal JwtUserDetails userDetails){
        Financa financa = FinancaMapper.toFinanca(dto);
        financa.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        financaService.salvar(financa);
        return ResponseEntity.status(HttpStatus.CREATED).body(FinancaMapper.toDto(financa));
    }
    @GetMapping
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<PageableDTO> getAllFinancasDoUsuario(@AuthenticationPrincipal JwtUserDetails user,
                                                                       @PageableDefault(
                                                                              size = 5, sort = "dataCriacao",
                                                                              direction = Sort.Direction.ASC) Pageable pageable){
        Page<FinancaProjection> projection = financaService.buscarTodosPorUsuarioId(user.getId(), pageable);
        PageableDTO dto = PageableMapper.toDto(projection);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Void> updateFinancePorId(@PathVariable UUID id,
                                                   @Valid @RequestBody FinancaCreateDTO dto) throws AccessDeniedException {

        financaService.editarFinancaPorId(id, dto.getTipo(), dto.getValor(), dto.getCategoria(), dto.getDescricao());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Void> deleteFinancePorId(@PathVariable UUID id) throws AccessDeniedException {
        financaService.deletarFinancaPorId(id);
        return ResponseEntity.noContent().build();
    }

}
