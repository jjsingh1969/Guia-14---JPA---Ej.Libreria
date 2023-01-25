package libreria.services;

import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    EntityManager em = emf.createEntityManager();

    Scanner read = new Scanner(System.in).useDelimiter("\n");

    public void crearAutor() {
        Autor autor = new Autor();

        try {

            System.out.println("Ingrese el nombre del autor");
            String nombre = read.nextLine();
            if (nombre == null) {
                throw new Exception("Por favor ingrese nombre del autor");
            }
            if (busquedaAutorPorNombre(nombre) != null) {
                throw new Exception("El autor ya existe");
            }
            autor.setNombre(nombre);
            autor.setAlta(true);
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public void borrarAutor() {

        try {
            System.out.println("Ingrese el Id del autor a borrar");
            Long id = read.nextLong();
            if (id == null) {
                throw new Exception("Por favor ingrese id del autor");
            }
            Autor autor = em.find(Autor.class, id);
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

    }

    public void modificarAutor() {
        try {
            System.out.println("Ingrese el Id del autor a modificar");
            Long id = read.nextLong();
            if (id == null) {
                throw new Exception("Por favor ingrese id del autor");
            }
            Autor autor = em.find(Autor.class, id);
            System.out.println("Escriba el nombre correcto");
            String nombre = read.next();
            autor.setNombre(nombre);
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public Autor busquedaAutorPorNombre(String nombre) {
        Autor autor = new Autor();
        try {
//            System.out.println("Ingrese el nombre del autor");
//            String nombre = read.next();
            if (nombre == null) {
                throw new Exception("Por favor ingrese nombre del autor");
            }

            autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre ")
                    .setParameter("nombre", nombre).getSingleResult();
            imprimirAutor(autor);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
        return autor;

    }

    public Autor busquedaAutorPorId(Long id) {
        Autor autor = new Autor();
        try {
            if (id == null) {
                throw new Exception("Por favor ingrese id del autor");
            }
            autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id LIKE :id ")
                    .setParameter("id", id).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

        return autor;
    }

    public void imprimirAutor(Autor autor) {

        System.out.println("ID " + autor.getId());
        System.out.println("Nombre del Autor " + autor.getNombre());
    }

}
