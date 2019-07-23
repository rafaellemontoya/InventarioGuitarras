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
public class Guitarra implements Serializable {
    private long id;
    private Float precio;
    private Especificacion especificaciones;

    public Guitarra(){
        
    }
    public Guitarra(long id, Float precio, Especificacion especificacion) {
        this.id = id;
        this.precio = precio;
        this.especificaciones = especificacion;
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Especificacion getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(Especificacion especificaciones) {
        this.especificaciones = especificaciones;
    }

    @Override
    public String toString() {
        return "Guitarra{" + "id=" + id + ", precio=" + precio + ", especificaciones=" + especificaciones.toString() + '}';
    }
    
    
}
