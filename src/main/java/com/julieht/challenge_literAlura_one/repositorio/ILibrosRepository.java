package com.julieht.challenge_literAlura_one.repositorio;

import com.julieht.challenge_literAlura_one.modelo.Idiomas;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.julieht.challenge_literAlura_one.modelo.Libros;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ILibrosRepository extends JpaRepository<Libros, Long> {

    //JPA
    Optional<Libros> findByTituloContainingIgnoreCase(String titulo);
    List<Libros> findByIdiomas(Idiomas idiomas);

    //JPQL
    @Query("SELECT l FROM Libros l ORDER BY l.numeroDescargas DESC")
    List<Libros> listarTop10(Pageable pageable);
}
