/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;

package service;

import model.Cliente;
import model.Notificacion;
import repository.NotificacionRepository;

import java.time.LocalDateTime;

public class NotificacionService {
    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public void enviarNotificacion(Cliente cliente, String titulo, String mensaje,
                                   Notificacion.Tipo tipo, Long referenciaId) {
        if (cliente == null || !cliente.isActivo()) {
            System.out.println("No se puede enviar notificación: cliente inválido.");
            return;
        }

        Notificacion notificacion = new Notificacion();
        notificacion.setDestinatario(cliente);
        notificacion.setTitulo(titulo);
        notificacion.setMensaje(mensaje);
        notificacion.setTipo(tipo);
        notificacion.setFechaEnvio(LocalDateTime.now());
        notificacion.setReferenciaId(referenciaId);
        notificacion.setEntregada(true); // simulado como entregada

        repository.guardar(notificacion);

        // Aquí iría la integración real con FCM, OneSignal, etc.
        System.out.println("Notificación enviada a " + cliente.getNombre() + ": " + titulo);
    }
}
