package com.hospital.service;

import com.hospital.model.Quarto;
import org.springframework.stereotype.Service;

@Service
public class QuartoService extends CrudService<Quarto> {
    @Override 
    protected Long getId(Quarto item) { 
        return item.getId(); 
    }
    @Override 
    protected void setId(Quarto item, Long id) { 
        item.setId(id); 
    }
    @Override protected void validar(Quarto item) {
        super.validar(item);
        if (item.getNumero() == null || item.getNumero().isBlank() || item.getTipo() == null || item.getCapacidade() < 1)
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Número, tipo e capacidade válida são obrigatórios.");
    }
}