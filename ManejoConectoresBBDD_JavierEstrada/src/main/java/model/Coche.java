package model;

import database.HibernateUtil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table (name = "coches")

public class Coche implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String marca;
    @Column
    private String modelo;
    @Column
    private String matricula;

    public Coche(int id){
        this.id = id;
    }

    public Coche(String marca, String modelo, String matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    public void anadirNuevoCoche(String marca, String modelo, String matricula){
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(
                new Coche(marca,modelo,matricula));
        session.getTransaction().commit();
        session.close();
        System.out.println("Se ha añadido el coche correctamente");
    }

    public void borrarCocheID(int id){
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Coche coche = new Coche(id);
        session.delete(coche);
        session.getTransaction().commit();
        session.close();
    }

    public void consultarPorID(int id){
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Coche coche = session.get(Coche.class,id);
        System.out.println(coche);
        session.getTransaction().commit();
        session.close();
    }

    public void modificarPorID(int id, String marca, String modelo, String matricula){
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Coche coche = session.get(Coche.class,id);
        coche.setMarca(marca);
        coche.setModelo(modelo);
        coche.setMatricula(matricula);
        session.merge(coche);
        session.getTransaction().commit();
        session.close();
        System.out.println("Coche actualizado con exito");
    }

    public void listarTodos(){
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Coche> query = session.createQuery("SELECT t FROM Coche t",Coche.class);
        List<Coche> listaCoches = query.list();
        for (Coche coche: listaCoches){
            System.out.println(coche);
        }
        session.getTransaction().commit();
        session.close();
    }

}
