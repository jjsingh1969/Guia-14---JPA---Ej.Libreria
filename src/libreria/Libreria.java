package libreria;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.services.AutorService;
import libreria.services.EditorialService;
import libreria.services.LibroService;

public class Libreria {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
        EntityManager em = emf.createEntityManager();

        AutorService as = new AutorService();
        EditorialService es = new EditorialService();
        LibroService ls = new LibroService();
        Scanner read = new Scanner(System.in).useDelimiter("\n");
        boolean salir = true;

        while (salir) {
            System.out.println("MENU LIBRERIA");
            System.out.println("1) Altas");
            System.out.println("2) Bajas");
            System.out.println("3) Editar");
            System.out.println("4) Búsqueda de un Autor por nombre.");
            System.out.println("5) Búsqueda de un libro por ISBN.");
            System.out.println("6) Búsqueda de un libro por Título.");
            System.out.println("7) Búsqueda de un libro/s por nombre de Autor.");
            System.out.println("8) Búsqueda de un libro/s por nombre de Editorial.");
            System.out.println("9) Salir");

            try {
                System.out.println("Escribe una de las opciones");
                int opcion = read.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("MENU ALTAS");
                        System.out.println("1) Autor");
                        System.out.println("2) Editorial");
                        System.out.println("3) Libro");
                        System.out.println("4) Salir");
                        System.out.println("Indique lo que desea dar alta");
                        int opcionA = read.nextByte();
                        switch (opcionA) {
                            case 1:
                                as.crearAutor();
                                break;
                            case 2:
                                es.crearEditorial();
                                break;
                            case 3:
                                ls.crearLibro();
                                break;
                            case 4:
                                salir = false;
                                break;
                            default:
                                System.out.println("Debe ingresar una opcion valida");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("MENU BAJAS");
                        System.out.println("1) Autor");
                        System.out.println("2) Editorial");
                        System.out.println("3) Libro");
                        System.out.println("4) Salir");
                        System.out.println("Indique lo que desea dar baja");
                        int opcionB = read.nextByte();
                        switch (opcionB) {
                            case 1:
                                as.borrarAutor();
                                break;
                            case 2:
                                es.borrarEditorial();
                                break;
                            case 3:
                                ls.borrarLibro();
                                break;
                            case 4:
                                salir = false;
                                break;
                            default:
                                System.out.println("Debe ingresar una opcion valida");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("MENU EDITAR");
                        System.out.println("1) Autor");
                        System.out.println("2) Editorial");
                        System.out.println("3) Libro");
                        System.out.println("4) Salir");
                        System.out.println("Indique lo que desea editar");
                        int opcionE = read.nextInt();
                        switch (opcionE) {
                            case 1:
                                as.modificarAutor();
                                break;
                            case 2:
                                es.modificarEditorial();
                                break;
                            case 3:
                                ls.modificarLibro();
                                break;
                            case 4:
                                salir = true;
                                break;
                            default:
                                System.out.println("Debe ingresar una opcion valida");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Ingrese el nombre del autor");
                        String nombre = read.next();
                        as.busquedaAutorPorNombre(nombre);
                        break;
                    case 5:
                        ls.busquedaLibroPorIsbn();
                        break;
                    case 6:
                        ls.busquedaLibroPorTitulo();
                        break;
                    case 7:
                        ls.busquedaLibroPorNombreAutor();
                        break;
                    case 8:
                        ls.busquedaLibroPorNombreEditorial();
                        break;
                    case 9:
                        System.out.println("Que tenga un buen día");
                        salir = false;
                        break;
                    default:
                        System.out.println("Debe ingresar una opcion valida");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                read.next();
            }
        }

    }

}
