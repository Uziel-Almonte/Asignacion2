package edu.pucmm.eict.encapsulaciones;

import java.util.Objects;

import jakarta.persistence.*;

/**
 * Objeto con estructura POJO.
 */
@Entity
@Table(name = "estudiantes") 
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matricula;

    private String nombre;
    private String apellido; // Nuevo atributo
    private String telefono; // Nuevo atributo
    private String carrera;

    public Estudiante() {
    }

    public Estudiante(int matricula, String nombre, String apellido, String telefono, String carrera) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.carrera = carrera;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void mezclar(Estudiante e) {
        this.matricula = e.getMatricula();
        this.nombre = e.getNombre();
        this.carrera = e.getCarrera();
        this.telefono = e.getTelefono();
        this.apellido = e.getApellido();
    }    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return matricula == that.matricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}
