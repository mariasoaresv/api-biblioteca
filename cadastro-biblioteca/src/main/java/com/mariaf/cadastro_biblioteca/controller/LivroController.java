package com.mariaf.cadastro_biblioteca.controller;

import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Livro;
import com.mariaf.cadastro_biblioteca.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livro")

public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarLivro(@RequestBody Livro livro){
        livroService.salvarLivro(livro);
        return ResponseEntity.ok().build();
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

    @DeleteMapping
    public ResponseEntity<Livro> deletarLivroPorId(@RequestParam Integer id){
        livroService.deletarLivroPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Livro> atualizarUsuarioPorID(@RequestParam Integer id, @RequestBody Livro livro){
        livroService.atualizarLivroPorID(id, livro);
        return ResponseEntity.ok().build();
    }
}