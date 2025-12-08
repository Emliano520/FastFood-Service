package com.fastfood.FastFoodService.datastructures;

import com.fastfood.FastFoodService.model.Pedido;

public class Queue {

    private Pedido[] queue;  // Arreglo para almacenar pedidos
    private int front;  // Índice del frente (para dequeue)
    private int rear;  // Índice del final (para enqueue)
    private int size;  // Número actual de elementos
    private int capacity;  // Tamaño máximo del arreglo

    // Constructor (capacidad por defecto 100)
    public Queue() {
        this.capacity = 100;
        this.queue = new Pedido[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Agregar al final (enqueue)
    public void enqueue(Pedido pedido) {
        if (size == capacity) {
            System.out.println("Cola llena, no se puede agregar");  // Para debug, puedes expandir el arreglo si quieres
            return;
        }
        rear = (rear + 1) % capacity;  // Circular: si llega al final, vuelve al inicio
        queue[rear] = pedido;
        size++;
    }

    // Sacar del frente (dequeue)
    public Pedido dequeue() {
        if (isEmpty()) {
            return null;  // Cola vacía
        }
        Pedido pedido = queue[front];
        front = (front + 1) % capacity;  // Mueve el frente
        size--;
        return pedido;
    }

    // Verificar si está vacía
    public boolean isEmpty() {
        return size == 0;
    }

    // Para debug: mostrar la cola
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            sb.append(queue[index].toString()).append("\n");
        }
        return sb.toString();
    }

    // Metodo main para probar (quita después)
    public static void main(String[] args) {
        Queue queue = new Queue();
        Pedido p1 = new Pedido(1, "Juan", "Hamburguesa", 10.0, Pedido.REGISTRADO);
        Pedido p2 = new Pedido(2, "Ana", "Pizza", 15.0, Pedido.REGISTRADO);
        queue.enqueue(p1);
        queue.enqueue(p2);
        System.out.println("Cola: " + queue);
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Cola después: " + queue);
    }
}
