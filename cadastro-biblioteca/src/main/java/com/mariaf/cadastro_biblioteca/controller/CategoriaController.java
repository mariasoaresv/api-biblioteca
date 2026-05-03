package com.mariaf.cadastro_biblioteca.controller;


import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Autor;
import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Categoria;
import com.mariaf.cadastro_biblioteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/categoria")

public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarCategoria(@Valid @RequestBody Categoria categoria){
        categoriaService.salvarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarTodosCategoria() {
        return ResponseEntity.ok(categoriaService.buscarTodosCategoria());
    }

    @GetMapping("/{id}") //Buscar Por ID
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }

    @GetMapping("/nome") //Buscar Por nome
    public ResponseEntity<Categoria> buscarCategoriaPorNome(@RequestParam String nome){
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorNomeIgnoreCase(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> deletarCategoriaPorId(@PathVariable Integer id){
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoriaPorID(@PathVariable Integer id, @RequestBody Categoria categoria){
        categoriaService.atualizarCategoriaPorId(id, categoria);
        return ResponseEntity.ok().build();
    }
}
