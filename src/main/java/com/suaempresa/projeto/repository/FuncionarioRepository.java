package com.suaempresa.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suaempresa.projeto.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}