package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.PersonaHabilidad;

@Repository
public interface PersonaHabilidadRepositorio extends CrudRepository<PersonaHabilidad, Integer> {
    Optional<PersonaHabilidad> findById(Integer id);

    Set<PersonaHabilidad> findAll();

    boolean existsById(Integer id);

    List<PersonaHabilidad> findByPersonaId(@Param("id") Integer id);

    @Query(value="select score from persona_habilidad u where u.id =:id", nativeQuery=true)
    Optional<Integer> selectHabilidadScore(@Param(value = "id") Integer id);

    @Modifying
    @Query("update PersonaHabilidad u set u.score = :score where u.id = :id")
    void updateHabilidadScore(@Param(value = "id") Integer id, @Param(value = "score") Integer score);

}
