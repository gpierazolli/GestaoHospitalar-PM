package com.hospital.model;

import com.hospital.enums.StatusInternacao;
import java.time.LocalDate;

public class Internacao {
    private Long id;
    private Paciente paciente;
    private Quarto quarto;
    private String leito;
    private Profissional medicoResponsavel;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String motivo;
    private StatusInternacao status = StatusInternacao.ATIVA;
    private String observacoes;

    public boolean estaAtiva() { 
        return status == StatusInternacao.ATIVA || status == StatusInternacao.ALTA_PREVISTA; 
    }
    public Long getId(){ 
        return id; 
    } 
    public void setId(Long v){ 
        id=v; 
    }
    public Paciente getPaciente(){ 
        return paciente; 
    } 
    public void setPaciente(Paciente v){ 
        paciente=v; 
    }
    public Quarto getQuarto(){ 
        return quarto; 
    } 
    public void setQuarto(Quarto v){ 
        quarto=v; 
    }
    public String getLeito(){ 
        return leito; 
    } 
    public void setLeito(String v){ 
        leito=v; 
    }
    public Profissional getMedicoResponsavel(){ 
        return medicoResponsavel; 
    } 
    public void setMedicoResponsavel(Profissional v){ 
        medicoResponsavel=v; 
    }
    public LocalDate getDataEntrada(){ 
        return dataEntrada; 
    } 
    public void setDataEntrada(LocalDate v){ 
        dataEntrada=v; 
    }
    public LocalDate getDataSaida(){ 
        return dataSaida; 
    } 
    public void setDataSaida(LocalDate v){ 
        dataSaida=v; 
    }
    public String getMotivo(){ 
        return motivo; 
    } 
    public void setMotivo(String v){ 
        motivo=v; 
    }
    public StatusInternacao getStatus(){ 
        return status; 
    } 
    public void setStatus(StatusInternacao v){ 
        status=v; 
    }
    public String getObservacoes(){ 
        return observacoes; 
    } 
    public void setObservacoes(String v){ 
        observacoes=v; 
    }
}