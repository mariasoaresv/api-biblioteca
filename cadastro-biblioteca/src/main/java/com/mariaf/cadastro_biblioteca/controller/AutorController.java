package com.mariaf.cadastro_biblioteca.controller;


import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Autor;
import com.mariaf.cadastro_biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")

public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarAutor(@Valid @RequestBody Autor autor){
        autorService.salvarAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Autor>> buscarTodosAutor() {
        return ResponseEntity.ok(autorService.buscarTodosAutor());
    }

    @GetMapping("/{id}")//Buscar Por ID
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Integer id){
        return ResponseEntity.ok(autorService.buscarAutorPorId(id));
    }

    @GetMapping("/nome")//Buscar Por nome
    public ResponseEntity<Autor> buscarAutorPorNome(@RequestParam String nome){
        return ResponseEntity.ok(autorService.buscarAutorPorNomeIgnoreCase(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Autor> deletarAutorPorId(@PathVariable Integer id){
        autorService.deletarAutorPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarUsuarioPorID(@PathVariable Integer id, @RequestBody Autor autor){
        autorService.atualizarAutorPorID(id, autor);
        return ResponseEntity.ok().build();
    }
}
