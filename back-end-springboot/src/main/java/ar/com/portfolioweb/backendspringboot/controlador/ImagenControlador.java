package ar.com.portfolioweb.backendspringboot.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import ar.com.portfolioweb.backendspringboot.dto.Mensaje;
import ar.com.portfolioweb.backendspringboot.modelo.Imagen;
import ar.com.portfolioweb.backendspringboot.servicio.ImagenServicio;
import ar.com.portfolioweb.backendspringboot.utilidad.ImagenUtilidad;

@Controller
@RequestMapping(path = "/api/usuarios")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class ImagenControlador {

        @Autowired
        private ImagenServicio imagenServicio;

     @PostMapping("/upload/imagen")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Mensaje> uplaodImage(@RequestParam("imagen") MultipartFile file)
                        throws IOException {

                imagenServicio.guardarImagen(Imagen.builder()
                                .nombre(file.getOriginalFilename())
                                .tipo(file.getContentType())
                                .imagen(ImagenUtilidad.compressImage(file.getBytes())).build());
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new Mensaje("Imagen subidad satisfactoriamente: " +
                                                file.getOriginalFilename()));
        } 

        @GetMapping(path = { "/get/imagen/info/{nombre}" })
        @PreAuthorize("hasRole('ADMIN')")
        public Imagen getImageDetails(@PathVariable("nombre") String nombre) throws IOException {

                final Optional<Imagen> dbImagen = imagenServicio.obtenerImgPorNombre(nombre);

                return Imagen.builder()
                                .nombre(dbImagen.get().getNombre())
                                .tipo(dbImagen.get().getTipo())
                                .imagen(ImagenUtilidad.decompressImage(dbImagen.get().getImagen())).build();
        }

        @GetMapping(path = { "/get/imagen/{nombre}" })
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<byte[]> getImage(@PathVariable("nombre") String nombre) throws IOException {

                final Optional<Imagen> dbImage = imagenServicio.obtenerImgPorNombre(nombre);

                return ResponseEntity
                                .ok()
                                .contentType(MediaType.valueOf(dbImage.get().getTipo()))
                                .body(ImagenUtilidad.decompressImage(dbImage.get().getImagen()));
        }

}