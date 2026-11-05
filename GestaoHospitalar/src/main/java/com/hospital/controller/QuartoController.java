package com.hospital.controller;

import com.hospital.model.Quarto;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/quartos")
public class QuartoController {
    private final Map<Long, Quarto> dados = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    @GetMapping
    public Collection<Quarto> listar() {
        return dados.values();
    }

    @GetMapping("/{id}")
    public Quarto buscar(@PathVariable Long id) {
        return dados.get(id);
    }

    @PostMapping
    public Quarto cadastrar(@RequestBody Quarto item) {
        item.setId(sequencia.getAndIncrement());
        dados.put(item.getId(), item);
        return item;
    }

    @PutMapping("/{id}")
    public Quarto atualizar(@PathVariable Long id, @RequestBody Quarto item) {
        item.setId(id);
        dados.put(id, item);
        return item;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        dados.remove(id);
    }
}