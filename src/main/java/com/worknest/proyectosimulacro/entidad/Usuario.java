/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idusuario")
    private Long idusuario;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "contrasena")
    private String contrasena;
    
    @Column(name = "psecreta")
    private String psecreta;
    
    @Column(name = "rsecreta")
    private String rsecreta;

    
    /**
     * 
     * Métodos set y get de las propiedades de la tabla
     * usuario
     */
    
    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPsecreta() {
        return psecreta;
    }

    public void setPsecreta(String psecreta) {
        this.psecreta = psecreta;
    }

    public String getRsecreta() {
        return rsecreta;
    }

    public void setRsecreta(String rsecreta) {
        this.rsecreta = rsecreta;
    }
    
    
}
