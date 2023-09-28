package edu.vibatista.test;

import edu.vibatista.dao.CategoriaDAO;
import edu.vibatista.dao.ProdutoDAO;
import edu.vibatista.model.Categoria;
import edu.vibatista.model.CategoriaId;
import edu.vibatista.model.Produto;
import edu.vibatista.util.JPAUtil;
import jakarta.persistence.EntityManager;


import java.math.BigDecimal;
import java.util.List;

public class CadastraProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        Long id = 2L;

        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(manager);

        Produto p = produtoDAO.findById(id);
//
//        System.out.println("Produto: " + p.getNome() + "\n");

//        List<Produto> listaProdutos = produtoDAO.findAll();
        // List<Produto> listaProdutos = produtoDAO.findByName(p.getNome());
//       List<Produto> listaProdutos = produtoDAO.findByCategoria("Games");

//        System.out.println("Lista de produtos: " + listaProdutos);
//
//        BigDecimal preco = produtoDAO.findByPreco(p.getNome());
//        System.out.println("Preço do produto: " + preco);

        System.out.println(produtoDAO.findByParameter("Hollow Knight", null, null));
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

        manager.find(Categoria.class, new CategoriaId("Chaveiro", "Acessório"));

        manager.close();
    }
}
