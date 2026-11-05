package com.hospital.model;

import com.hospital.enums.TipoQuarto;

public class Quarto {
    private Long id;
    private String numero;
    private TipoQuarto tipo;
    private int capacidade = 1;
    private boolean disponivel = true;

public Long getId(){ 
    return id; 
} 
public void setId(Long id){ 
    this.id=id; 
}
public String getNumero(){ 
    return numero; 
} 
public void setNumero(String v){ 
    numero=v; 
}
public TipoQuarto getTipo(){ 
    return tipo; 
} 
public void setTipo(TipoQuarto v){ 
    tipo=v; 
}
public int getCapacidade(){ 
    return capacidade; 
} 
public void setCapacidade(int v){ 
    capacidade=v; 
}
public boolean isDisponivel(){ 
    return disponivel; 
} 
public void setDisponivel(boolean v){ 
    disponivel=v; 
}

}