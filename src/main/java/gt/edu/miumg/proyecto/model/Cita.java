/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.model;

package model;

import java.time.LocalDateTime;

public class Cita {
    private int id;
    private Cliente cliente;
    private Servicio servicio;
    private LocalDateTime fechaHora;
    private String notas;
    private EstadoCita estado;
    private LocalDateTime fechaAccion; // fecha de confirmación/rechazo
    private String motivoRechazo;

    public Cita(int id, Cliente cliente, Servicio servicio, LocalDateTime fechaHora, String notas, EstadoCita estado) {
        this.id = id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.notas = notas;
        this.estado = estado;
        this.fechaAccion = null;
        this.motivoRechazo = null;
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Servicio getServicio() { return servicio; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getNotas() { return notas; }
    public EstadoCita getEstado() { return estado; }
    public LocalDateTime getFechaAccion() { return fechaAccion; }
    public String getMotivoRechazo() { return motivoRechazo; }

    // Setters
    public void setEstado(EstadoCita estado) { this.estado = estado; }
    public void setFechaAccion(LocalDateTime fechaAccion) { this.fechaAccion = fechaAccion; }
    public void setMotivoRechazo(String motivoRechazo) { this.motivoRechazo = motivoRechazo; }
}
