package com.hospital.model;

public class MedicoEspecialista extends Profissional {
    public MedicoEspecialista() {}
    private String especialidade;
    public String getEspecialidade() { 
        return especialidade; 
    }
    public void setEspecialidade(String especialidade){ 
        this.especialidade = especialidade; 
    }
}