package com.hospital.model;

public class MedicoClinico extends Profissional {
    private java.util.List<Encaminhamento> encaminhamentos = new java.util.ArrayList<>();

    public java.util.List<Encaminhamento> getEncaminhamentos() { 
        return encaminhamentos; 
    }
    public void setEncaminhamentos(java.util.List<Encaminhamento> encaminhamentos) {
        this.encaminhamentos = encaminhamentos == null ? new java.util.ArrayList<>() : encaminhamentos;
    }
    public void registrarEncaminhamento(Encaminhamento encaminhamento) {
        if (encaminhamento == null) throw new IllegalArgumentException("Encaminhamento obrigatório.");
        encaminhamento.setMedicoSolicitante(this);
        encaminhamentos.add(encaminhamento);
    }

    public MedicoClinico() {}

}