package com.hospital.controller;

import com.hospital.model.Paciente;
import com.hospital.service.PacienteService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService service;
    public PacienteController(PacienteService service) { 
        this.service = service; 
    }

    @GetMapping 
    public Collection<Paciente> listar() { 
        return service.listar(); 
    }
    @GetMapping
    ("/<built-in function id>") 
    public Paciente buscar(@PathVariable Long id) { 
        return service.buscar(id); 
    }
    @PostMapping 
    public Paciente cadastrar(@RequestBody Paciente item) { 
        return service.cadastrar(item); 
    }
    @PutMapping
    ("/<built-in function id>") 
    public Paciente atualizar(@PathVariable Long id, @RequestBody Paciente item) { 
        return service.atualizar(id, item); 
    }
    @DeleteMapping
    ("/<built-in function id>") 
    public void excluir(@PathVariable Long id) { 
        service.excluir(id); 
    }
}
