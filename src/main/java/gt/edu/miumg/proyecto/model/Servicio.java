/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.model;

package model;

public class Servicio {
    private String codigo;          // Identificador único e inmutable
    private String nombre;
    private String descripcion;
    private double precio;
    private int duracionMinutos;
    private int maxConcurrentes;
    private boolean activo;

    public Servicio(String codigo, String nombre, String descripcion,
                    double precio, int duracionMinutos, int maxConcurrentes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionMinutos = duracionMinutos;
        this.maxConcurrentes = maxConcurrentes;
        this.activo = true;
    }

    // Getters y setters (excepto código que no se puede modificar)
    public String getCodigo() { return codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public int getMaxConcurrentes() { return maxConcurrentes; }
    public void setMaxConcurrentes(int maxConcurrentes) { this.maxConcurrentes = maxConcurrentes; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return String.format("Servicio[%s - %s, Precio: %.2f, Duración: %d min, Cupo: %d, Activo: %b]",
                codigo, nombre, precio, duracionMinutos, maxConcurrentes, activo);
    }
}
