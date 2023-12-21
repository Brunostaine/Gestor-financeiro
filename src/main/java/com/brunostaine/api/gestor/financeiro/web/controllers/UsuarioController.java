package com.brunostaine.api.gestor.financeiro.web.controllers;

import com.brunostaine.api.gestor.financeiro.entities.Usuario;
import com.brunostaine.api.gestor.financeiro.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario createDTO) {
        Usuario user = usuarioService.salvar(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getAll(@PathVariable UUID id){
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getById(){
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(users);
    }
}
