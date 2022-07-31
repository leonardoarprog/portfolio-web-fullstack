package ar.com.portfolioweb.backendspringboot.seguridad.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioControlador {

}
