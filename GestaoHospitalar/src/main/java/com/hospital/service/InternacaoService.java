package com.hospital.service;

import com.hospital.model.Internacao;
import org.springframework.stereotype.Service;

@Service
public class InternacaoService extends CrudService<Internacao> {
    
@Override 
protected Long getId(Internacao item) { 
    return item.getId(); 
}
@Override 
protected void setId(Internacao item, Long id) { 
    item.setId(id); 
}
@Override 
protected void validar(Internacao item) {
    super.validar(item);
    if (item.getQuarto() == null || item.getPaciente() == null || item.getDataEntrada() == null)
        throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Paciente, quarto e data de entrada são obrigatórios.");
    for (Internacao existente : dados.values()) {
        if (existente.getId().equals(item.getId())) continue;
        if (existente.estaAtiva() && existente.getQuarto() != null && existente.getQuarto().getId().equals(item.getQuarto().getId()))
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.CONFLICT, "Já existe internação ativa neste quarto/leito.");
        }    
    }
}
