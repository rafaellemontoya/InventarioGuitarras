/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Scanner;

/**
 *
 * @author Rafa
 */
public class Menu {

    private Scanner scanner;
    private Inventario inventario;
    

    public Menu() {
        scanner = new Scanner(System.in);
       
    }



    
   

    

    public void buscarGuitarra() {
        System.out.println("\n ¿Por cuántos parámetros deseas buscar?");
        float numParams = Float.valueOf(scanner.nextLine());

    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

}
