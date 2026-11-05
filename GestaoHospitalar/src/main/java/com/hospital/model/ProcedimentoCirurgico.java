package com.hospital.model;

import com.hospital.enums.StatusCirurgia;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProcedimentoCirurgico {
    private Long id;
    private String descricao;
    private String tipoCirurgia;
    private String observacoesPreOperatorias;
    private String observacoesPosOperatorias;
    private Paciente paciente;
    private Cirurgiao cirurgiao;
    private LocalDate dataPrevista;
    private LocalTime horarioPrevisto;
    private LocalDate dataReal;
    private LocalTime horarioReal;
    private StatusCirurgia status = StatusCirurgia.AGENDADO;

    public boolean concluir() {
        if (status != StatusCirurgia.EM_ANDAMENTO) {
            return false;
        }
        status = StatusCirurgia.CONCLUIDO;
        return true;
    }

    public Long getId(){ 
        return id; 
    }
    public void setId(Long id){ 
        this.id = id; 
    }
    public String getDescricao(){ 
        return descricao; 
    }
    public void setDescricao(String descricao){ 
        this.descricao = descricao; 
    }
    public String getTipoCirurgia(){ 
        return tipoCirurgia; 
    }
    public void setTipoCirurgia(String tipoCirurgia){ 
        this.tipoCirurgia = tipoCirurgia; 
    }
    public String getObservacoesPreOperatorias(){ 
        return observacoesPreOperatorias; 
    }
    public void setObservacoesPreOperatorias(String observacoesPreOperatorias){ 
        this.observacoesPreOperatorias = observacoesPreOperatorias; 
    }
    public String getObservacoesPosOperatorias(){ 
        return observacoesPosOperatorias; 
    }
    public void setObservacoesPosOperatorias(String observacoesPosOperatorias){ 
        this.observacoesPosOperatorias = observacoesPosOperatorias; 
    }
    public Paciente getPaciente(){ 
        return paciente; 
    }
    public void setPaciente(Paciente paciente){ 
        this.paciente = paciente; 
    }
    public Cirurgiao getCirurgiao(){ 
        return cirurgiao; 
    }
    public void setCirurgiao(Cirurgiao cirurgiao){ 
        this.cirurgiao = cirurgiao; 
    }
    public LocalDate getDataPrevista(){ 
        return dataPrevista; 
    }
    public void setDataPrevista(LocalDate dataPrevista){ 
        this.dataPrevista = dataPrevista; 
    }
    public LocalTime getHorarioPrevisto(){ 
        return horarioPrevisto; 
    }
    public void setHorarioPrevisto(LocalTime horarioPrevisto){ 
        this.horarioPrevisto = horarioPrevisto; 
    }
    public LocalDate getDataReal(){ 
        return dataReal; 
    }
    public void setDataReal(LocalDate dataReal){ 
        this.dataReal = dataReal; 
    }
    public LocalTime getHorarioReal(){
        return horarioReal; 
    }
    public void setHorarioReal(LocalTime horarioReal){ 
        this.horarioReal = horarioReal; 
    }
    public StatusCirurgia getStatus(){ 
        return status; 
    }
    public void setStatus(StatusCirurgia status){ 
        this.status = status; 
    }
}