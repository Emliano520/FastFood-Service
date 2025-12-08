package com.fastfood.FastFoodService.model;

public class Pedido {

    // Constantes para los estados del pedido
    public static final String REGISTRADO = "REGISTRADO";
    public static final String EN_PREPARACION = "EN_PREPARACION";
    public static final String DESPACHADO = "DESPACHADO";
    public static final String CANCELADO = "CANCELADO";

    private int id;
    private String nombreCliente;
    private String descripcion;
    private double monto;
    private String estado;

    public Pedido() {
    }

    public Pedido(int id, String nombreCliente, String descripcion, double monto, String estado) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                ", estado='" + estado + '\'' +
                '}';
    }
}

