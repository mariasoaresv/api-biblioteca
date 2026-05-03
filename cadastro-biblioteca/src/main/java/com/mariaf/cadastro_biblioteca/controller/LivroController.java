package com.mariaf.cadastro_biblioteca.controller;

import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Categoria;
import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Livro;
import com.mariaf.cadastro_biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")

public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarLivro(@Valid @RequestBody Livro livro){
        livroService.salvarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Livro>> buscarTodosLivro() {
        return ResponseEntity.ok(livroService.buscarTodosLivro());
    }

    @GetMapping("/{id}") //Buscar Por ID
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Integer id){
        return ResponseEntity.ok(livroService.buscarLivroPorId(id));
    }

    @GetMapping("/titulo")//Buscar Por titulo
    public ResponseEntity<Livro> buscarLivroPorTituloIgnoreCase(@RequestParam String titulo){
        return ResponseEntity.ok(livroService.buscarLivroPorTituloIgnoreCase(titulo));
    }

    @GetMapping("/autor") //Buscar Por Autor
    public ResponseEntity<Livro> buscarLivroPorAutorNomeIgnoreCase(@RequestParam String nome){
        return ResponseEntity.ok(livroService.buscarLivroPorAutorNomeIgnoreCase(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Livro> deletarLivroPorId(@PathVariable Integer id){
        livroService.deletarLivroPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarUsuarioPorID(@PathVariable Integer id, @RequestBody Livro livro){
        livroService.atualizarLivroPorID(id, livro);
        return ResponseEntity.ok().build();
    }
}