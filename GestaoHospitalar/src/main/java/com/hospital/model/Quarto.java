package com.hospital.model;

public class Quarto {
    private Long id;
    private String numero;
    private String tipo;
    private int capacidade;
    private boolean disponivel = true;

    public Long getId(){ 
        return id; 
    }
    public void setId(Long id){ 
        this.id = id; 
    }
    public String getNumero(){ 
        return numero; 
    }
    public void setNumero(String numero){ 
        this.numero = numero; }
    public String getTipo(){ 
        return tipo; 
    }
    public void setTipo(String tipo){ 
        this.tipo = tipo; 
    }
    public int getCapacidade(){ 
        return capacidade; 
    }
    public void setCapacidade(int capacidade){ 
        this.capacidade = capacidade; 
    }
    public boolean isDisponivel(){ 
        return disponivel; 
    }
    public void setDisponivel(boolean disponivel){ 
        this.disponivel = disponivel; 
    }
}