/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.repository;


import gt.edu.miumg.proyecto.model.Cliente;
import gt.edu.miumg.proyecto.model.Factura;

import java.util.List;

public interface FacturaRepository {
    List<Factura> findByCliente(Cliente cliente);
}
