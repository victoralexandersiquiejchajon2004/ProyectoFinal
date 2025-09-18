/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.model;
package gt.edu.miumg.proyecto.model;

import java.time.LocalDateTime;

public class Factura {
    private String numeroFactura;
    private Cliente cliente;
    private String servicio; // Nombre o descripción básica del servicio
    private double montoTotal;
    private double impuestos;
    private Estado estado;
    private LocalDateTime fechaEmision;

    public enum Estado {
        PAGADA,
        PENDIENTE
    }

    // Constructor
    public Factura(String numeroFactura, Cliente cliente, String servicio,
                   double montoTotal, double impuestos, Estado estado, LocalDateTime fechaEmision) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.servicio = servicio;
        this.montoTotal = montoTotal;
        this.impuestos = impuestos;
        this.estado = estado;
        this.fechaEmision = fechaEmision;
    }

    // Getters y Setters
    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }

    public double getImpuestos() { return impuestos; }
    public void setImpuestos(double impuestos) { this.impuestos = impuestos; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
}
