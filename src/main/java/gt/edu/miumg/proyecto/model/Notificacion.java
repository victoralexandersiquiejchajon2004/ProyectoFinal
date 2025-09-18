/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.model;

package model;

import java.time.LocalDateTime;

public class Notificacion {
    private Long id;
    private Cliente destinatario;
    private String titulo;
    private String mensaje;
    private Tipo tipo;
    private LocalDateTime fechaEnvio;
    private boolean entregada;
    private Long referenciaId; // id de cita o factura

    public enum Tipo {
        CONFIRMACION_CITA,
        RECHAZO_CITA,
        RECORDATORIO_CITA,
        CANCELACION_CITA,
        FACTURA_EMITIDA
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getDestinatario() { return destinatario; }
    public void setDestinatario(Cliente destinatario) { this.destinatario = destinatario; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public boolean isEntregada() { return entregada; }
    public void setEntregada(boolean entregada) { this.entregada = entregada; }

    public Long getReferenciaId() { return referenciaId; }
    public void setReferenciaId(Long referenciaId) { this.referenciaId = referenciaId; }
}
