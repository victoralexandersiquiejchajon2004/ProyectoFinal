/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.controller;

import gt.edu.miumg.proyecto.model.Cliente;
import gt.edu.miumg.proyecto.model.Factura;
import gt.edu.miumg.proyecto.model.Sesion;
import gt.edu.miumg.proyecto.service.FacturaService;
import gt.edu.miumg.proyecto.service.SesionService;

import java.time.LocalDateTime;

public class FacturaController {

    private FacturaService facturaService;
    private SesionService sesionService;

    public FacturaController(FacturaService facturaService, SesionService sesionService) {
        this.facturaService = facturaService;
        this.sesionService = sesionService;
    }

    public void generarFactura(Long sesionId, String observaciones) {
        Sesion sesion = sesionService.obtenerSesionPorId(sesionId);
        if (sesion == null) {
            System.out.println("Sesión no encontrada.");
            return;
        }

        if (!"ATENDIDA".equalsIgnoreCase(sesion.getEstado())) {
            System.out.println("Solo se pueden facturar sesiones atendidas.");
            return;
        }

        if (sesion.isFacturada()) {
            System.out.println("Esta sesión ya fue facturada.");
            return;
        }

        double precio = sesion.getServicio().getPrecio();
        double duracion = sesion.getServicio().getDuracion();

        if (precio <= 0 || duracion <= 0) {
            System.out.println("El precio y duración deben ser válidos.");
            return;
        }

        Factura factura = new Factura();
        factura.setCliente(sesion.getCliente());
        factura.setSesion(sesion);
        factura.setPrecio(precio);
        factura.setDuracion(duracion);
        factura.setFecha(LocalDateTime.now());
        factura.setObservaciones(observaciones);

        facturaService.guardarFactura(factura);

        sesion.setFacturada(true);
        sesionService.actualizarSesion(sesion);

        System.out.println("Factura generada exitosamente con ID: " + factura.getId());
    }
}
