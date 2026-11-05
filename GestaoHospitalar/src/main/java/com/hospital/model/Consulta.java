package com.hospital.model;

import com.hospital.enums.StatusConsulta;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ConsultaRotina.class, name = "rotina"),
    @JsonSubTypes.Type(value = ConsultaEspecializada.class, name = "especializada"),
    @JsonSubTypes.Type(value = AtendimentoEmergencia.class, name = "emergencia")
})
public abstract class Consulta {
    protected Long id;
    protected LocalDate data;
    protected LocalTime horario;
    protected Paciente paciente;
    protected Profissional profissional;
    protected String motivo;
    protected String observacoes;
    protected StatusConsulta status = StatusConsulta.AGENDADA;

    public Consulta(){}

    public abstract boolean validarAtendimento();

    public Long getId(){ 
        return id; 
    }
    public void setId(Long id){ 
        this.id = id;
    }
    public LocalDate getData(){ 
        return data; 
    }
    public void setData(LocalDate data){ 
        this.data = data;
    }
    public LocalTime getHorario(){ 
        return horario; 
    }
    public void setHorario(LocalTime horario){ 
        this.horario = horario; 
    }
    public Paciente getPaciente(){ 
        return paciente; 
    }
    public void setPaciente(Paciente paciente){ 
        this.paciente = paciente; 
    }
    public Profissional getProfissional(){ 
        return profissional; 
    }
    public void setProfissional(Profissional profissional){ 
        this.profissional = profissional; 
    }
    public String getMotivo(){ 
        return motivo; 
    }
    public void setMotivo(String motivo){ 
        this.motivo = motivo; 
    }
    public String getObservacoes(){ 
        return observacoes; 
    }
    public void setObservacoes(String observacoes){ 
        this.observacoes = observacoes; 
    }
    public StatusConsulta getStatus(){ 
        return status; 
    }
    public void setStatus(StatusConsulta status){ 
        this.status = status; 
    }
}