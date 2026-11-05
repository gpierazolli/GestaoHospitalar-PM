package com.hospital.controller;

import com.hospital.model.Consulta;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final Map<Long, Consulta> dados = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    @GetMapping
    public Collection<Consulta> listar() {
        return dados.values();
    }

    @GetMapping("/{id}")
    public Consulta buscar(@PathVariable Long id) {
        return dados.get(id);
    }

    @PostMapping
    public Consulta cadastrar(@RequestBody Consulta item) {
        item.setId(sequencia.getAndIncrement());
        dados.put(item.getId(), item);
        return item;
    }

    @PutMapping("/{id}")
    public Consulta atualizar(@PathVariable Long id, @RequestBody Consulta item) {
        item.setId(id);
        dados.put(id, item);
        return item;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        dados.remove(id);
    }
}