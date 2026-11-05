package com.hospital.controller;

import com.hospital.model.Internacao;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {
    private final Map<Long, Internacao> dados = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    @GetMapping
    public Collection<Internacao> listar() {
        return dados.values();
    }

    @GetMapping("/{id}")
    public Internacao buscar(@PathVariable Long id) {
        return dados.get(id);
    }

    @PostMapping
    public Internacao cadastrar(@RequestBody Internacao item) {
        item.setId(sequencia.getAndIncrement());
        dados.put(item.getId(), item);
        return item;
    }

    @PutMapping("/{id}")
    public Internacao atualizar(@PathVariable Long id, @RequestBody Internacao item) {
        item.setId(id);
        dados.put(id, item);
        return item;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        dados.remove(id);
    }
}