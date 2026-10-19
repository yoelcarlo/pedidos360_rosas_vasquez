package cl.duoc.pedidos360.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importaciones basadas en las carpetas de tu proyecto
import cl.duoc.pedidos360.backend.model.Pedido;
import cl.duoc.pedidos360.backend.repository.PedidoRepository;
import cl.duoc.pedidos360.backend.publisher.PedidoSnsPublisher;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoSnsPublisher snsPublisher;

    public Pedido guardarPedido(Pedido pedido) {
        // 1. Guardar el pedido en la base de datos MySQL
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // 2. Notificar a AWS SNS
        try {
            snsPublisher.publicarPedido(pedidoGuardado);
            System.out.println("✅ Evento publicado en SNS para el pedido: " + pedidoGuardado.getId());
        } catch (Exception e) {
            System.err.println("❌ ERROR al publicar en SNS: " + e.getMessage());
            e.printStackTrace(); // Esto imprimirá el motivo real del rechazo
        }
        

        // 3. Retornar el pedido
        return pedidoGuardado;
    }

}