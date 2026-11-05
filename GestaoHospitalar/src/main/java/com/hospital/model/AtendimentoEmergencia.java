package com.hospital.model;

import com.hospital.enums.Prioridade;

public class AtendimentoEmergencia extends Consulta {
    private Prioridade prioridade;

    public Prioridade getPrioridade(){ 
        return prioridade; 
    }
    public void setPrioridade(Prioridade prioridade){ 
        this.prioridade = prioridade; 
    }

    @Override
    public boolean validarAtendimento() {
        return paciente != null && prioridade != null;
    }
}