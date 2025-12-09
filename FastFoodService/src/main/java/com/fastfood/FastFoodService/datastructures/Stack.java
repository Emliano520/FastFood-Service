package com.fastfood.FastFoodService.datastructures;

import com.fastfood.FastFoodService.model.HistorialOperacion;

public class Stack {

    private HistorialOperacion[] stack; // Arreglo para guardar las notas
    private int top; // Dónde está el "tope" (la última nota)
    private int capacity; // Tamaño máximo del arreglo

    // Constructor: crea la pila vacía
    public Stack() {
        this.capacity = 10; // Empieza con espacio para 10 notas
        this.stack = new HistorialOperacion[capacity];
        this.top = -1; // -1 significa vacía
    }

    // Push: agrega una nota arriba
    public void push(HistorialOperacion op) {
        if (top == capacity - 1) {
            expandCapacity(); // Si está llena, hace más grande
        }
        stack[++top] = op; // Pone la nota en el tope
    }

    // Pop: saca la nota de arriba
    public HistorialOperacion pop() {
        if (isEmpty()) {
            return null; // Si está vacía, no hay nada
        }
        return stack[top--]; // Saca y baja el tope
    }

    // isEmpty: ¿está vacía?
    public boolean isEmpty() {
        return top == -1;
    }

    // expandCapacity: hace el arreglo más grande si se llena
    private void expandCapacity() {
        capacity *= 2; // Duplica el tamaño
        HistorialOperacion[] newStack = new HistorialOperacion[capacity];
        for (int i = 0; i <= top; i++) {
            newStack[i] = stack[i]; // Copia las notas viejas
        }
        stack = newStack;
    }

    // toString: para ver todas las notas (debug)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(stack[i].toString()).append("\n");
        }
        return sb.toString();
    }
}