/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Rafa
 */
public class Modelo implements Serializable {
    private String nombre;
    private String clave;
    private String codigoBarras;
    private Long id;

    public Modelo(){
        
    }
    public Modelo(String nombre, String clave, String codigoBarras) {
        this.nombre = nombre;
        this.clave = clave;
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Modelo{" + "nombre=" + nombre + ", clave=" + clave + ", codigoBarras=" + codigoBarras + '}';
    }
    
     
    
}
