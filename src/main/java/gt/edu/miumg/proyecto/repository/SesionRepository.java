/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.repository;

package repository;

import model.Cliente;
import model.Sesion;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SesionRepository {
    private final Map<String, Sesion> sesiones = new HashMap<>();

    public void save(Sesion sesion) {
        sesiones.put(sesion.getId(), sesion);
    }

    public Sesion findById(String id) {
        return sesiones.get(id);
    }

    public List<Sesion> findByClienteAndFiltro(Cliente cliente,
                                               LocalDateTime inicio,
                                               LocalDateTime fin,
                                               String servicioNombre) {
        return sesiones.values().stream()
                .filter(s -> s.getCliente().getId().equals(cliente.getId()))
                .filter(s -> (inicio == null || !s.getFechaHora().isBefore(inicio)))
                .filter(s -> (fin == null || !s.getFechaHora().isAfter(fin)))
                .filter(s -> (servicioNombre == null || s.getServicio().getNombre().equalsIgnoreCase(servicioNombre)))
                .sorted(Comparator.comparing(Sesion::getFechaHora))
                .collect(Collectors.toList());
    }
}
