package com.fastfood.FastFoodService.model;

public class PedidoResponse {

    private int id;
    private String nombreCliente;
    private String descripcion;
    private double monto;
    private String estado;

    // Constructor vacío
    public PedidoResponse() {
    }

    // Constructor con parámetros
    public PedidoResponse(int id, String nombreCliente, String descripcion, double monto, String estado) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
    }

    // Getters (solo para devolver datos, no setters)
    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public String getEstado() {
        return estado;
    }
}

