/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package gt.edu.miumg.proyecto.controller;

import gt.edu.miumg.proyecto.model.Usuario;
import gt.edu.miumg.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestParam String usernameOrEmail,
                        @RequestParam String password) {
        try {
            Usuario usuario = usuarioService.autenticar(usernameOrEmail, password);

            // Simulación de token JWT
            String token = "TOKEN_" + usuario.getUsername() + "_" + System.currentTimeMillis();

            return "Bienvenido " + usuario.getUsername() + " - Token: " + token;

        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
