package com.hospital.service;

import com.hospital.model.Consulta;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService extends CrudService<Consulta> {
    
@Override 
protected Long getId(Consulta item) { 
    return item.getId(); 
}
@Override 
protected void setId(Consulta item, Long id) { 
    item.setId(id); 
}
@Override 
protected void validar(Consulta item) {
    super.validar(item);
        if (!item.validarAtendimento()) throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Consulta incompatível com paciente/profissional ou dados obrigatórios.");
}
}
