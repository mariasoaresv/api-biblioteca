package com.mariaf.cadastro_biblioteca.service;

import com.mariaf.cadastro_biblioteca.infraestructure.entitys.Livro;
import com.mariaf.cadastro_biblioteca.infraestructure.repository.CategoriaRepository;
import com.mariaf.cadastro_biblioteca.infraestructure.repository.LivroRepository;
import com.mariaf.cadastro_biblioteca.infraestructure.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private AutorRepository autorRepository; // Para validar se o autor existe
    @Autowired
    private CategoriaRepository categoriaRepository; // Para validar se a categoria existe
    @Autowired
    private LivroRepository livroRepository;


    //Post Livro
    public void salvarLivro(Livro livro) {
        if (livro.getAutor().getId() == null || !autorRepository.existsById(livro.getAutor().getId())) {
            throw new RuntimeException("Este autor ainda não foi cadastrado no sistema!");
        }
        if (livro.getCategoria().getId() == null || !categoriaRepository.existsById(livro.getCategoria().getId())) {
            throw new RuntimeException("Esta categoria ainda não foi cadastrada no sistema!");
        }
        livroRepository.saveAndFlush(livro);
    }

    public List<Livro> buscarTodosLivro() {
        return livroRepository.findAll();
    }

    //Get livro por ID
    public Livro buscarLivroPorId(Integer id){
        return livroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado.")
        );
    }

    //Get livro por titulo
    public Livro buscarLivroPorTituloIgnoreCase(String titulo){
        return livroRepository.findByTituloIgnoreCase(titulo).orElseThrow(
                () -> new RuntimeException("Um livro com esse título não foi encontrado.")
        );
    }

    public Livro buscarLivroPorAutorNomeIgnoreCase(String autorNome){
        return livroRepository.findByAutor_NomeIgnoreCase(autorNome).orElseThrow(
                () -> new RuntimeException("Um livro com esse autor não foi encontrado.")
        );
    }

    //Delete livro por id (id garante precisão por ser único)
    @Transactional
    public void deletarLivroPorId(Integer id){
        buscarLivroPorId(id);
        livroRepository.deleteById(id);
    }

    //Put
    public void atualizarLivroPorID(Integer id, Livro livro) {
        Livro livroEntity = buscarLivroPorId(id);
        Livro livroAtualizado = Livro.builder()
                .titulo(livro.getTitulo() != null ?
                        livro.getTitulo() : livroEntity.getTitulo())
                .anoPublicacao(livro.getAnoPublicacao() != null ?
                        livro.getAnoPublicacao() : livroEntity.getAnoPublicacao())
                .descricao(livro.getDescricao() != null ?
                        livro.getDescricao() : livroEntity.getDescricao())
                .capa(livro.getCapa() != null ?
                        livro.getCapa() : livroEntity.getCapa())
                .autor(livro.getAutor() != null ?
                        livro.getAutor() : livroEntity.getAutor())
                .categoria(livro.getCategoria() != null ?
                        livro.getCategoria() : livroEntity.getCategoria())
                .id(livroEntity.getId())
                .build();

        livroRepository.saveAndFlush(livroAtualizado);
    }
}
