package com.brunostaine.api.gestor.financeiro.web.controllers;

import com.brunostaine.api.gestor.financeiro.entities.Usuario;
import com.brunostaine.api.gestor.financeiro.services.UsuarioService;
import com.brunostaine.api.gestor.financeiro.web.dtos.UsuarioCreateDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.UsuarioResponseDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.UsuarioSenhaDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO createDTO) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USUARIO') AND #id == authentication.principal.id")
    public ResponseEntity<Usuario> getById(@PathVariable UUID id){
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USUARIO') AND (#id == authentication.principal.id)")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID id, @Valid @RequestBody UsuarioSenhaDTO dto) {
        usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(users);
    }


}
