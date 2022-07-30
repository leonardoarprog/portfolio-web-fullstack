package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.Persona;

@Repository
public interface PersonaRepositorio extends CrudRepository<Persona, Integer> {
    
    Optional<Persona> findById(Integer id);

    List<Persona> findAll();

    Optional<Persona> findByNombre(String nombre);

    Optional<Persona> findByApellido(String apellido);

    boolean existsById(Integer id);

    @Modifying
    @Query("update Persona u set u.fotoPerfil = :fotoPerfil where u.id = :id")
    void updateFotoPerfil(@Param(value = "id") Integer id, @Param(value = "fotoPerfil") byte[] fotoPerfil);

    @Modifying
    @Query("update Persona u set u.imgBg = :imgBg where u.id = :id")
    void updateImgBg(@Param(value = "id") Integer id, @Param(value = "imgBg") byte[] imgBg);

    @Modifying
    @Query("update Persona u set u.sobreMi = :sobreMi where u.id = :id")
    void updateSobreMi(@Param(value = "id") Integer id, @Param(value = "sobreMi") String sobreMi);

   // @Query("select Persona u get u.fotoPerfil = :fotoPerfil where u.id = :id")
    @Query(value="select foto_perfil from Persona u where u.id =:id", nativeQuery=true)
    Optional<byte[]> selectFotoPerfil(@Param(value = "id") Integer id);

    //@Query("select Persona u get u.imgBg = u.imgBg where u.id = :id")
    @Query(value="select img_bg from Persona u where u.id =:id", nativeQuery=true)
    Optional<byte[]> selectImgBg(@Param(value = "id") Integer id);

    
    //@Query("select Persona u get u.imgBg = u.imgBg where u.id = :id")
    @Query(value="select sobre_mi from Persona u where u.id =:id", nativeQuery=true)
    Optional<String> selectSobreMi(@Param(value = "id") Integer id);

     //@Query("select Persona u get u.imgBg = u.imgBg where u.id = :id")
 
    @Modifying
    @Query("update Persona u set u.sobreMi = null where u.id = :id")
    void deleteSobreMi(@Param(value = "id") Integer id);


}