/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.repository;

package repository;

import model.Cliente;
import model.Notificacion;
import java.util.List;

public interface NotificacionRepository {
    void guardar(Notificacion notificacion);
    List<Notificacion> buscarPorCliente(Cliente cliente);
}
