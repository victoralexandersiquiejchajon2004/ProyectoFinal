/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.repository;

package repository;

import model.Servicio;

import java.util.*;

public class ServicioRepository {
    private final Map<String, Servicio> servicios = new HashMap<>();

    public void save(Servicio servicio) {
        servicios.put(servicio.getCodigo(), servicio);
    }

    public Servicio findByCodigo(String codigo) {
        return servicios.get(codigo);
    }

    public List<Servicio> findAll() {
        return new ArrayList<>(servicios.values());
    }

    public void delete(String codigo) {
        servicios.remove(codigo);
    }
}
