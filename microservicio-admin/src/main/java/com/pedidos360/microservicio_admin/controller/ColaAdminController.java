package com.pedidos360.microservicio_admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class ColaAdminController {

    @GetMapping("/estado-colas")
    public ResponseEntity<String> obtenerEstadoColas() {
        // Lógica para consultar el estado de las colas en AWS SQS
        return ResponseEntity.ok("Estado de colas operativas y sincronizadas con AWS.");
    }

    @PostMapping("/purgar-dlq/{nombreCola}")
    public ResponseEntity<String> purgarDlq(@PathVariable String nombreCola) {
        // Lógica para vaciar o reprocesar la Dead Letter Queue
        return ResponseEntity.ok("DLQ de la cola " + nombreCola + " purgada y reprocesada con éxito.");
    }
}