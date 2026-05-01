package com.mariaf.cadastro_biblioteca.infraestructure.repository;

import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    Optional<Livro> findByTituloIgnoreCase(String titulo);

    Optional<Livro> findByAutor_NomeIgnoreCase(String autorNome);
}
