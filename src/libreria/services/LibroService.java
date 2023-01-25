package libreria.services;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;

public class LibroService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    EntityManager em = emf.createEntityManager();
    Scanner read = new Scanner(System.in).useDelimiter("\n");
    AutorService as = new AutorService();
    EditorialService es = new EditorialService();

    public void crearLibro() {
        Libro libro = new Libro();

        try {

            System.out.println("Ingrese el numero de ISBN");
            Long isbn = read.nextLong();
            libro.setIsbn(isbn);
            System.out.println("Ingrese el titulo del libro");
            String titulo = read.next();
            libro.setTitulo(titulo);
            System.out.println("Ingrese el año de su publicacion");
            Integer anio = read.nextInt();
            System.out.println("Ingrese la cantidad de ejemplares ");
            Integer ejemplares = read.nextInt();
            libro.setAlta(true);
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public void borrarLibro() {

        try {
            System.out.println("Ingrese el Id del libro a borrar");
            Long id = read.nextLong();
            Libro libro = em.find(Libro.class, id);
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

    }

    public void modificarLibro() {
        boolean salir;
        try {
            System.out.println("Ingrese el Id del libro a modificar");
            Long id = read.nextLong();
            Libro libro = em.find(Libro.class, id);

            System.out.println("MENU MODIFICAR LIBRO");
            System.out.println("1) Modificar ISBN");
            System.out.println("2) Modificar titulo");
            System.out.println("3) Modificar año de publicación");
            System.out.println("4) Modificar cantidad de ejemplares");
            System.out.println("5) Modificar autor");
            System.out.println("6) Modificar editorial");
            System.out.println("7) Salir");

            System.out.println("Ingrese alguna de las opciones");
            int opcion = read.nextByte();
            switch (opcion) {
                case 1:
                    System.out.println("Escriba el numero ISBN correcto");
                    Long isbn = read.nextLong();
                    libro.setIsbn(isbn);
                    break;
                case 2:
                    System.out.println("Escriba el titulo correcto");
                    String titulo = read.next();
                    libro.setTitulo(titulo);
                    break;
                case 3:
                    System.out.println("Escriba el año correcto de publicación");
                    Integer anio = read.nextInt();
                    libro.setAnio(anio);
                    break;
                case 4:
                    System.out.println("Escriba la cantidad correcta de ejemplares");
                    Integer ejemp = read.nextInt();
                    libro.setEjemplares(ejemp);
                    break;
                case 5:
                    System.out.println("Ingrese id del autor");
                    Long idAutor = read.nextLong();
                    Autor autor = as.busquedaAutorPorId(idAutor);
                    libro.setAutor(autor);
                    break;
                case 6:
                    System.out.println("Ingrese id de la editorial");
                    Long idEditorial = read.nextLong();
                    Editorial editorial = es.busquedaEditorialPorId(idEditorial);
                    libro.setEditorial(editorial);
                    break;
                case 7:
                    salir = false;
                default:
                    System.out.println("Debe ingresar una opcion valida");

            }

            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    }

    public void busquedaLibroPorIsbn() {

        try {
            System.out.println("Ingrese el ISBN del libro");
            Long isbn = read.nextLong();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn ")
                    .setParameter("isbn", isbn).getSingleResult();
            imprimirLibro(libro);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

    }

    public void busquedaLibroPorTitulo() {

        try {
            System.out.println("Ingrese el titulo del libro");
            String titulo = read.next();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo ")
                    .setParameter("titulo", titulo).getSingleResult();
            imprimirLibro(libro);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

    }

    public void busquedaLibroPorNombreAutor() {

        try {
            System.out.println("Ingrese el nombre del autor");
            String nombre = read.next();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre ")
                    .setParameter("nombre", nombre).getSingleResult();
            imprimirLibro(libro);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

    }

    public void busquedaLibroPorNombreEditorial() {

        try {
            System.out.println("Ingrese el nombre de la editorial");
            String nombre = read.next();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre ")
                    .setParameter("nombre", nombre).getSingleResult();
            imprimirLibro(libro);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

    }

    public void imprimirLibro(Libro libro) {

        System.out.println(libro.toString());
    }
}
