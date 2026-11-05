package com.hospital.service;

import com.hospital.model.Profissional;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalService extends CrudService<Profissional> {
    @Override 
    protected Long getId(Profissional item) { 
        return item.getId(); 
    }
    @Override 
    protected void setId(Profissional item, Long id) { 
        item.setId(id); 
    }
}
