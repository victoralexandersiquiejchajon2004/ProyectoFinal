/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gt.edu.miumg.proyecto.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Para ambos registros
    @Column(nullable = false)
    private String nombreCompleto;

    // Para registro Web (DPI/Identificador único)
    @Column(unique = true)
    private String identificador; 

    private LocalDate fechaNacimiento;

    private String telefono;

    @Column(unique = true)
    private String email;

    // Para registro desde la App
    private String passwordHash;

    // Estado (activo, desactivado, eliminado)
    private boolean activo = true;

    // === Constructores ===
    public Cliente() {}

    public Cliente(String nombreCompleto, String identificador, LocalDate fechaNacimiento,
                   String telefono, String email, String passwordHash) {
        this.nombreCompleto = nombreCompleto;
        this.identificador = identificador;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // === Getters & Setters ===
    public Long getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
