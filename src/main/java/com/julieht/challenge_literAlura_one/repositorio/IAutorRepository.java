package com.julieht.challenge_literAlura_one.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.julieht.challenge_literAlura_one.modelo.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

    //JPA
    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    //JPQL
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :fecha AND a.fechaFallecimiento > :fecha")
    List<Autor> buscarAutoresVivosPorElAnio(int fecha);
}
