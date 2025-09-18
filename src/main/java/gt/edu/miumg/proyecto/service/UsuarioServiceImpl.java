/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import gt.edu.miumg.proyecto.model.Usuario;
import gt.edu.miumg.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario autenticar(String usernameOrEmail, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(usernameOrEmail);

        if (!usuario.isPresent()) {
            usuario = usuarioRepository.findByEmail(usernameOrEmail);
        }

        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            if (!usuario.get().isActivo()) {
                throw new RuntimeException("Cuenta inactiva. Contacte al administrador.");
            }
            return usuario.get();
        }

        throw new RuntimeException("Usuario o contraseña inválidos");
    }
}
