/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


package service;

import model.Cita;
import model.Cliente;
import model.EstadoCita;
import model.Servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CitaService {
    private List<Cita> citas = new ArrayList<>();
    private int secuenciaId = 1;

    // Crear cita (ya implementado antes)
    public Cita agendarCita(Cliente cliente, Servicio servicio, LocalDateTime fechaHora, String notas, boolean esRecepcionista) {
        if (cliente == null || !cliente.isActivo()) {
            throw new IllegalArgumentException("Cliente inválido o inactivo.");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("Servicio inválido.");
        }
        if (fechaHora.isBefore(LocalDateTime.now().plusHours(2))) {
            throw new IllegalArgumentException("La cita debe agendarse con al menos 2 horas de antelación.");
        }
        if (!verificarDisponibilidad(servicio, fechaHora)) {
            throw new IllegalArgumentException("El horario no está disponible o se alcanzó el cupo máximo.");
        }
        if (contarPendientesCliente(cliente) >= 3) {
            throw new IllegalArgumentException("El cliente ya tiene 3 citas pendientes.");
        }

        EstadoCita estado = esRecepcionista ? EstadoCita.CONFIRMADA : EstadoCita.PENDIENTE;
        Cita nueva = new Cita(secuenciaId++, cliente, servicio, fechaHora, notas, estado);
        citas.add(nueva);
        return nueva;
    }

    // Confirmar cita pendiente
    public void confirmarCita(int idCita) {
        Cita cita = buscarCitaPorId(idCita).orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));
        if (cita.getEstado() != EstadoCita.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden confirmar citas en estado PENDIENTE.");
        }
        if (!cita.getCliente().isActivo() || !cita.getServicio().isActivo()) {
            throw new IllegalArgumentException("Cliente o servicio inválido/inactivo.");
        }
        if (cita.getFechaHora().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new IllegalStateException("La cita no cumple con la antelación mínima de 2 horas.");
        }
        if (!verificarDisponibilidad(cita.getServicio(), cita.getFechaHora())) {
            throw new IllegalStateException("No se puede confirmar: cupo completo o solapamiento.");
        }

        cita.setEstado(EstadoCita.CONFIRMADA);
        cita.setFechaAccion(LocalDateTime.now());

        // TODO: Notificación al cliente
    }

    // Rechazar cita pendiente
    public void rechazarCita(int idCita, String motivo) {
        Cita cita = buscarCitaPorId(idCita).orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));
        if (cita.getEstado() != EstadoCita.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden rechazar citas en estado PENDIENTE.");
        }
        cita.setEstado(EstadoCita.RECHAZADA);
        cita.setMotivoRechazo(motivo);
        cita.setFechaAccion(LocalDateTime.now());

        // TODO: Notificación al cliente
    }

    // Verificar disponibilidad en horario
    public boolean verificarDisponibilidad(Servicio servicio, LocalDateTime fechaHora) {
        List<Cita> citasEnEsaHora = citas.stream()
                .filter(c -> c.getServicio().getId() == servicio.getId()
                        && c.getFechaHora().equals(fechaHora)
                        && (c.getEstado() == EstadoCita.PENDIENTE || c.getEstado() == EstadoCita.CONFIRMADA))
                .collect(Collectors.toList());
        return citasEnEsaHora.size() < servicio.getMaxConcurrentes();
    }

    public int contarPendientesCliente(Cliente cliente) {
        return (int) citas.stream()
                .filter(c -> c.getCliente().getId() == cliente.getId() && c.getEstado() == EstadoCita.PENDIENTE)
                .count();
    }

    public Optional<Cita> buscarCitaPorId(int id) {
        return citas.stream().filter(c -> c.getId() == id).findFirst();
    }

    public List<Cita> listarPendientes() {
        return citas.stream().filter(c -> c.getEstado() == EstadoCita.PENDIENTE).collect(Collectors.toList());
    }
}
