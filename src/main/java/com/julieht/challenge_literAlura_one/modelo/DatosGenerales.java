package com.julieht.challenge_literAlura_one.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGenerales(
        @JsonAlias("results") List<DatosLibros> resultados) {
}
