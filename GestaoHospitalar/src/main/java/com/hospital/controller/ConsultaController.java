package com.hospital.controller;

import com.hospital.model.Consulta;
import com.hospital.service.ConsultaService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService service;
    public ConsultaController(ConsultaService service) { 
        this.service = service; 
    }

    @GetMapping 
    public Collection<Consulta> listar() { 
        return service.listar(); 
    }
    @GetMapping
    ("/<built-in function id>") 
    public Consulta buscar(@PathVariable Long id) { 
        return service.buscar(id); 
    }
    @PostMapping 
    public Consulta cadastrar(@RequestBody Consulta item) { 
        return service.cadastrar(item); 
    }
    @PutMapping
    ("/<built-in function id>") 
    public Consulta atualizar(@PathVariable Long id, @RequestBody Consulta item) { 
        return service.atualizar(id, item); 
    }
    @DeleteMapping
    ("/<built-in function id>") 
    public void excluir(@PathVariable Long id) { 
        service.excluir(id); 
    }
}