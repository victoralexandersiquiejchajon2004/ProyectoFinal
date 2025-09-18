/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.controller;


import gt.edu.miumg.proyecto.model.Cliente;
import gt.edu.miumg.proyecto.model.Servicio;
import gt.edu.miumg.proyecto.model.Sesion;
import gt.edu.miumg.proyecto.model.Factura;
import gt.edu.miumg.proyecto.service.SesionService;
import gt.edu.miumg.proyecto.service.FacturaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReporteController {

    private SesionService sesionService;
    private FacturaService facturaService;

    public ReporteController(SesionService sesionService, FacturaService facturaService) {
        this.sesionService = sesionService;
        this.facturaService = facturaService;
    }

    public void generarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin,
                               Cliente clienteFiltro, Servicio servicioFiltro) {

        if (fechaInicio != null && fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            System.out.println("Rango de fechas inválido. Corrija los valores.");
            return;
        }

        // Filtrado de sesiones
        List<Sesion> sesionesFiltradas = sesionService.listarSesiones().stream()
                .filter(s -> (fechaInicio == null || !s.getFechaHora().isBefore(fechaInicio)) &&
                             (fechaFin == null || !s.getFechaHora().isAfter(fechaFin)) &&
                             (clienteFiltro == null || s.getCliente().equals(clienteFiltro)) &&
                             (servicioFiltro == null || s.getServicio().equals(servicioFiltro)))
                .collect(Collectors.toList());

        if (sesionesFiltradas.isEmpty()) {
            System.out.println("No se encontraron resultados para los criterios seleccionados.");
            return;
        }

        // Métricas básicas
        long totalSesiones = sesionesFiltradas.size();
        long sesionesPendientes = sesionesFiltradas.stream().filter(s -> "PENDIENTE".equalsIgnoreCase(s.getEstado())).count();
        long sesionesConfirmadas = sesionesFiltradas.stream().filter(s -> "CONFIRMADA".equalsIgnoreCase(s.getEstado())).count();
        long sesionesCanceladas = sesionesFiltradas.stream().filter(s -> "CANCELADA".equalsIgnoreCase(s.getEstado())).count();
        long sesionesAtendidas = sesionesFiltradas.stream().filter(s -> "ATENDIDA".equalsIgnoreCase(s.getEstado())).count();

        double ingresos = sesionesFiltradas.stream()
                .filter(Sesion::isFacturada)
                .mapToDouble(s -> s.getServicio().getPrecio())
                .sum();

        double ocupacionPromedio = sesionesFiltradas.stream()
                .mapToDouble(s -> s.getServicio().getMaxConcurrentes() > 0 ?
                        (double) sesionesFiltradas.stream()
                                .filter(ss -> ss.getServicio().equals(s.getServicio()) &&
                                              ss.getFechaHora().equals(s.getFechaHora()))
                                .count() / s.getServicio().getMaxConcurrentes() : 0)
                .average()
                .orElse(0.0) * 100;

        // Mostrar reporte en pantalla
        System.out.println("===== REPORTE GENERAL =====");
        System.out.println("Fecha de reporte: " + LocalDateTime.now());
        if (clienteFiltro != null) System.out.println("Cliente filtrado: " + clienteFiltro.getNombre());
        if (servicioFiltro != null) System.out.println("Servicio filtrado: " + servicioFiltro.getNombre());
        System.out.println("Total de sesiones: " + totalSesiones);
        System.out.println("Pendientes: " + sesionesPendientes);
        System.out.println("Confirmadas: " + sesionesConfirmadas);
        System.out.println("Canceladas: " + sesionesCanceladas);
        System.out.println("Atendidas: " + sesionesAtendidas);
        System.out.println("Ingresos totales: Q" + ingresos);
        System.out.println(String.format("Ocupación promedio: %.2f%%", ocupacionPromedio));

        // Pendiente: Exportar PDF/CSV
        System.out.println("Exportación a PDF/CSV pendiente de implementación.");

        // Pendiente: registrar auditoría
        System.out.println("Registro en bitácora pendiente de implementación.");
    }
}
