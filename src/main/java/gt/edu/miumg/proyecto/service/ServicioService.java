/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;

package service;

import exception.ServicioException;
import model.Servicio;
import repository.ServicioRepository;

import java.util.List;

public class ServicioService {
    private final ServicioRepository repository;

    public ServicioService(ServicioRepository repository) {
        this.repository = repository;
    }

    // Crear servicio
    public void crearServicio(Servicio servicio) throws ServicioException {
        if (servicio.getCodigo() == null || servicio.getCodigo().isBlank())
            throw new ServicioException("El código es obligatorio.");
        if (repository.findByCodigo(servicio.getCodigo()) != null)
            throw new ServicioException("El código ya está en uso.");
        if (servicio.getPrecio() <= 0)
            throw new ServicioException("El precio debe ser mayor a 0.");
        if (servicio.getDuracionMinutos() <= 0)
            throw new ServicioException("La duración debe ser mayor a 0.");
        if (servicio.getMaxConcurrentes() < 1 || servicio.getMaxConcurrentes() > 10)
            throw new ServicioException("El valor de maxConcurrentes debe estar entre 1 y 10.");

        repository.save(servicio);
    }

    // Modificar servicio (excepto código)
    public void modificarServicio(String codigo, String nombre, String descripcion,
                                  double precio, int duracion, int maxConcurrentes) throws ServicioException {
        Servicio servicio = repository.findByCodigo(codigo);
        if (servicio == null) throw new ServicioException("Servicio no encontrado.");
        if (!servicio.isActivo()) throw new ServicioException("No se puede modificar un servicio inactivo.");
        if (precio <= 0 || duracion <= 0)
            throw new ServicioException("Precio y duración deben ser mayores a 0.");
        if (maxConcurrentes < 1 || maxConcurrentes > 10)
            throw new ServicioException("maxConcurrentes fuera de rango (1–10).");

        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(precio);
        servicio.setDuracionMinutos(duracion);
        servicio.setMaxConcurrentes(maxConcurrentes);
    }

    // Eliminar servicio (borrado lógico)
    public void eliminarServicio(String codigo) throws ServicioException {
        Servicio servicio = repository.findByCodigo(codigo);
        if (servicio == null) throw new ServicioException("Servicio no encontrado.");
        // 🔴 Pendiente: verificar si hay citas futuras asociadas
        servicio.setActivo(false);
    }

    // Listar servicios
    public List<Servicio> listarServicios() {
        return repository.findAll();
    }
}
