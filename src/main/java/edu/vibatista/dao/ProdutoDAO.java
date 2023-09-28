package edu.vibatista.dao;

import edu.vibatista.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.entityManager.merge(produto);
    }

    public void remover(Produto produto) {
        produto = entityManager.merge(produto);
        this.entityManager.remove(produto);
    }


    public Produto findById(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public List findAll() {
        String jpql = "SELECT p FROM Produto p";
        return entityManager.createQuery(jpql).getResultList();
    }

    public List findByName(String nome) {
//        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
//        return entityManager.createQuery(jpql).setParameter("nome", nome)
//                .getResultList();
        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
        return entityManager.createQuery(jpql).setParameter(1, nome)
                .getResultList();
    }

    public List findByCategoria(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.id.nome = :nome";
        return entityManager.createQuery(jpql).setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal findByPreco(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
        return entityManager.createQuery(jpql, BigDecimal.class).setParameter(1, nome).getSingleResult();
    }

    public List<Produto> findByParameter(String nome, BigDecimal preco, LocalDate dataCadastro) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);
        Predicate and = builder.and();

        if (nome != null && !nome.trim().isEmpty()) {
            and = builder.and(and, builder.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            and = builder.and(and, builder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {
            and = builder.and(and, builder.equal(from.get("localDate"), dataCadastro));
        }

        query.where(and);

        return entityManager.createQuery(query).getResultList();
    }
}
