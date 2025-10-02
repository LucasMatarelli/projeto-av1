package com.suaempresa.projeto.controller;

import com.suaempresa.projeto.model.Departamento;
import com.suaempresa.projeto.repository.DepartamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/departamentos")
public class DepartamentoWebController {

    @Autowired
    private DepartamentoRepository repository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("departamentos", repository.findAll());
        return "lista-departamentos";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "form-departamento";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Departamento departamento) {
        repository.save(departamento);
        return "redirect:/web/departamentos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Departamento departamento = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("departamento", departamento);
        return "form-departamento";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/web/departamentos";
    }
}