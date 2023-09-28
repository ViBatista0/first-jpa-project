package edu.vibatista.test;

import edu.vibatista.dao.CategoriaDAO;
import edu.vibatista.dao.ProdutoDAO;
import edu.vibatista.model.Categoria;
import edu.vibatista.model.Produto;
import edu.vibatista.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CriteriaAPITest {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(manager);
        produtoDAO.findByParameter("Mortal Kombat", null, LocalDate.now());

    }

    public static void cadastrarProduto() {

        Categoria games = new Categoria("Games");

        Produto jogo = new Produto("Mortal Kombat", "Jogo de luta com bastante gore",
                new BigDecimal("279.00"), games);

        Produto jogo2 = new Produto("Hollow Knight", "Jogo dark, lindo e reflexivo",
                new BigDecimal("80.00"), games);

        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(manager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(manager);

        manager.getTransaction().begin();

        categoriaDAO.cadastrar(games);
        produtoDAO.cadastrar(jogo);
        produtoDAO.cadastrar(jogo2);

        manager.getTransaction().commit();

        manager.merge(games);

        manager.remove(jogo);

        manager.close();
    }
}
