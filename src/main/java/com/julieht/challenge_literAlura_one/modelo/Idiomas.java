package com.julieht.challenge_literAlura_one.modelo;

public enum Idiomas {
    INGLES("en", "Inglés"),
    ESPANOL("es", "Español"),
    FRANCES("fr", "Francés"),
    PORTUGUES("pt", "Portugués");

    private String idiomaGutendex;
    private String idiomaEspanol;

    Idiomas (String idiomaGutendex, String idiomaEspanol){
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaEspanol = idiomaEspanol;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.idiomaGutendex.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Ningún idioma fue encontrado: " + text);
    }

    public static Idiomas fromEspanol(String text) {
        for (Idiomas categoria : Idiomas.values()) {
            if (categoria.idiomaEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ningún idioma fue encontrado: " + text);
    }
}
