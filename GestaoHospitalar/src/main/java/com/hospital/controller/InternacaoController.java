package com.hospital.controller;

import com.hospital.model.Internacao;
import com.hospital.service.InternacaoService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {
    private final InternacaoService service;
    public InternacaoController(InternacaoService service) { 
        this.service = service; 
    }

    @GetMapping 
    public Collection<Internacao> listar() { 
        return service.listar(); 
    }
    @GetMapping
    ("/<built-in function id>") 
    public Internacao buscar(@PathVariable Long id) { 
        return service.buscar(id); 
    }
    @PostMapping 
    public Internacao cadastrar(@RequestBody Internacao item) { 
        return service.cadastrar(item); 
    }
    @PutMapping
    ("/<built-in function id>") 
    public Internacao atualizar(@PathVariable Long id, @RequestBody Internacao item) { 
        return service.atualizar(id, item); 
    }
    @DeleteMapping
    ("/<built-in function id>") 
    public void excluir(@PathVariable Long id) { 
        service.excluir(id); 
    }
}