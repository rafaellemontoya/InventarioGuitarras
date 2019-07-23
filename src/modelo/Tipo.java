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
public class Tipo implements Serializable {
    private String nombre;
    private String clave;
    private String descripcion;
    private Long id;

    public Tipo(){
        
    }
    public Tipo(String nombre, String clave, String descripcion) {
        this.nombre = nombre;
        this.clave = clave;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Tipo{" + "nombre=" + nombre + ", clave=" + clave + ", descripcion=" + descripcion + '}';
    }
    
    
}
