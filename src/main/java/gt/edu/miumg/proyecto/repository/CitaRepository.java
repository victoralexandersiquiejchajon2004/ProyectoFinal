/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.repository;


import gt.edu.miumg.proyecto.model.Cita;
import gt.edu.miumg.proyecto.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByServicioAndFechaHora(Servicio servicio, LocalDateTime fechaHora);
    List<Cita> findByClienteIdAndEstado(Long clienteId, String estado);
}
