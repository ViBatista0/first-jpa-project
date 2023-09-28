package edu.vibatista.test;

import edu.vibatista.dao.CategoriaDAO;
import edu.vibatista.dao.ClienteDAO;
import edu.vibatista.dao.PedidoDAO;
import edu.vibatista.dao.ProdutoDAO;
import edu.vibatista.model.*;
import edu.vibatista.util.JPAUtil;
import edu.vibatista.vo.RelatorioDeVendasVo;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastraPedido {
    public static void main(String[] args) {
        popularBD();

        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(manager);
        Produto produto = produtoDAO.findById(1L);
        Produto produto2 = produtoDAO.findById(2L);
        ClienteDAO clienteDAO = new ClienteDAO(manager);

        manager.getTransaction().begin();

        Cliente cliente = clienteDAO.findById(1L);
        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(7, produto, pedido));
        pedido.adicionarItem(new ItemPedido(2, produto2, pedido));

        PedidoDAO pedidoDAO = new PedidoDAO(manager);
        pedidoDAO.cadastrar(pedido);
        pedidoDAO.cadastrar(pedido2);

        manager.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("Valor total do pedido: " + totalVendido);

        List<RelatorioDeVendasVo> relatorioDeVendas = pedidoDAO.relatorioVendas();
        relatorioDeVendas.forEach(System.out::println);
    }

    public static void popularBD() {

        Categoria games = new Categoria("Games");
        Categoria livros = new Categoria("Livros");

        Produto jogo = new Game("Mortal Kombat", "Jogo de luta", new BigDecimal(479.00), games,
                "Netherealms", "18");

        Produto livro = new Livro("Harry Potter", "Livro de maguinho",
                new BigDecimal("80.00"), livros, "Uma vagaba", 270);

        Cliente cliente = new Cliente("Vinícius", "5555555");


        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(manager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(manager);
        ClienteDAO clienteDAO = new ClienteDAO(manager);

        manager.getTransaction().begin();

        categoriaDAO.cadastrar(games);
        produtoDAO.cadastrar(jogo);
        produtoDAO.cadastrar(livro);
        clienteDAO.cadastrar(cliente);


        manager.merge(games);
        manager.merge(livros);

        manager.getTransaction().commit();


        manager.close();
    }
}
