package cl.duoc.pedidos360.backend.repository;

import cl.duoc.pedidos360.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Al extender de JpaRepository, ya tienes listos métodos como save(), findAll() o findById()
}