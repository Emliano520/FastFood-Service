package com.fastfood.FastFoodService.service;

import com.fastfood.FastFoodService.datastructures.Queue;
import com.fastfood.FastFoodService.datastructures.SinglyLinkedList;
import com.fastfood.FastFoodService.datastructures.Stack;
import com.fastfood.FastFoodService.model.HistorialOperacion;
import com.fastfood.FastFoodService.model.Pedido;
import com.fastfood.FastFoodService.model.PedidoRequest;
import com.fastfood.FastFoodService.model.PedidoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    // Tus estructuras: lista para todos, cola para pendientes, pila para historial
    private final SinglyLinkedList pedidosLista = new SinglyLinkedList();
    private final Queue pedidosCola = new Queue();
    private final Stack historial = new Stack();
    private static int nextId = 1; // Para IDs únicos

    // Registrar un pedido nuevo
    public PedidoResponse registrarPedido(PedidoRequest request) {
        // Crea el pedido con ID nuevo y estado "REGISTRADO"
        Pedido pedido = new Pedido(nextId++, request.getNombreCliente(), request.getDescripcion(), request.getMonto(), Pedido.REGISTRADO);
        // Agrega a la lista y cola
        pedidosLista.add(pedido);
        pedidosCola.enqueue(pedido);
        // Guarda en historial
        historial.push(new HistorialOperacion("CREAR", null, pedido));
        // Devuelve respuesta
        return new PedidoResponse(pedido.getId(), pedido.getNombreCliente(), pedido.getDescripcion(), pedido.getMonto(), pedido.getEstado());
    }

    // Listar todos (simplificado: asume que agregas getAll en SinglyLinkedList)
    public List<PedidoResponse> listarPedidos() {
        // Necesitas agregar en SinglyLinkedList: public List<Pedido> getAll() { List<Pedido> list = new ArrayList<>(); Node current = head; while(current != null) { list.add(current.data); current = current.next; } return list; }
        List<Pedido> pedidos = pedidosLista.getAll();
        List<PedidoResponse> responses = new ArrayList<>();
        for (Pedido p : pedidos) {
            responses.add(new PedidoResponse(p.getId(), p.getNombreCliente(), p.getDescripcion(), p.getMonto(), p.getEstado()));
        }
        return responses;
    }

    // Consultar por ID
    public PedidoResponse consultarPedido(int id) {
        Pedido pedido = pedidosLista.findById(id);
        if (pedido == null) return null; // No encontrado
        return new PedidoResponse(pedido.getId(), pedido.getNombreCliente(), pedido.getDescripcion(), pedido.getMonto(), pedido.getEstado());
    }

    // Cancelar (simplificado)
    public PedidoResponse cancelarPedido(int id) {
        Pedido pedido = pedidosLista.findById(id);
        if (pedido == null) return null;

        // Copia del estado ANTES
        Pedido copiaAntes = new Pedido(
                pedido.getId(),
                pedido.getNombreCliente(),
                pedido.getDescripcion(),
                pedido.getMonto(),
                pedido.getEstado()
        );

        // Cambiar estado en la lista
        pedido.setEstado(Pedido.CANCELADO);

        // Sacar de la cola
        pedidosCola.removeById(id);

        // Guardar operación en la pila
        historial.push(new HistorialOperacion("CANCELAR", copiaAntes, pedido));

        // Respuesta
        return new PedidoResponse(
                pedido.getId(),
                pedido.getNombreCliente(),
                pedido.getDescripcion(),
                pedido.getMonto(),
                pedido.getEstado()
        );
    }


    // Despachar (sacar de cola y cambiar estado)
    public PedidoResponse despacharPedido() {
        Pedido pedido = pedidosCola.dequeue();
        if (pedido == null) return null;
        Pedido copiaAntes = new Pedido(pedido.getId(), pedido.getNombreCliente(), pedido.getDescripcion(), pedido.getMonto(), pedido.getEstado());
        pedido.setEstado(Pedido.DESPACHADO);
        historial.push(new HistorialOperacion("DESPACHAR", copiaAntes, pedido));
        return new PedidoResponse(pedido.getId(), pedido.getNombreCliente(), pedido.getDescripcion(), pedido.getMonto(), pedido.getEstado());
    }

    // Estadísticas (cuenta simple)
    public Map<String, Object> getEstadisticas() {
        List<Pedido> pedidos = pedidosLista.getAll();
        int total = pedidos.size();
        double totalMonto = 0;
        int registrados = 0, despachados = 0, cancelados = 0;
        for (Pedido p : pedidos) {
            totalMonto += p.getMonto();
            if (Pedido.REGISTRADO.equals(p.getEstado())) registrados++;
            else if (Pedido.DESPACHADO.equals(p.getEstado())) despachados++;
            else if (Pedido.CANCELADO.equals(p.getEstado())) cancelados++;
        }
        return Map.of("totalPedidos", total, "totalMonto", totalMonto, "totalRegistrados", registrados, "totalDespachados", despachados, "totalCancelados", cancelados);
    }

    // Monto total recursivo (agrega en SinglyLinkedList: public double sumRecursivo(Node node) { if (node == null) return 0; return node.data.getMonto() + sumRecursivo(node.next); } y llama con head)
    public double calcularMontoTotalRecursivo() {
        return pedidosLista.sumRecursivo(pedidosLista.getHead());
    }

    // Rollback (deshacer)
    public PedidoResponse rollback() {
        HistorialOperacion op = historial.pop();
        if (op == null) return null;
        if ("CREAR".equals(op.getTipoOperacion())) {
            pedidosLista.removeById(op.getPedidoDespues().getId());
            // Remover de cola
        } else if ("CANCELAR".equals(op.getTipoOperacion())) {
            Pedido p = pedidosLista.findById(op.getPedidoDespues().getId());
            if (p != null) p.setEstado(op.getPedidoAntes().getEstado());
        } else if ("DESPACHAR".equals(op.getTipoOperacion())) {
            Pedido p = pedidosLista.findById(op.getPedidoDespues().getId());
            if (p != null) {
                p.setEstado(op.getPedidoAntes().getEstado());
                pedidosCola.enqueue(p);
            }
        }
        return new PedidoResponse(op.getPedidoDespues().getId(), op.getPedidoDespues().getNombreCliente(), op.getPedidoDespues().getDescripcion(), op.getPedidoDespues().getMonto(), op.getPedidoDespues().getEstado());
    }
}