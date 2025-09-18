/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.controller;

package controller;

import exception.HistorialException;
import model.Cliente;
import model.Factura;
import model.Sesion;
import model.Servicio;
import repository.SesionRepository;
import service.SesionService;

import java.time.LocalDateTime;
import java.util.List;

public class HistorialController {
    private final SesionService service;
    private final SesionRepository repository;

    public HistorialController() {
        this.repository = new SesionRepository();
        this.service = new SesionService(repository);
        cargarDatosDemo(); // datos iniciales
    }

    private void cargarDatosDemo() {
        Cliente cliente = new Cliente("C001", "Juan Pérez");
        Servicio servicio1 = new Servicio("S001", "Corte de Cabello", "Servicio de barbería", 50, 30, 2);
        Servicio servicio2 = new Servicio("S002", "Masaje", "Masaje relajante", 100, 60, 1);

        Sesion s1 = new Sesion("SES001", cliente, servicio1,
                LocalDateTime.now().minusDays(10), Sesion.Estado.ATENDIDA, "Cliente satisfecho");
        s1.setFactura(new Factura("F001", 50.0));

        Sesion s2 = new Sesion("SES002", cliente, servicio2,
                LocalDateTime.now().minusDays(5), Sesion.Estado.CANCELADA, "Cancelación por cliente");

        Sesion s3 = new Sesion("SES003", cliente, servicio1,
                LocalDateTime.now().plusDays(2), Sesion.Estado.CONFIRMADA, "Pendiente de atención");

        repository.save(s1);
        repository.save(s2);
        repository.save(s3);
    }

    public void consultarHistorialDemo(String clienteId, LocalDateTime inicio, LocalDateTime fin, String servicio) {
        try {
            Cliente cliente = new Cliente(clienteId, "Juan Pérez"); // en BD real se buscaría
            List<Sesion> sesiones = service.consultarHistorial(cliente, inicio, fin, servicio);
            System.out.println("📋 Historial de sesiones:");
            sesiones.forEach(System.out::println);
        } catch (HistorialException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
