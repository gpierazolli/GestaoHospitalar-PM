package com.hospital.controller;

import com.hospital.model.Profissional;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {
    private final Map<Long, Profissional> dados = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    @GetMapping
    public Collection<Profissional> listar() {
        return dados.values();
    }

    @GetMapping("/{id}")
    public Profissional buscar(@PathVariable Long id) {
        return dados.get(id);
    }

    @PostMapping
    public Profissional cadastrar(@RequestBody Profissional item) {
        item.setId(sequencia.getAndIncrement());
        dados.put(item.getId(), item);
        return item;
    }

    @PutMapping("/{id}")
    public Profissional atualizar(@PathVariable Long id, @RequestBody Profissional item) {
        item.setId(id);
        dados.put(id, item);
        return item;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        dados.remove(id);
    }
}