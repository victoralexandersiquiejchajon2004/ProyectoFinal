/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import gt.edu.miumg.proyecto.model.Cliente;

public interface ClienteService {
    Cliente registrarClienteWeb(Cliente cliente);   // Recepcionista
    Cliente registrarClienteApp(String nombre, String email, String password);  // App
}
