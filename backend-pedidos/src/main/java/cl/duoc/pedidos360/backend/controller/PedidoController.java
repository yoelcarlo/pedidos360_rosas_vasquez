package cl.duoc.pedidos360.backend.controller;

import cl.duoc.pedidos360.backend.model.Pedido;
import cl.duoc.pedidos360.backend.repository.PedidoRepository;
import cl.duoc.pedidos360.backend.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}) // Permite ambos puertos de Vite
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // 1. Inyectamos tu servicio que contiene la lógica de AWS SNS
    @Autowired
    private PedidoService pedidoService;

    // CREATE: Cualquier usuario autenticado puede guardar un pedido
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        // 2. Ahora usamos el servicio en lugar del repositorio directo
        return pedidoService.guardarPedido(pedido);
    }

    // READ: Cualquier usuario autenticado puede ver los pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // DELETE: SOLO los administradores pueden eliminar pedidos
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
    }
}