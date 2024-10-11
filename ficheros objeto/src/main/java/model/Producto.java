package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Producto implements Serializable {


    public static final Long serialVersionUID = 12345L;
    private int id;
    private String title;
    private double price;
    private int stock;

    public void mostrarDatos(){
        System.out.println(serialVersionUID);
        System.out.println(id);
        System.out.println(title);
        System.out.println(price);
        System.out.println(stock);
    }
}
