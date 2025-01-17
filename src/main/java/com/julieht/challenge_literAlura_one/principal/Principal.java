package com.julieht.challenge_literAlura_one.principal;

import com.julieht.challenge_literAlura_one.modelo.*;
import com.julieht.challenge_literAlura_one.repositorio.*;
import com.julieht.challenge_literAlura_one.servicio.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.*;

public class Principal {

    //Se declaran las variables
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversorDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private ILibrosRepository librosRepository;
    private IAutorRepository autorRepository;

    public Principal(ILibrosRepository librosRepository, IAutorRepository autorRepository) {
        this.librosRepository = librosRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    *********************************************************************
                    *          Bases de Datos para Libros con Gutendex API              *
                    *                                                                   *
                    * Por favor seleccione la opción que desea realizar...              *
                    *                                                                   *
                    * 1 - Buscar Libros por Titulo/Nombre                               *
                    * 2 - Mostrar lista de los Libros                                   *
                    * 3 - Mostrar lista de los Autores                                  *
                    * 4 - Mostrar lista de los Autores vivos según un determinado año   *
                    * 5 - Mostrar lista de la cantidad de libros por determinado idioma *
                    * 6 - Buscar Autores por su nombre                                  *
                    * 7 - Mostrar el Top 10 de los Libros más descargados               *
                    *                                                                   *
                    * 0 - Salir                                                         *
                    **********************************************************          *
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    buscarAutoresVivosPorElAnio();
                    break;
                case 5:
                    listarLibrosPorElIdioma();
                    break;
                case 6:
                    buscarAutoresPorNombre();
                    break;
                case 7:
                    listarTop10();
                    break;
                case 0:
                    System.out.println("La aplicación se está cerrando!!");
                    break;
                default:
                    System.out.println("La opción no es valida!!");
            }
        }
    }

    private void buscarLibrosPorTitulo() {
        System.out.print("Ingrese el título del libro que desea buscar: ");
        String nombreLibro = teclado.nextLine().toLowerCase();

        // Obtener datos de la API
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        DatosGenerales datos = conversorDatos.obtenerDatos(json, DatosGenerales.class);

        if (datos.resultados().isEmpty()) {
            System.out.println("No se encontró el libro en la API.");
            return;
        }

        DatosLibros libroApi = datos.resultados().get(0);
        Optional<Libros> libroDb = librosRepository.findByTituloContainingIgnoreCase(libroApi.titulo());

        if (libroDb.isPresent()) {
            System.out.println("El libro ya está registrado en la base de datos:");
            System.out.println(libroDb.get());
            return;
        }

        // Procesar al autor
        Autor autor = null;
        if (!libroApi.autor().isEmpty()) {
            DatosAutor datosAutor = libroApi.autor().get(0);
            autor = autorRepository.findByNombreContainingIgnoreCase(datosAutor.nombre())
                    .stream()
                    .findFirst()
                    .orElseGet(() -> {
                        Autor nuevoAutor = new Autor(datosAutor);
                        return autorRepository.save(nuevoAutor);
                    });
        }

        // Crear y guardar el libro en la base de datos
        Libros nuevoLibro = new Libros(libroApi, autor);
        librosRepository.save(nuevoLibro);
        System.out.println("Libro guardado exitosamente:\n" + nuevoLibro);
    }

    private void listarLibros() {
        List<Libros> libros = librosRepository.findAll(Sort.by(Sort.Order.asc("idiomas")));
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            libros.forEach(libro -> {
                System.out.println("ID: " + libro.getId());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idioma: " + libro.getIdiomas());
                System.out.println("Número de descargas: " + libro.getNumeroDescargas());
                System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Sin autor asociado"));
                System.out.println("----------------------------------");
            });
        }
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores registrados en la base de datos.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void buscarAutoresVivosPorElAnio() {
        System.out.print("Ingrese el año para buscar autores vivos: ");
        int fecha = teclado.nextInt();
        List<Autor> autores = autorRepository.buscarAutoresVivosPorElAnio(fecha);
        autores.forEach(System.out::println);
    }

    private void listarLibrosPorElIdioma() {
        System.out.print("Ingrese el idioma para buscar los libros (ejemplo: inglés, español, frances o portugués): ");
        String idioma = teclado.nextLine().toLowerCase();
        Idiomas idiomaEnum = Idiomas.fromEspanol(idioma);
        List<Libros> librosPorIdioma = librosRepository.findByIdiomas(idiomaEnum);
        librosPorIdioma.forEach(System.out::println);
    }

    private void buscarAutoresPorNombre() {
        System.out.print("Ingrese el nombre del autor que desea buscar: ");
        String nombre = teclado.nextLine();
        List<Autor> autores = autorRepository.findByNombreContainingIgnoreCase(nombre);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores con ese nombre.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarTop10() {
        List<Libros> top10Libros = librosRepository.listarTop10(PageRequest.of(0, 10, Sort.by(Sort.Order.desc("numeroDescargas"))));
        top10Libros.forEach(System.out::println);
    }
}
