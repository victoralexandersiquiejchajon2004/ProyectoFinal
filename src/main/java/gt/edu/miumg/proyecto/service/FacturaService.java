/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.service;


import gt.edu.miumg.proyecto.model.Cliente;
import gt.edu.miumg.proyecto.model.Factura;
import gt.edu.miumg.proyecto.repository.FacturaRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FacturaService {

    private final FacturaRepository repository;

    public FacturaService(FacturaRepository repository) {
        this.repository = repository;
    }

    /**
     * Consulta todas las facturas de un cliente autenticado (UC-15).
     */
    public List<Factura> consultarFacturas(Cliente cliente) {
        if (cliente == null || !cliente.isActivo()) {
            System.out.println("Cliente no encontrado o inactivo.");
            return List.of();
        }

        List<Factura> facturas = repository.findByCliente(cliente);

        if (facturas.isEmpty()) {
            System.out.println("No existen facturas registradas a su nombre.");
            return List.of();
        }

        return facturas.stream()
                .sorted(Comparator.comparing(Factura::getFechaEmision).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el detalle de una factura específica.
     */
    public Factura consultarDetalleFactura(String numeroFactura, Cliente cliente) {
        return repository.findByCliente(cliente).stream()
                .filter(f -> f.getNumeroFactura().equals(numeroFactura))
                .findFirst()
                .orElse(null);
    }

    /**
     * Simula exportación en PDF/XML (pendiente de implementar).
     */
    public void exportarFactura(Factura factura, String formato) {
        if (factura == null) {
            System.out.println("Factura no encontrada.");
            return;
        }

        switch (formato.toUpperCase()) {
            case "PDF" -> System.out.println("Exportando factura " + factura.getNumeroFactura() + " a PDF...");
            case "XML" -> System.out.println("Exportando factura " + factura.getNumeroFactura() + " a XML...");
            default -> System.out.println("Formato no soportado.");
        }
    }
}
