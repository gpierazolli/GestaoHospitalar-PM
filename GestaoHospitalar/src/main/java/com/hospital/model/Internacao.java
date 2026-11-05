package com.hospital.model;

import java.time.LocalDate;

public class Internacao {
    private Long id;
    private Paciente paciente;
    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String motivo;

    public Long getId(){ 
        return id; 
    }
    public void setId(Long id){ 
        this.id = id; 
    }
    public Paciente getPaciente(){ 
        return paciente; 
    }
    public void setPaciente(Paciente paciente){ 
        this.paciente = paciente; 
    }
    public Quarto getQuarto(){ 
        return quarto; 
    }
    public void setQuarto(Quarto quarto){ 
        this.quarto = quarto; 
    }
    public LocalDate getDataEntrada(){ 
        return dataEntrada; 
    }
    public void setDataEntrada(LocalDate dataEntrada){ 
        this.dataEntrada = dataEntrada; 
    }
    public LocalDate getDataSaida(){ 
        return dataSaida; 
    }
    public void setDataSaida(LocalDate dataSaida){ 
        this.dataSaida = dataSaida; 
    }
    public String getMotivo(){ 
        return motivo; 
    }
    public void setMotivo(String motivo){ 
        this.motivo = motivo; 
    }
}