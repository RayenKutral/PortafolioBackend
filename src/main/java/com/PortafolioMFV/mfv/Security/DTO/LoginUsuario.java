
package com.PortafolioMFV.mfv.Security.DTO;

import jakarta.validation.constraints.NotBlank;


public class LoginUsuario {
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;

    
    //getters and setters
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
    
    
}
