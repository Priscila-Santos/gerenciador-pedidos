package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRespository extends JpaRepository<Produto, Long> {
    // 1 - Retorne todos os produtos com o nome exato fornecido.
    List<Produto> findByNome(String nome);

    // 2 - Retorne todos os produtos associados a uma categoria específica.
    List<Produto> findByCategoria(Categoria categoria);

    // 3 - Retorne produtos com preço maior que o valor fornecido.
    List<Produto> findByPrecoGreaterThan(Double preco);

   // 4 - Retorne produtos com preço menor que o valor fornecido.
    List<Produto> findByPrecoIsLessThan(Double preco);

    //5 - Retorne produtos cujo nome contenha o termo especificado.
    List<Produto> findByNomeContaining(String nome);

    // 8 - Retorne produtos de uma categoria ordenados pelo preço de forma crescente.
    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    // 9 - Retorne produtos de uma categoria ordenados pelo preço de forma decrescente.
    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);

    // 10 - Retorne a contagem de produtos em uma categoria específica.
    long countByCategoriaNome(String categoriaNome);

    // 11 - Retorne a contagem de produtos cujo preço seja maior que o valor fornecido.
    long countByPrecoGreaterThan(Double preco);

    // 12 - Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
    List<Produto> findByPrecoLessThanOrNomeContainingIgnoreCase(Double preco, String nome);

    // 16 - Retorne os três produtos mais caros.
    List<Produto> findTop3ByPrecoDesc();

    // 17 - Retorne os cinco produtos mais baratos de uma categoria.
    List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    /////////////// QUERYS //////////////////////////

    // 1 - Crie uma consulta que retorne os produtos com preço maior que um valor
    @Query("SELECT p FROM Produto p WHERE p.preco > :valor")
    List<Produto> precoMaiorQueValorEstipulado(Double valor);
    // 2 - Crie uma consulta que retorne os produtos ordenados pelo preço crescente.
    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC ")
    List<Produto> precoMOrdenadoAsc();

    // 3 - Crie uma consulta que retorne os produtos ordenados pelo preço decrescente.
    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC ")
    List<Produto> precoMOrdenadoDesc();

    // 4 - Crie uma consulta que retorne os produtos que comecem com uma letra específica.
    @Query("SELECT p FROM Produto p WHERE p.nome ILIKE :letra%")
    List<Produto> produtoComecaComDeterminadaLetra(char letra);

    // 6 - Crie uma consulta que retorne a média de preços dos produtos.
    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double calcularMediaPrecoProdutos();

    // 7 - Crie uma consulta que retorne o preço máximo de um produto em uma categoria
    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :categoria")
    Double valorMaximoProdutoPorCategoria(@Param("categoria") String categoria);

    // 8 - Crie uma consulta para contar o número de produtos por categoria.
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome")
    List<Object[]> contarProdutosPorCategoria();

    // 9 - Crie uma consulta para filtrar categorias com mais de 10 produtos.
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > 10 ")
    List<Object[]> categoriasComMaisDe();

    // 10 - Crie uma consulta para retornar os produtos filtrados por nome ou por categoria.
    @Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) OR (:categoria IS NULL OR p.categoria.nome = :categoria)")
    List<Produto> buscarProdutosFiltrados(@Param("nome") String nome, @Param("categoria") String categoria);

    // 11 - Crie uma consulta nativa para buscar os cinco produtos mais caros
    @Query(value = "SELECT * FROM produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
    List<Produto> buscarTop5ProdutosMaisCaros();
}
