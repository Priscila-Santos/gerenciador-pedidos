package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Pedido;
import org.hibernate.mapping.Selectable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRespository extends JpaRepository<Pedido, Long> {
    // 6 - Retorne pedidos que ainda não possuem uma data de entrega.
    List<Pedido> findByDataIsNull();

    // 7 - Retorne pedidos com data de entrega preenchida.
    List<Pedido> findByDataIsNotNull();

    //13 - Retorne pedidos feitos após uma data específica.
    List<Pedido> findByDataAfter(LocalDate data);

    // 14 - Retorne pedidos feitos antes de uma data específica.
    List<Pedido> findByDataBefore(LocalDate data);

    // 15 - Retorne pedidos feitos em um intervalo de datas.
    List<Pedido> findByDataBetween(LocalDate data1, LocalDate data2);

    /////////////// QUERYS //////////////////////////

    // 5 - Crie uma consulta que retorne os pedidos feitos entre duas datas.
    @Query("SELECT p FROM Pedido p WHERE p.data BETWEEN :inicio AND :fim")
    List<Pedido> buscarPedidosPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
