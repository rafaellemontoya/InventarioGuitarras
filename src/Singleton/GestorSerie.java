/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import java.io.Serializable;

/**
 *
 * @author Rafa
 */
public class GestorSerie implements Serializable{
    private static GestorSerie serie;
    private static long contador;
    
    private GestorSerie (){
                
    }
    public static GestorSerie getInstance(){
        if(serie == null){
            serie = new GestorSerie();
         }
        
        return serie;
    }
    
    public long generarSerie(){
        contador++;
        return contador;
    }


    @Override
    public String toString() {
        return String.valueOf(contador); //To change body of generated methods, choose Tools | Templates.
    }
    
     
}
