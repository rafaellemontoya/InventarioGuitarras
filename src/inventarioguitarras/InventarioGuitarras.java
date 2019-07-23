/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventarioguitarras;

import Controlador.Inventario;
import Controlador.Menu;
import View.UIPrincipal;

/**
 *
 * @author Rafa
 */
public class InventarioGuitarras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Menu menu = new Menu();
        
        Inventario inventario = menu.getInventario();
        if (inventario != null){
            inventario.getAlGuitarras();
        }else{
            inventario = new Inventario();
        }

        
        
        
        UIPrincipal threadJob = new UIPrincipal(inventario);
            Thread myThread = new Thread(threadJob);
            myThread.start();
    }
    
}
