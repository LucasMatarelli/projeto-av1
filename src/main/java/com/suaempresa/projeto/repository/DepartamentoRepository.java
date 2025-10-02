package com.suaempresa.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suaempresa.projeto.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}