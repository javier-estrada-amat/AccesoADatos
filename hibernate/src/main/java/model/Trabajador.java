package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table (name = "empleados")

public class Trabajador implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String direccion;
    @Column
    private int telefono;

    public Trabajador(int id) {
        this.id = id;
    }

    public Trabajador(String nombre, String apellido, String direccion, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }


}
