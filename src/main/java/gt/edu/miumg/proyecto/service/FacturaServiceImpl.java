/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import gt.edu.miumg.proyecto.model.Factura;
import gt.edu.miumg.proyecto.model.Sesion;
import gt.edu.miumg.proyecto.repository.FacturaRepository;

import java.time.LocalDateTime;

public class FacturaServiceImpl implements FacturaService {

    private FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public Factura generarFactura(Factura factura) throws Exception {
        Sesion sesion = factura.getSesion();
        if (sesion == null || sesion.isFacturada()) {
            throw new Exception("Sesión no facturable o ya facturada");
        }
        if (factura.getPrecio() <= 0 || factura.getDuracion() <= 0) {
            throw new Exception("Precio y duración deben ser mayores a cero");
        }

        factura.setFecha(LocalDateTime.now());
        sesion.setFacturada(true);
        return facturaRepository.save(factura);
    }

    @Override
    public Factura obtenerFacturaPorSesion(Long sesionId) {
        return facturaRepository.findBySesionId(sesionId);
    }
}
