package com.hospital.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MedicoClinico.class, name = "clinico"),
    @JsonSubTypes.Type(value = MedicoEspecialista.class, name = "especialista"),
    @JsonSubTypes.Type(value = Cirurgiao.class, name = "cirurgiao")
})
public abstract class Profissional {
    protected Long id;
    protected String nome;
    protected String registroProfissional;
    protected String telefone;
    protected String email;

    public Profissional(){}

    public Profissional(Long id, String nome, String registroProfissional,
                       String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.registroProfissional = registroProfissional;
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
    public String getRegistroProfissional(){ 
        return registroProfissional; 
    }
    public void setRegistroProfissional(String registroProfissional){ 
        this.registroProfissional = registroProfissional; 
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