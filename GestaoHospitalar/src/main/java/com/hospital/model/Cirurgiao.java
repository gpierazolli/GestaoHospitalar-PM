package com.hospital.model;
import java.util.ArrayList;
import java.util.List;

public class Cirurgiao extends Profissional {
    public Cirurgiao() {}
    private String areaCirurgica;
    private List<ProcedimentoCirurgico> procedimentos = new ArrayList<>();

    public List<ProcedimentoCirurgico> getProcedimentos() { 
        return procedimentos; 
    }
    public void setProcedimentos(List<ProcedimentoCirurgico> procedimentos) {
        this.procedimentos = procedimentos == null ? new ArrayList<>() : procedimentos;
    }
    public void registrarProcedimento(ProcedimentoCirurgico procedimento) {
        if (procedimento == null) throw new IllegalArgumentException("Procedimento obrigatório.");
        procedimento.setCirurgiao(this);
        procedimentos.add(procedimento);
    }
    public String getAreaCirurgica(){ 
        return areaCirurgica; 
    }
    public void setAreaCirurgica(String areaCirurgica){ 
        this.areaCirurgica = areaCirurgica; 
    }
}