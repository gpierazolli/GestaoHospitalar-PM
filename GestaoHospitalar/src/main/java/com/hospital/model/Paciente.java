package com.hospital.model;

import java.time.LocalDate;

public class Paciente {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String telefone;
    private String email;

    public Paciente() {}

    public Paciente(Long id, String nome, String cpf, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId(){ 
        return id; 
    }
    public void setId(Long id){ 
        this.id = id; 
    }
    public String getNome(){ 
        return nome; 
    }
    public void setNome(String nome){ 
        this.nome = nome; 
    }
    public String getCpf(){ 
        return cpf; 
    }
    public void setCpf(String cpf){ 
        this.cpf = cpf; 
    }
    public LocalDate getNascimento() {
        return nascimento;
    }
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    public String getTelefone(){ 
        return telefone; 
    }
    public void setTelefone(String telefone){ 
        this.telefone = telefone; 
    }
    public String getEmail(){ 
        return email; 
    }
    public void setEmail(String email){ 
        this.email = email; 
    }
}
