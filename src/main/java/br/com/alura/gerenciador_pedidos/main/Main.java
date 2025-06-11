package br.com.alura.gerenciador_pedidos.main;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRespository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRespository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRespository;

import java.time.LocalDate;

public class Main {
    private ProdutoRespository produtoRepository;
    private CategoriaRespository categoriaRepository;
    private PedidoRespository pedidoRepository;

    public Main(ProdutoRespository produtoRepository, CategoriaRespository categoriaRepository, PedidoRespository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public void salvarDados() {
        Produto produto = new Produto(3500.0, "Notebook");
        Categoria categoria = new Categoria("Eletrônicos");
        Pedido pedido = new Pedido(LocalDate.now());

        produtoRepository.save(produto);
        categoriaRepository.save(categoria);
        pedidoRepository.save(pedido);
    }


//    public void salvarProdutoNoBancoDeDados() {
//        Produto produto = new Produto(1L, 3500.0, "Notebook");
//        produtoRepository.save(produto);
//    }
//
//    public void salvarCategoriaNoBancoDeDados() {
//        Categoria categoria = new Categoria(1L, "Eletrônicos");
//        categoriaRepository.save(categoria);
//    }
//
//    public void salvarPedidoNoBancoDeDados() {
//        Pedido pedido = new Pedido(1L, LocalDate.now());
//        pedidoRepository.save(pedido);
//    }

}
