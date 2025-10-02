package com.suaempresa.projeto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private LocalDate dataAdmissao;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
}