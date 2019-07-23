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
public class Fabricante implements Serializable {
    
    private String nombre;
    private String clave;
    private Long id;

    public Fabricante(){
        
    }
    public Fabricante(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
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
        return "Fabricante{" + "nombre=" + nombre + ", clave=" + clave + '}';
    }
    
    
    
}
