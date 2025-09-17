/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.controller;


import gt.edu.miumg.proyecto.model.Cliente;
import gt.edu.miumg.proyecto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Registro por recepcionista (Web)
    @PostMapping("/web")
    public ResponseEntity<Cliente> registrarClienteWeb(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.registrarClienteWeb(cliente));
    }

    // Registro por cliente (App)
    @PostMapping("/app")
    public ResponseEntity<Cliente> registrarClienteApp(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return ResponseEntity.ok(clienteService.registrarClienteApp(nombre, email, password));
    }
}
