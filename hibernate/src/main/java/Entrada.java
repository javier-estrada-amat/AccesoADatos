import database.HibernateUtil;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Entrada {

    public static void main(String[] args) {
/*
        //Inserccion
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(
                new Trabajador("Borja","Amat","Madrid",123123));
        session.getTransaction().commit();
        session.close();  */


        // Obtencion -> select - ps, rs
        /*
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

       Trabajador t = session.get(Trabajador.class,1);
        System.out.println(t);
        session.getTransaction().commit();
        session.close();

        */

        //ACTUALIZAR

    /*    SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Trabajador trabajador = session.get(Trabajador.class,1);
        trabajador.setDireccion("Cádiz");
        session.merge(trabajador);
        session.getTransaction().commit();
        session.close();

     */
                    //ELIMINAR
      /*  SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Trabajador trabajador = new Trabajador(1);
        session.delete(trabajador);

        session.getTransaction().commit();
        session.close();
        */
        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        // T es la nomenclatura para un trabajador
        // Parecido a SELECT * FROM EMPLEADOS
        Query<Trabajador> query = session.createQuery("SELECT t FROM Trabajador t",Trabajador.class);
        List<Trabajador> listaTrabajadores = query.list();
        for (Trabajador trabajador: listaTrabajadores){
            System.out.println(trabajador);
        }

        session.getTransaction().commit();
        session.close();
    }


}
