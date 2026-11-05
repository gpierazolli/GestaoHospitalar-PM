package com.hospital.controller;

import com.hospital.model.Quarto;
import com.hospital.service.QuartoService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/quartos")
public class QuartoController {
    private final QuartoService service;
    public QuartoController(QuartoService service) { 
        this.service = service; 
    }

    @GetMapping 
    public Collection<Quarto> listar() { 
        return service.listar(); 
    }
    @GetMapping("/<built-in function id>") 
    public Quarto buscar(@PathVariable Long id) { 
        return service.buscar(id); 
    }
    @PostMapping 
    public Quarto cadastrar(@RequestBody Quarto item) { 
        return service.cadastrar(item); 
    }
    @PutMapping
    ("/<built-in function id>") 
    public Quarto atualizar(@PathVariable Long id, @RequestBody Quarto item) { 
        return service.atualizar(id, item); 
    }
    @DeleteMapping("/<built-in function id>") 
    public void excluir(@PathVariable Long id) { 
        service.excluir(id); 
    }
}