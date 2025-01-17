package com.julieht.challenge_literAlura_one.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libros {

    //Se declaran las variables y atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private String nombreAutor;
    private int numeroDescargas;
    @ManyToOne(optional = true) // Permite que el autor sea null
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;

    //Constructor predeterminado
    public Libros() {}

    public Libros(DatosLibros datosLibros, Autor autor) {
        this.titulo = datosLibros.titulo();
        // Tomar el primer idioma de la lista y convertirlo
        if (!datosLibros.idiomas().isEmpty()) {
            this.idiomas = Idiomas.fromString(datosLibros.idiomas().get(0));
        }
        this.numeroDescargas = datosLibros.numeroDescargas();
        this.autor = autor;
        if (autor != null) {
            this.nombreAutor = autor.getNombre();
        } else if (!datosLibros.autor().isEmpty()) {
            this.nombreAutor = datosLibros.autor().get(0).nombre();
        }
    }

    @Override
    public String toString() {
        return "       Libro:  " + '\n' +
                " Id: " + id + '\n' +
                " Titulo: '" + titulo + '\n' +
                " Idioma: " + idiomas + '\n' +
                " Numero de Descargas: " + numeroDescargas + '\n' +
                " Autor: " + (autor != null ? autor.getNombre() : "Sin autor") + '\n' +
                "********************************";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idioma) {
        this.idiomas = idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
