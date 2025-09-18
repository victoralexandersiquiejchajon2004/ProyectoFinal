/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.model;

package model;

import java.time.LocalDateTime;

public class Sesion {
    public enum Estado {
        PENDIENTE, CONFIRMADA, CANCELADA, ATENDIDA
    }

    private String id;
    private Cliente cliente;
    private Servicio servicio;
    private LocalDateTime fechaHora;
    private Estado estado;
    private String notas;
    private Factura factura; 
    
    private boolean facturada;

    public boolean isFacturada() { return facturada; }
    public void setFacturada(boolean facturada) { this.facturada = facturada; }

    public Sesion(String id, Cliente cliente, Servicio servicio,
                  LocalDateTime fechaHora, Estado estado, String notas) {
        this.id = id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.notas = notas;
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Servicio getServicio() { return servicio; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Estado getEstado() { return estado; }
    public String getNotas() { return notas; }
    public Factura getFactura() { return factura; }

    public void setEstado(Estado estado) { this.estado = estado; }
    public void setNotas(String notas) { this.notas = notas; }
    public void setFactura(Factura factura) { this.factura = factura; }

    @Override
    public String toString() {
        return String.format("Sesion[%s - %s, %s, Estado: %s, Factura: %s]",
                id, cliente.getNombre(), servicio.getNombre(), estado,
                (factura != null ? factura.getId() : "N/A"));
    }
}
