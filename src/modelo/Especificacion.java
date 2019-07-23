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
public class Especificacion implements Serializable {
    private Fabricante fabricante;
    private Madera madera;
    private Modelo modelo;
    private Tipo tipo;

    public Especificacion(Fabricante fabricante, Madera madera, Modelo modelo, Tipo tipo) {
        this.fabricante = fabricante;
        this.madera = madera;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Madera getMadera() {
        return madera;
    }

    public void setMadera(Madera madera) {
        this.madera = madera;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Especificacion{" + "fabricante=" + fabricante.toString() + ", madera=" + madera.toString() + ", modelo=" + modelo.toString() + ", tipo=" + tipo.toString() + '}';
    }
    
    
    
}
