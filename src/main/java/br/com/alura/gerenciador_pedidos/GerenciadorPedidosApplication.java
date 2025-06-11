package br.com.alura.gerenciador_pedidos;

import br.com.alura.gerenciador_pedidos.main.Main;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRespository;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRespository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	@Autowired
	private ProdutoRespository produtoRespository;

	@Autowired
	private CategoriaRespository categoriaRespository;

	@Autowired
	private PedidoRespository pedidoRespository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(produtoRespository, categoriaRespository, pedidoRespository, fornecedorRepository);
		main.salvarDados();

	}

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}

}
