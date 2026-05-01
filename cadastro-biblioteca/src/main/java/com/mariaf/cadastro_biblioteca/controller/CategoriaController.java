package com.mariaf.cadastro_biblioteca.controller;


import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Categoria;
import com.mariaf.cadastro_biblioteca.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/categoria")

public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarCategoria(@RequestBody Categoria categoria){
        categoriaService.salvarCategoria(categoria);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}") //Buscar Por ID
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }

    @GetMapping //Buscar Por nome
    public ResponseEntity<Categoria> buscarCategoriaPorNome(@RequestParam String nome){
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorNomeIgnoreCase(nome));
    }

    @DeleteMapping
    public ResponseEntity<Categoria> deletarCategoriaPorId(@RequestParam Integer id){
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Categoria> atualizarCategoriaPorID(@RequestParam Integer id, @RequestBody Categoria categoria){
        categoriaService.atualizarCategoriaPorId(id, categoria);
        return ResponseEntity.ok().build();
    }
}
