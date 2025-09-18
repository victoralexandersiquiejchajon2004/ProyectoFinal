/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.controller;


import gt.edu.miumg.proyecto.model.Cliente;
import gt.edu.miumg.proyecto.service.ClienteService;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Editar datos de un cliente existente.
     */
    public void editarCliente(Cliente clienteExistente, String nuevoNombre, String nuevoCorreo,
                              String nuevoTelefono, String nuevaDireccion, String nuevoDPI) {

        if (clienteExistente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Validaciones
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return;
        }

        if (!validarEmail(nuevoCorreo)) {
            System.out.println("Formato de correo inválido.");
            return;
        }

        if (!validarTelefono(nuevoTelefono)) {
            System.out.println("Formato de teléfono inválido.");
            return;
        }

        if (!validarDPI(nuevoDPI)) {
            System.out.println("DPI inválido. Debe tener al menos 8 dígitos.");
            return;
        }

        // Verificar unicidad de correo y DPI
        if (clienteService.existeCorreoEnOtroCliente(clienteExistente, nuevoCorreo)) {
            System.out.println("El correo ya está registrado en otro cliente.");
            return;
        }

        if (clienteService.existeDPIEnOtroCliente(clienteExistente, nuevoDPI)) {
            System.out.println("El DPI ya está registrado en otro cliente.");
            return;
        }

        // Actualizar datos
        clienteExistente.setNombre(nuevoNombre);
        clienteExistente.setCorreo(nuevoCorreo);
        clienteExistente.setTelefono(nuevoTelefono);
        clienteExistente.setDireccion(nuevaDireccion);
        clienteExistente.setDpi(nuevoDPI);

        // Guardar cambios en servicio
        clienteService.actualizarCliente(clienteExistente);

        // Pendiente: registrar auditoría
        System.out.println("Datos de cliente actualizados exitosamente. Auditoría pendiente.");
    }

    private boolean validarEmail(String email) {
        if (email == null) return false;
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    private boolean validarTelefono(String telefono) {
        if (telefono == null) return false;
        return telefono.matches("\\d{8,}");
    }

    private boolean validarDPI(String dpi) {
        if (dpi == null) return false;
        return dpi.matches("\\d{8,}");
    }
}
