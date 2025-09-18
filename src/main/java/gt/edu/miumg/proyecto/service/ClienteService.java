/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import gt.edu.miumg.proyecto.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    /**
     * Registrar un nuevo cliente.
     */
    public boolean registrarCliente(Cliente nuevoCliente) {
        if (existeCorreo(nuevoCliente.getCorreo())) {
            System.out.println("El correo ya está registrado.");
            return false;
        }
        if (existeDPI(nuevoCliente.getDpi())) {
            System.out.println("El DPI ya está registrado.");
            return false;
        }

        clientes.add(nuevoCliente);
        System.out.println("Cliente registrado exitosamente.");
        return true;
    }

    /**
     * Actualizar datos de un cliente existente.
     */
    public void actualizarCliente(Cliente cliente) {
        // Como estamos usando una lista en memoria, el objeto ya está actualizado.
        // Pendiente: Persistencia real en base de datos.
        System.out.println("Cliente actualizado en el servicio.");
    }

    /**
     * Buscar cliente por correo.
     */
    public Cliente buscarPorCorreo(String correo) {
        return clientes.stream()
                .filter(c -> c.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);
    }

    /**
     * Buscar cliente por DPI.
     */
    public Cliente buscarPorDPI(String dpi) {
        return clientes.stream()
                .filter(c -> c.getDpi().equals(dpi))
                .findFirst()
                .orElse(null);
    }

    /**
     * Verifica si el correo ya existe en otro cliente distinto.
     */
    public boolean existeCorreoEnOtroCliente(Cliente clienteActual, String correo) {
        return clientes.stream()
                .anyMatch(c -> !c.equals(clienteActual) && c.getCorreo().equalsIgnoreCase(correo));
    }

    /**
     * Verifica si el DPI ya existe en otro cliente distinto.
     */
    public boolean existeDPIEnOtroCliente(Cliente clienteActual, String dpi) {
        return clientes.stream()
                .anyMatch(c -> !c.equals(clienteActual) && c.getDpi().equals(dpi));
    }

    /**
     * Verifica si el correo ya existe en la lista.
     */
    public boolean existeCorreo(String correo) {
        return clientes.stream()
                .anyMatch(c -> c.getCorreo().equalsIgnoreCase(correo));
    }

    /**
     * Verifica si el DPI ya existe en la lista.
     */
    public boolean existeDPI(String dpi) {
        return clientes.stream()
                .anyMatch(c -> c.getDpi().equals(dpi));
    }

    /**
     * Listar todos los clientes.
     */
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }
    /**
 * Desactiva un cliente (borrado lógico).
 */
public boolean desactivarCliente(Cliente cliente) {
    if (cliente != null && cliente.isActivo()) {
        cliente.setActivo(false);
        System.out.println("Cliente desactivado exitosamente.");
        return true;
    }
    System.out.println("Cliente ya estaba inactivo o no existe.");
    return false;
}

/**
 * Elimina un cliente si no tiene citas futuras (borrado físico o lógico según política).
 */
public boolean eliminarCliente(Cliente cliente) {
    if (cliente == null) {
        System.out.println("Cliente no encontrado.");
        return false;
    }

    // Pendiente: verificar que no tenga citas futuras activas
    boolean tieneCitasFuturas = false; // ← Aquí se integrará con CitaService

    if (tieneCitasFuturas) {
        System.out.println("No se puede eliminar cliente: existen citas futuras asociadas.");
        return false;
    }

    clientes.remove(cliente); // eliminación física
    System.out.println("Cliente eliminado exitosamente.");
    return true;
}
/**
 * Consultar perfil personal de un cliente por su correo (simulando sesión/token).
 * Retorna null si no se encuentra.
 */
public Cliente consultarPerfil(String correoCliente) {
    Cliente cliente = buscarPorCorreo(correoCliente);
    if (cliente != null && cliente.isActivo()) {
        // Retornamos una copia o el objeto directamente en modo lectura
        return cliente;
    }
    System.out.println("Perfil no encontrado o cliente inactivo.");
    return null;
}


}
