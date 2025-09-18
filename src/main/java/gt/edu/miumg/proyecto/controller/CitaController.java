/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.controller;

package controller;

import model.Cita;
import service.CitaService;

import java.util.List;

public class CitaController {
    private CitaService citaService = new CitaService();

    public void mostrarPendientes() {
        List<Cita> pendientes = citaService.listarPendientes();
        if (pendientes.isEmpty()) {
            System.out.println("No hay citas pendientes.");
        } else {
            pendientes.forEach(c -> System.out.println(
                    "Cita #" + c.getId() + " | Cliente: " + c.getCliente().getNombre() +
                            " | Servicio: " + c.getServicio().getNombre() +
                            " | Fecha: " + c.getFechaHora() +
                            " | Estado: " + c.getEstado()
            ));
        }
    }

    public void confirmarCita(int idCita) {
        citaService.confirmarCita(idCita);
        System.out.println("✅ Cita #" + idCita + " confirmada con éxito.");
    }

    public void rechazarCita(int idCita, String motivo) {
        citaService.rechazarCita(idCita, motivo);
        System.out.println("❌ Cita #" + idCita + " rechazada. Motivo: " + motivo);
    }
}
