package com.hospital.model;

public class ConsultaRotina extends Consulta {
    @Override
    public boolean validarAtendimento() {
        return paciente != null && profissional instanceof MedicoClinico;
    }
}