/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import exception.HistorialException;
import model.Cliente;
import model.Sesion;
import model.Servicio;
import repository.SesionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SesionService {

    private final SesionRepository repository;
    private final List<Servicio> servicios; // Simulación en memoria (puedes reemplazar con ServicioRepository)

    public SesionService(SesionRepository repository) {
        this.repository = repository;
        this.servicios = new ArrayList<>();
    }

    /**
     * Consulta historial de sesiones con filtros opcionales.
     */
    public List<Sesion> consultarHistorial(Cliente cliente,
                                           LocalDateTime inicio,
                                           LocalDateTime fin,
                                           String servicioNombre) throws HistorialException {
        if (cliente == null || !cliente.isActivo())
            throw new HistorialException("Cliente no encontrado o inactivo.");

        List<Sesion> resultado = repository.findByClienteAndFiltro(cliente, inicio, fin, servicioNombre);

        if (resultado.isEmpty())
            throw new HistorialException("No hay sesiones para los criterios seleccionados.");

        return resultado;
    }

    /**
     * Retorna la lista de servicios activos (UC-13).
     */
    public List<Servicio> listarServiciosActivos() {
        List<Servicio> activos = servicios.stream()
                .filter(Servicio::isActivo)
                .collect(Collectors.toList());

        if (activos.isEmpty()) {
            System.out.println("No existen servicios disponibles en este momento.");
        }

        return activos;
    }

    /**
     * Retorna el historial de sesiones de un cliente autenticado (UC-14).
     * Solo incluye ATENDIDAS o CANCELADAS, ordenadas de más reciente a más antigua.
     */
    public List<Sesion> verHistorialSesiones(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return List.of();
        }

        List<Sesion> historial = repository.findByCliente(cliente).stream()
                .filter(s -> s.getEstado() == Sesion.Estado.ATENDIDA ||
                             s.getEstado() == Sesion.Estado.CANCELADA)
                .sorted(Comparator.comparing(Sesion::getFecha).reversed())
                .collect(Collectors.toList());

        if (historial.isEmpty()) {
            System.out.println("No existen sesiones registradas en su historial.");
        }

        return historial;
    }
}
