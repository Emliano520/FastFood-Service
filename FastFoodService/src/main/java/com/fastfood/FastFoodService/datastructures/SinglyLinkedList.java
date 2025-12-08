package com.fastfood.FastFoodService.datastructures;

import com.fastfood.FastFoodService.model.Pedido;

public class SinglyLinkedList {

    // Clase interna para los nodos (simple: id para búsqueda rápida, data para el pedido, next para el siguiente nodo)
    private static class Node {
        int id;  // Para buscar rápido
        Pedido data;  // El pedido
        Node next;  // Referencia al siguiente nodo

        Node(int id, Pedido data) {
            this.id = id;
            this.data = data;
            this.next = null;
        }
    }

    private Node head;  // El primer nodo de la lista
    private int size;  // Número de elementos

    // Constructor
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Agregar al final
    public void add(Pedido pedido) {
        Node newNode = new Node(pedido.getId(), pedido);
        if (head == null) {
            head = newNode;  // Si la lista está vacía, este es el primero
        } else {
            Node current = head;
            while (current.next != null) {  // Busca el último nodo
                current = current.next;
            }
            current.next = newNode;  // Agrega al final
        }
        size++;
    }

    // Buscar por id
    public Pedido findById(int id) {
        Node current = head;
        while (current != null) {
            if (current.id == id) {
                return current.data;  // Encontrado
            }
            current = current.next;
        }
        return null;  // No encontrado
    }

    // Eliminar por id
    public boolean removeById(int id) {
        if (head == null) return false;
        if (head.id == id) {
            head = head.next;  // Elimina el primero
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.id == id) {
                current.next = current.next.next;  // Salta el nodo a eliminar
                size--;
                return true;
            }
            current = current.next;
        }
        return false;  // No encontrado
    }

    // Número de elementos
    public int size() {
        return size;
    }

    // Para recorrer y mostrar (como pide el profesor)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data.toString()).append("\n");
            current = current.next;
        }
        return sb.toString();
    }

    // Metodo main para probar (quitar después)
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        Pedido p1 = new Pedido(1, "Juan", "Hamburguesa", 10.0, Pedido.REGISTRADO);
        Pedido p2 = new Pedido(2, "Ana", "Pizza", 15.0, Pedido.REGISTRADO);
        list.add(p1);
        list.add(p2);
        System.out.println("Lista: " + list);
        System.out.println("Buscar id 1: " + list.findById(1));
        list.removeById(1);
        System.out.println("Después de eliminar: " + list);
    }
}