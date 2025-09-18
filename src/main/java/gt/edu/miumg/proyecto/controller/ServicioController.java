/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.controller;

package controller;

import exception.ServicioException;
import model.Servicio;
import repository.ServicioRepository;
import service.ServicioService;

import java.util.List;

public class ServicioController {
    private final ServicioService service;

    public ServicioController() {
        this.service = new ServicioService(new ServicioRepository());
    }

    public void crearServicioDemo() {
        try {
            Servicio servicio = new Servicio("S001", "Corte de Cabello",
                    "Corte clásico para hombres", 50.0, 30, 2);
            service.crearServicio(servicio);
            System.out.println("✅ Servicio creado: " + servicio);
        } catch (ServicioException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void listarServiciosDemo() {
        List<Servicio> servicios = service.listarServicios();
        System.out.println("📋 Lista de servicios:");
        servicios.forEach(System.out::println);
    }

    public void eliminarServicioDemo(String codigo) {
        try {
            service.eliminarServicio(codigo);
            System.out.println("✅ Servicio eliminado (inactivo): " + codigo);
        } catch (ServicioException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
