package com.suaempresa.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String localizacao;
}