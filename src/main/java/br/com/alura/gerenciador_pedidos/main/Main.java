package br.com.alura.gerenciador_pedidos.main;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRespository;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRespository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRespository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public class Main {
    private ProdutoRespository produtoRepository;
    private CategoriaRespository categoriaRepository;
    private PedidoRespository pedidoRepository;
    private FornecedorRepository fornecedorRepository;

    public Main(ProdutoRespository produtoRepository, CategoriaRespository categoriaRepository, PedidoRespository pedidoRepository, FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pedidoRepository = pedidoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public void salvarDados() {
        // Criar categorias
        Categoria categoriaEsportiva = new Categoria("Esportivos");
        Categoria categoriaLivros = new Categoria("Livros");

        // Criar fornecedor
        Fornecedor fornecedorEsportivo = new Fornecedor("Fornecedor Esportivos");
        Fornecedor fornecedorLiterario = new Fornecedor("Fornecedor Literario");

        // Criar produtos e associar às categorias e ao fornecedor
        Produto produto1 = new Produto("Skate", 3500.0, categoriaEsportiva);
        produto1.setFornecedor(fornecedorEsportivo);

        Produto produto2 = new Produto("Raquete", 2500.0, categoriaEsportiva);
        produto2.setFornecedor(fornecedorEsportivo);

        Produto produto3 = new Produto("Livro de Java", 100.0, categoriaLivros);
        produto3.setFornecedor(fornecedorLiterario);

        Produto produto4 = new Produto("Livro de Spring Boot", 150.0, categoriaLivros);
        produto4.setFornecedor(fornecedorLiterario);

        // Associar produtos às categorias
        categoriaEsportiva.setProdutos(List.of(produto1, produto2));
        categoriaLivros.setProdutos(List.of(produto3, produto4));

        // Salvar categorias (cascateia produtos)
        categoriaRepository.saveAll(List.of(categoriaEsportiva, categoriaLivros));

        // Criar pedido e associar produtos
//        Pedido pedido = new Pedido(LocalDate.now());
//        pedido.setProdutos(List.of(produto1, produto3)); // Exemplo: comprando Skate e Livro de Java

        // salvar fornecedor
        fornecedorRepository.save(fornecedorEsportivo);
        fornecedorRepository.save(fornecedorLiterario);

        // 2. Salvar as categorias (e os produtos em cascata)
        categoriaRepository.saveAll(List.of(categoriaEsportiva, categoriaLivros));

        // 3. Agora os produtos já estão salvos, então podemos criar o pedido
        Pedido pedido = new Pedido(LocalDate.now());
        pedido.setProdutos(List.of(produto1, produto3)); // produtos já persistidos

        // 4. Salvar o pedido
        pedidoRepository.save(pedido);


        // Exibir dados
        System.out.println("Categorias e seus produtos:");
        categoriaRepository.findAll().forEach(categoria -> {
            System.out.println("Categoria: " + categoria.getNome());
            categoria.getProdutos().forEach(produto ->
                    System.out.println(" - Produto: " + produto.getNome() + " | Fornecedor: " + produto.getFornecedor().getNome())
            );
        });

        System.out.println("\nPedidos e seus produtos:");
        pedidoRepository.findAll().forEach(p -> {
            System.out.println("Pedido ID: " + p.getId() + " | Data: " + p.getData());
            p.getProdutos().forEach(produto ->
                    System.out.println(" - Produto: " + produto.getNome())
            );
        });
    }

}
