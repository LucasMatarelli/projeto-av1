package com.suaempresa.projeto.controller;

import com.suaempresa.projeto.model.Funcionario;
import com.suaempresa.projeto.repository.DepartamentoRepository;
import com.suaempresa.projeto.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioRestController {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @PostMapping
    public ResponseEntity<Funcionario> criar(@RequestBody Funcionario funcionario) {
        if (funcionario.getDepartamento() == null || funcionario.getDepartamento().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Long deptoId = funcionario.getDepartamento().getId();

        return departamentoRepository.findById(deptoId)
                .map(depto -> {
                    funcionario.setDepartamento(depto);
                    Funcionario salvo = repository.save(funcionario);
                    return ResponseEntity.ok(salvo);
                })
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario func) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(func.getNome());
                    record.setEmail(func.getEmail());
                    record.setDataAdmissao(func.getDataAdmissao());
                    if (func.getDepartamento() != null && func.getDepartamento().getId() != null) {
                        departamentoRepository.findById(func.getDepartamento().getId())
                                .ifPresent(record::setDepartamento);
                    }
                    Funcionario atualizado = repository.save(record);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
