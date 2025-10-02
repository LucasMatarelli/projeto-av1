package com.suaempresa.projeto.controller;

import com.suaempresa.projeto.model.Departamento;
import com.suaempresa.projeto.repository.DepartamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoRestController {

    @Autowired
    private DepartamentoRepository repository;

    @PostMapping
    public Departamento criar(@RequestBody Departamento departamento) {
        return repository.save(departamento);
    }

    @GetMapping
    public List<Departamento> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizar(@PathVariable Long id, @RequestBody Departamento depto) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(depto.getNome());
                    record.setLocalizacao(depto.getLocalizacao());
                    Departamento atualizado = repository.save(record);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}