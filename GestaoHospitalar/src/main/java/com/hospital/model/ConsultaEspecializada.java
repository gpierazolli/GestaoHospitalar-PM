package com.hospital.model;

public class ConsultaEspecializada extends Consulta {
    private String especialidade;

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    @Override
    public boolean validarAtendimento() {
        return paciente != null
                && profissional instanceof MedicoEspecialista
                && especialidade != null
                && especialidade.equalsIgnoreCase(((MedicoEspecialista) profissional).getEspecialidade());
    }
}