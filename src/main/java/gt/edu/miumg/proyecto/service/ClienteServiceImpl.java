/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import gt.edu.miumg.proyecto.model.Cliente;
import gt.edu.miumg.proyecto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Cliente registrarClienteWeb(Cliente cliente) {
        if (clienteRepository.findByIdentificador(cliente.getIdentificador()).isPresent()) {
            throw new RuntimeException("Identificador ya registrado");
        }
        if (cliente.getEmail() != null && clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente registrarClienteApp(String nombre, String email, String password) {
        if (clienteRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }
        String passwordHash = passwordEncoder.encode(password);
        Cliente cliente = new Cliente();
        cliente.setNombreCompleto(nombre);
        cliente.setEmail(email);
        cliente.setPasswordHash(passwordHash);
        return clienteRepository.save(cliente);
    }
}
