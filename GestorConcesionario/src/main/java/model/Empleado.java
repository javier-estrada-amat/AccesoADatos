package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Empleado {
   
    private String nombre, apellido, correo;
    private int telefono;
    private Tipo tipo;

    public Empleado(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void mostrarDatos() {
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
    }
}
