package libreria.services;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

public class EditorialService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    EntityManager em = emf.createEntityManager();
    Scanner read = new Scanner(System.in).useDelimiter("\n");

    public void crearEditorial() {
        Editorial editorial = new Editorial();

        try {

            System.out.println("Ingrese el nombre de la editorial");
            String nombre = read.nextLine();
            if (nombre == null) {
                throw new Exception("Por favor ingrese nombre de la editorial");
            }
            if (busquedaAutorPorNombreEditorial(nombre) != null) {
                throw new Exception("La editorial ya existe");
            }
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public void borrarEditorial() {

        try {
            System.out.println("Ingrese el Id de la editorial a borrar");
            Long id = read.nextLong();
            if (id == null) {
                throw new Exception("Por favor ingrese id de la editorial");
            }
            Editorial editorial = em.find(Editorial.class, id);
            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

    }

    public void modificarEditorial() {
        try {
            System.out.println("Ingrese el Id de la editorial a modificar");
            Long id = read.nextLong();
            if (id == null) {
                throw new Exception("Por favor ingrese id de la editorial");
            }
            Editorial editorial = em.find(Editorial.class, id);
            System.out.println("Escriba el nombre correcto");
            String nombre = read.next();
            editorial.setNombre(nombre);
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public Editorial busquedaEditorialPorId(Long id) {
        Editorial editorial = new Editorial();
        try {
            if (id == null) {
                throw new Exception("Por favor ingrese id de la editorial");
            }
            editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id LIKE :id ")
                    .setParameter("id", id).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

        return editorial;
    }

    public Editorial busquedaAutorPorNombreEditorial(String nombre) {
        
        Editorial editorial= new Editorial();
        try {

            if (nombre == null) {
                throw new Exception("Por favor ingrese nombre del autor");
            }

            editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre ")
                    .setParameter("nombre", nombre).getSingleResult();
            imprimirEditorial(editorial);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
        return editorial;
    }

    private void imprimirEditorial(Editorial editorial) {
        System.out.println("ID "+editorial.getId());
        System.out.println("Nombre Editorial "+editorial.getNombre());
    }

}
