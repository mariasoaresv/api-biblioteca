package com.mariaf.cadastro_biblioteca.infraestructure.repository;


import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository <Categoria, Integer>{
    Optional<Categoria> findByNomeIgnoreCase(String nome);
}
