package com.hospital.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public abstract class CrudService<T> {
    protected final Map<Long,T> dados = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);
    protected abstract Long getId(T item);
    protected abstract void setId(T item, Long id);
    protected void validar(T item) {
        if (item == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Corpo da requisição obrigatório.");
    }
    public Collection<T> listar() { 
        return dados.values(); 
    }
    public T buscar(Long id) {
        T item = dados.get(id);
        if (item == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado: " + id);
        return item;
    }
    public T cadastrar(T item) {
        validar(item);
        setId(item, sequencia.getAndIncrement());
        dados.put(getId(item), item);
        return item;
    }
    public T atualizar(Long id, T item) {
        buscar(id);
        setId(item, id);
        validar(item);
        dados.put(id, item);
        return item;
    }
    public void excluir(Long id) { 
        buscar(id); 
        dados.remove(id); 
    }
}
