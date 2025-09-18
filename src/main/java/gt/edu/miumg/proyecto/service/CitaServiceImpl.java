/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import gt.edu.miumg.proyecto.model.*;
import gt.edu.miumg.proyecto.repository.CitaRepository;
import gt.edu.miumg.proyecto.repository.UsuarioRepository;
import gt.edu.miumg.proyecto.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Cita agendarCita(Long clienteId, Long servicioId, String notas, String fechaHora, boolean esRecepcionista) {
        Optional<Usuario> clienteOpt = usuarioRepository.findById(clienteId);
        Optional<Servicio> servicioOpt = servicioRepository.findById(servicioId);

        if (!clienteOpt.isPresent() || !clienteOpt.get().isActivo()) {
            throw new RuntimeException("Cliente inválido o inactivo.");
        }

        if (!servicioOpt.isPresent()) {
            throw new RuntimeException("Servicio inválido.");
        }

        LocalDateTime fechaHoraParsed = LocalDateTime.parse(fechaHora);

        // Validar solapamiento de horarios
        List<Cita> citasMismaHora = citaRepository.findByServicioAndFechaHora(servicioOpt.get(), fechaHoraParsed);
        if (!citasMismaHora.isEmpty()) {
            throw new RuntimeException("El horario no está disponible. Por favor elige otra hora.");
        }

        Cita cita = new Cita();
        cita.setCliente(clienteOpt.get());
        cita.setServicio(servicioOpt.get());
        cita.setNotas(notas);
        cita.setFechaHora(fechaHoraParsed);

        if (esRecepcionista) {
            cita.setEstado(EstadoCita.CONFIRMADA);
        } else {
            cita.setEstado(EstadoCita.PENDIENTE);
        }

        return citaRepository.save(cita);
    }
}
