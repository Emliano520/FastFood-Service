package com.fastfood.FastFoodService.controller;

import com.fastfood.FastFoodService.model.PedidoRequest;
import com.fastfood.FastFoodService.model.PedidoResponse;
import com.fastfood.FastFoodService.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service; // Spring inyecta el service

    // POST /api/pedidos (registrar)
    @PostMapping
    public ResponseEntity<PedidoResponse> registrar(@Valid @RequestBody PedidoRequest request) {
        PedidoResponse response = service.registrarPedido(request);
        return ResponseEntity.status(201).body(response); // 201 = creado
    }

    // GET /api/pedidos (listar)
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listar() {
        List<PedidoResponse> responses = service.listarPedidos();
        return ResponseEntity.ok(responses); // 200 = ok
    }

    // GET /api/pedidos/{id} (consultar)
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> consultar(@PathVariable int id) {
        PedidoResponse response = service.consultarPedido(id);
        if (response == null) return ResponseEntity.notFound().build(); // 404
        return ResponseEntity.ok(response);
    }

    // DELETE /api/pedidos/{id} (cancelar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> cancelar(@PathVariable int id) {
        PedidoResponse response = service.cancelarPedido(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("mensaje", "Pedido cancelado correctamente", "pedido", response));
    }
}
