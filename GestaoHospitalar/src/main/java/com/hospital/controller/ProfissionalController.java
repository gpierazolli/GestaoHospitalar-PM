package com.hospital.controller;

import com.hospital.model.Profissional;
import com.hospital.service.ProfissionalService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {
    private final ProfissionalService service;
    public ProfissionalController(ProfissionalService service) { 
        this.service = service; 
    }

    @GetMapping 
    public Collection<Profissional> listar() { 
        return service.listar(); 
    }
    @GetMapping
    ("/<built-in function id>") 
    public Profissional buscar(@PathVariable Long id) { 
        return service.buscar(id); 
    }
    @PostMapping 
    public Profissional cadastrar(@RequestBody Profissional item) { 
        return service.cadastrar(item); 
    }
    @PutMapping
    ("/<built-in function id>") 
    public Profissional atualizar(@PathVariable Long id, @RequestBody Profissional item) { 
        return service.atualizar(id, item); 
    }
    @DeleteMapping("/<built-in function id>") 
    public void excluir(@PathVariable Long id) { 
        service.excluir(id); 
    }
}