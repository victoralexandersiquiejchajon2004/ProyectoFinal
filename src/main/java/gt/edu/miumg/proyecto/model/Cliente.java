/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.model;


import java.time.LocalDate;

public class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String dpi;
    private LocalDate fechaNacimiento;
    private boolean activo; // ← NUEVO ATRIBUTO

    public Cliente(String id, String nombre, String correo, String telefono, String direccion, String dpi, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.dpi = dpi;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = true; // Por defecto activo
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDpi() { return dpi; }
    public void setDpi(String dpi) { this.dpi = dpi; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public boolean isActivo() { return activo; } // ← NUEVO MÉTODO
    public void setActivo(boolean activo) { this.activo = activo; } // ← NUEVO MÉTODO
    
    private List<Notificacion> notificaciones = new ArrayList<>();

public List<Notificacion> getNotificaciones() {
    return notificaciones;
}

public void setNotificaciones(List<Notificacion> notificaciones) {
    this.notificaciones = notificaciones;
}

}
