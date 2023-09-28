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

public class PerformanceTest {
    public static void main(String[] args) {
        popularBD();

        EntityManager manager = JPAUtil.getEntityManager();
        PedidoDAO pedidoDAO = new PedidoDAO(manager);
        Pedido pedido = pedidoDAO.buscarPedidoComCliente(1L);
        manager.close();
        System.out.println(pedido.getCliente().getNome());

    }

    public static void popularBD() {

        Categoria games = new Categoria("Games");

        Produto jogo = new Produto("Mortal Kombat", "Jogo de luta com bastante gore",
                new BigDecimal("279.00"), games);

        Produto jogo2 = new Produto("Hollow Knight", "Jogo dark, lindo e reflexivo",
                new BigDecimal("80.00"), games);

        Cliente cliente = new Cliente("Vinícius", "5555555");


        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(manager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(manager);
        ClienteDAO clienteDAO = new ClienteDAO(manager);

        manager.getTransaction().begin();

        Produto produto = produtoDAO.findById(1L);
        Produto produto2 = produtoDAO.findById(2L);

        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);

        pedido.adicionarItem(new ItemPedido(7, produto, pedido));
        pedido.adicionarItem(new ItemPedido(2, produto2, pedido));

        PedidoDAO pedidoDAO = new PedidoDAO(manager);
        pedidoDAO.cadastrar(pedido);
        pedidoDAO.cadastrar(pedido2);

        categoriaDAO.cadastrar(games);
        produtoDAO.cadastrar(jogo);
        produtoDAO.cadastrar(jogo2);
        clienteDAO.cadastrar(cliente);

        manager.getTransaction().commit();

        manager.merge(games);

        manager.remove(jogo);

        manager.close();
    }
}
