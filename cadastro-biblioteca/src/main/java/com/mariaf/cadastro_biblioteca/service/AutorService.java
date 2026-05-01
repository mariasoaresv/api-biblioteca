package com.mariaf.cadastro_biblioteca.service;


import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Autor;
import com.mariaf.cadastro_biblioteca.infraestructure.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    //Post Autor
    public void salvarAutor(Autor autor) {
        repository.saveAndFlush(autor);
    }

    //Get autor por ID
    public Autor buscarAutorPorId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado.")
        );
    }

    //Get autor por nome
    public Autor buscarAutorPorNomeIgnoreCase(String nome){
        return repository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new RuntimeException("Um autor com este nome não foi encontrado.")
        );
    }

    //Delete autor por id (id garante precisão por ser único)
    @Transactional
    public void deletarAutorPorId(Integer id){
        repository.deleteById(id);
    }

    //Put
    public void atualizarAutorPorID(Integer id, Autor autor) {
        Autor autorEntity = buscarAutorPorId(id);
        Autor autorAtualizado = Autor.builder()
                .nome(autor.getNome() != null ?
                        autor.getNome() : autorEntity.getNome())
                .nacionalidade(autor.getNacionalidade() != null ?
                        autor.getNacionalidade() : autorEntity.getNacionalidade())
                .dataNascimento(autor.getDataNascimento() != null ?
                        autor.getDataNascimento() : autorEntity.getDataNascimento())
                .id(autorEntity.getId())
                .build();

        repository.saveAndFlush(autorAtualizado);
        }

}
