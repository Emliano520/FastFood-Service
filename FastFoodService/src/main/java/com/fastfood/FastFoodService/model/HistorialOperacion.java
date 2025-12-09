package com.fastfood.FastFoodService.model;

public class HistorialOperacion {

    private String tipoOperacion; // Ejemplo: "CREAR" (cuando registras), "CANCELAR" (cuando cancelas)
    private Pedido pedidoAntes;   // Cómo estaba el pedido antes de la acción (puede ser null si es nuevo)
    private Pedido pedidoDespues; // Cómo quedó después

    // Constructor vacío (Spring lo necesita)
    public HistorialOperacion() {}

    // Constructor con datos
    public HistorialOperacion(String tipoOperacion, Pedido pedidoAntes, Pedido pedidoDespues) {
        this.tipoOperacion = tipoOperacion;
        this.pedidoAntes = pedidoAntes;
        this.pedidoDespues = pedidoDespues;
    }

    // Getters: para leer los valores
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public Pedido getPedidoAntes() {
        return pedidoAntes;
    }

    public Pedido getPedidoDespues() {
        return pedidoDespues;
    }

    // Setters: para cambiar los valores (aunque no se usaran mucho aquí)
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public void setPedidoAntes(Pedido pedidoAntes) {
        this.pedidoAntes = pedidoAntes;
    }

    public void setPedidoDespues(Pedido pedidoDespues) {
        this.pedidoDespues = pedidoDespues;
    }

    // toString: para imprimir la nota
    @Override
    public String toString() {
        return "HistorialOperacion{" +
                "tipoOperacion='" + tipoOperacion + '\'' +
                ", pedidoAntes=" + pedidoAntes +
                ", pedidoDespues=" + pedidoDespues +
                '}';
    }
}