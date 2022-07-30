package ar.com.portfolioweb.backendspringboot.seguridad.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class NuevoUsuario {

    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;

    private Set<String> roles;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}