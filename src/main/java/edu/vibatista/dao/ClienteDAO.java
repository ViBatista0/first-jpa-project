package edu.vibatista.dao;

import edu.vibatista.model.Cliente;
import edu.vibatista.model.Pedido;
import edu.vibatista.model.Produto;
import jakarta.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente cliente) {

        this.entityManager.persist(cliente);

    }

    public Cliente findById(Long id) {
        return entityManager.find(Cliente.class, id);
    }


}
