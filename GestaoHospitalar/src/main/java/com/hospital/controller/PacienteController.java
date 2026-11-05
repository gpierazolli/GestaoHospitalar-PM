package com.hospital.controller;

import com.hospital.model.Paciente;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final Map<Long, Paciente> dados = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    @GetMapping
    public Collection<Paciente> listar() {
        return dados.values();
    }

    @GetMapping("/{id}")
    public Paciente buscar(@PathVariable Long id) {
        return dados.get(id);
    }

    @PostMapping
    public Paciente cadastrar(@RequestBody Paciente item) {
        item.setId(sequencia.getAndIncrement());
        dados.put(item.getId(), item);
        return item;
    }

    @PutMapping("/{id}")
    public Paciente atualizar(@PathVariable Long id, @RequestBody Paciente item) {
        item.setId(id);
        dados.put(id, item);
        return item;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        dados.remove(id);
    }
}