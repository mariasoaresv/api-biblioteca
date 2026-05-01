package com.mariaf.cadastro_biblioteca.service;

import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Categoria;
import com.mariaf.cadastro_biblioteca.infraestructure.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    //Post Categoria
    public void salvarCategoria(Categoria categoria) {
        repository.saveAndFlush(categoria);
    }

    //Get categoria por ID
    public Categoria buscarCategoriaPorId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado.")
        );
    }

    //Get categoria por nome
    public Categoria buscarCategoriaPorNomeIgnoreCase(String nome){
        return repository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new RuntimeException("Categoria não encontrada.")
        );
    }

    //Delete categoria por id
    @Transactional
    public void deletarCategoriaPorId(Integer id){
        repository.deleteById(id);
    }

    //Put
    public void atualizarCategoriaPorId(Integer id, Categoria categoria) {
        Categoria categoriaEntity = buscarCategoriaPorId(id);
        Categoria categoriaAtualizada = Categoria.builder()
                .nome(categoria.getNome() != null ?
                        categoria.getNome() : categoriaEntity.getNome())
                .descricao(categoria.getDescricao() != null ?
                        categoria.getDescricao() : categoriaEntity.getDescricao())
                .id(categoriaEntity.getId())
                .build();

        repository.saveAndFlush(categoriaAtualizada);
    }
}
