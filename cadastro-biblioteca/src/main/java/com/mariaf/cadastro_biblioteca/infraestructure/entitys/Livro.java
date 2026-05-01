package com.mariaf.cadastro_biblioteca.infraestructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "livro")
@Entity

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank (message = "Por favor, informe o nome do livro!")
    @Column(name = "titulo", unique = true)
    private String titulo;

    @NotNull(message = "É necessário informar o ano da publicação do livro!")
    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "capa", unique = true)
    private String capa;

    @NotNull (message = "Este Campo não pode estar vazio, informe o autor do livro!")
    @ManyToOne
    @JoinColumn(name = "autor")
    private Autor autor;

    @NotNull(message = "Este Campo não pode estar vazio, informe a categoria do livro!")
    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;
}