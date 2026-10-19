package cl.duoc.pedidos360.backend.publisher;

import cl.duoc.pedidos360.backend.model.Pedido;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PedidoSnsPublisher {

    @Autowired
    private SnsTemplate snsTemplate;

    // Lee el ARN de tu topic desde el application.yml
    @Value("${aws.sns.topic.pedidos}")
    private String topicArn;

    public void publicarPedido(Pedido pedido) {
        // El header "monto" es obligatorio para que el filtro de SQS en AWS funcione
        Map<String, Object> headers = Map.of("monto", pedido.getTotal());
        
        // En la versión moderna, se utiliza sendNotification en lugar de convertAndSend
        snsTemplate.convertAndSend(topicArn, pedido, headers);
    }
}