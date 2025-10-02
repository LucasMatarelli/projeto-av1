package com.suaempresa.projeto.controller;

import com.suaempresa.projeto.model.Funcionario;
import com.suaempresa.projeto.repository.DepartamentoRepository;
import com.suaempresa.projeto.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/funcionarios")
public class FuncionarioWebController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "lista-funcionarios";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "form-funcionario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Funcionario funcionario) {
        // Se o formulário enviar apenas id do departamento, precisamos buscá-lo
        if (funcionario.getDepartamento() != null && funcionario.getDepartamento().getId() != null) {
            departamentoRepository.findById(funcionario.getDepartamento().getId())
                    .ifPresent(funcionario::setDepartamento);
        }
        funcionarioRepository.save(funcionario);
        return "redirect:/web/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "form-funcionario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/web/funcionarios";
    }
}