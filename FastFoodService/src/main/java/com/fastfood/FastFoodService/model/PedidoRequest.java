package com.fastfood.FastFoodService.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PedidoRequest {

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    private String nombreCliente;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @Min(value = 1, message = "El monto debe ser mayor a 0")
    private double monto;

    // Constructor vacío (necesario para Spring)
    public PedidoRequest() {
    }

    // Constructor con parámetros
    public PedidoRequest(String nombreCliente, String descripcion, double monto) {
        this.nombreCliente = nombreCliente;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    // Getters
    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    // Setters (para que Spring pueda asignar valores del JSON)
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}