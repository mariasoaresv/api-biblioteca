package com.mariaf.cadastro_biblioteca.controller;


import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Autor;
import com.mariaf.cadastro_biblioteca.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autor")

public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarAutor(@RequestBody Autor autor){
        autorService.salvarAutor(autor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")//Buscar Por ID
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Integer id){
        return ResponseEntity.ok(autorService.buscarAutorPorId(id));
    }

    @GetMapping //Buscar Por nome
    public ResponseEntity<Autor> buscarAutorPorNome(@RequestParam String nome){
        return ResponseEntity.ok(autorService.buscarAutorPorNomeIgnoreCase(nome));
    }

    @DeleteMapping
    public ResponseEntity<Autor> deletarAutorPorId(@RequestParam Integer id){
        autorService.deletarAutorPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Autor> atualizarUsuarioPorID(@RequestParam Integer id, @RequestBody Autor autor){
        autorService.atualizarAutorPorID(id, autor);
        return ResponseEntity.ok().build();
    }
}
