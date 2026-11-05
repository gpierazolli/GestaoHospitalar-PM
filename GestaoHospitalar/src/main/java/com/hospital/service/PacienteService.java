package com.hospital.service;

import com.hospital.model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteService extends CrudService<Paciente> {
    @Override 
    protected Long getId(Paciente item) { 
        return item.getId(); 
    }
    @Override 
    protected void setId(Paciente item, Long id) { 
        item.setId(id); 
    }
}
