package com.mariaf.cadastro_biblioteca.infraestructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "categoria")
@Entity

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Por favor, informe o nome da categoria!")
    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descricao")
    private String descricao;
}