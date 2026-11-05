package com.hospital.model;

import java.time.LocalDate;

public class Encaminhamento {
    private Long id;
    private Paciente paciente;
    private MedicoClinico medicoSolicitante;
    private Profissional profissionalDestino;
    private String especialidade;
    private String motivo;
    private LocalDate data = LocalDate.now();

    public Long getId(){ 
        return id; 
    } 
    public void setId(Long id){ 
        this.id=id; 
    }
    public Paciente getPaciente(){ 
        return paciente; 
    } 
    public void setPaciente(Paciente v){ 
        paciente=v; 
    }
    public MedicoClinico getMedicoSolicitante(){ 
        return medicoSolicitante; 
    } 
    public void setMedicoSolicitante(MedicoClinico v){
        medicoSolicitante=v; 
    }
    public Profissional getProfissionalDestino(){ 
        return profissionalDestino; 
    } 
    public void setProfissionalDestino(Profissional v){ 
        profissionalDestino=v; 
    }
    public String getEspecialidade(){ 
        return especialidade; 
    } 
    public void setEspecialidade(String v){ 
        especialidade=v; 
    }
    public String getMotivo(){ 
        return motivo; 
    } 
    public void setMotivo(String v){ 
        motivo=v; 
    }
    public LocalDate getData(){ 
        return data; 
    } 
    public void setData(LocalDate v){ 
        data=v; 
    }
}