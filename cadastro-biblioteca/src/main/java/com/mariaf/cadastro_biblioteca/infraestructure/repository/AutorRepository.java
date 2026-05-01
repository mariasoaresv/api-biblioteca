package com.mariaf.cadastro_biblioteca.infraestructure.repository;

import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findByNomeIgnoreCase(String nome);

}
