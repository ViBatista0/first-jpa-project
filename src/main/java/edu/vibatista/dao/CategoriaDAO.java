package edu.vibatista.dao;

import edu.vibatista.model.Categoria;
import jakarta.persistence.EntityManager;

public class CategoriaDAO {

    private EntityManager manager;

    public CategoriaDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void cadastrar(Categoria categoria) {
        this.manager.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        this.manager.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = manager.merge(categoria);
        manager.remove(categoria);
    }
}
