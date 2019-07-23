/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DBManager.SqlFabricante;
import DBManager.SqlGuitarras;
import DBManager.SqlMadera;
import DBManager.SqlModelo;
import DBManager.SqlTipo;
import java.io.Serializable;
import java.util.ArrayList;
import modelo.Especificacion;
import modelo.Fabricante;
import modelo.Guitarra;
import modelo.Madera;
import modelo.Modelo;
import modelo.Tipo;

/**
 *
 * @author Rafa
 */
public class Inventario  implements Serializable{

    private SqlFabricante sqlFabricante;
    private SqlMadera sqlMadera;
    private SqlModelo sqlModelo;
    private SqlTipo sqlTipo;
    private SqlGuitarras sqlGuitarras;

    
    
    public Inventario(){

        
        sqlFabricante = new SqlFabricante();
        sqlMadera = new SqlMadera();
        sqlModelo = new SqlModelo();
        sqlTipo = new SqlTipo();
        sqlGuitarras = new SqlGuitarras();
        
        createFabricante();
        createMadera();
        createModelos();
        createTipos();
        
    }


    public void createFabricante(){
        Fabricante f = new Fabricante("FENDER", "f");
        Fabricante g = new Fabricante("GIGSON", "g");
        Fabricante y = new Fabricante("YAMAHA", "y");
        Fabricante i = new Fabricante("IBAÑEZ", "i");
        Fabricante m = new Fabricante("MARTIN", "m");
        sqlFabricante.add(f);
        sqlFabricante.add(g);
        sqlFabricante.add(y);
        sqlFabricante.add(i);
        sqlFabricante.add(m);
        
    }
    
    public void createModelos(){
        sqlModelo.add( new Modelo("FOLCK","FO","123"));
        sqlModelo.add( new Modelo("STRATOCASTOR","ST","12345"));
        sqlModelo.add( new Modelo("MENFIS","ME","12345"));
        sqlModelo.add( new Modelo("CONTRY","CO","12345"));
        sqlModelo.add( new Modelo("CLASICA","CL","12345"));
        sqlModelo.add( new Modelo("PORTUGUESA","PO","12345"));
       
    }
    public void createTipos(){
        sqlTipo.add(new Tipo("ACÚSTICA","AC","AC2"));
        sqlTipo.add(new Tipo("ELÉCTRICA","EL","EL54"));
        sqlTipo.add(new Tipo("ELECTRO-ACÚSTICA","EA","EA5"));
    }
    public void createMadera(){
        sqlMadera.add(new Madera("INDIAN_ROSEWOOD","IN"));
        sqlMadera.add(new Madera("BRASILIAN_ROSEWOOD","BR"));
        sqlMadera.add(new Madera("MAHOGANY","MH"));
        sqlMadera.add(new Madera("MAPLE","MP"));
        sqlMadera.add(new Madera("COCOBOLO","CO"));
        sqlMadera.add(new Madera("CEDAR","CE"));
        sqlMadera.add(new Madera("ADIRONDARK","AD"));
        sqlMadera.add(new Madera("ALDER","AL"));
        sqlMadera.add(new Madera("SITKA","SI"));
        
    }

    public ArrayList<Guitarra> getAlGuitarras() {
        return sqlGuitarras.getGuitarras();
    }


    public ArrayList<Fabricante> getAlFabricantes() {
        return sqlFabricante.getFabricantes();
    }

    public ArrayList<Modelo> getAlModelos() {
        return sqlModelo.getModelos();
    }



    public ArrayList<Tipo> getAlTipos() {
        return sqlTipo.getTipos();
    }

 

    public ArrayList<Madera> getAlMadera() {
        return sqlMadera.getMaderas();
    }


    

    public Guitarra crearGuitarra(float precio,Especificacion especificacion){

        return sqlGuitarras.add(new Guitarra(0, precio, especificacion));
    }
    public Fabricante crearFabricante(String nombre, String clave){
        
        return sqlFabricante.add(new Fabricante(nombre, clave));
    }   
    public Madera crearMadera(String nombre, String clave){
        return sqlMadera.add( new Madera(nombre, clave));
    }    
    public Modelo crearModelo(String nombre, String clave,String codigoBarras){
        return sqlModelo.add( new Modelo(nombre, clave, codigoBarras));
    }    
    public Tipo crearTipo(String nombre, String clave, String descripcion){
        return sqlTipo.add( new Tipo(nombre, clave, descripcion));
    } 
    
    public ArrayList<Guitarra> buscarFabricante(Fabricante fabricante){
        ArrayList<Fabricante> fabricantes =sqlFabricante.getFabricantesNombre(fabricante);
        ArrayList<Guitarra> guitarrasObtenidas = new ArrayList<>();
        
        for (Fabricante fabricanteObtenido : fabricantes) {
            guitarrasObtenidas.addAll( sqlGuitarras.buscarGuitarras(fabricanteObtenido) );
        }
        return guitarrasObtenidas;
    }
    public ArrayList<Guitarra> buscarMadera(Madera madera){
        ArrayList<Madera> maderas =sqlMadera.getMaderasNombre(madera);
        ArrayList<Guitarra> guitarrasObtenidas = new ArrayList<>();
        
        for (Madera maderaObtenida : maderas) {
            guitarrasObtenidas.addAll( sqlGuitarras.buscarGuitarras(maderaObtenida) );
        }
        return guitarrasObtenidas;
    }

    
    public ArrayList<Guitarra> buscarModelo(Modelo modelo){
        ArrayList<Modelo> modelos =sqlModelo.getModelosNombre(modelo);
        ArrayList<Guitarra> guitarrasObtenidas = new ArrayList<>();
        
        for (Modelo modeloObtenido : modelos) {
            guitarrasObtenidas.addAll( sqlGuitarras.buscarGuitarras(modeloObtenido) );
        }
        return guitarrasObtenidas;
    }
    
        
    public ArrayList<Guitarra> buscarTipo(Tipo tipo){
        ArrayList<Tipo> tipos =sqlTipo.getTiposNombre(tipo);
        ArrayList<Guitarra> guitarrasObtenidas = new ArrayList<>();
        
        for (Tipo fabricanteObtenido : tipos) {
            guitarrasObtenidas.addAll( sqlGuitarras.buscarGuitarras(fabricanteObtenido) );
        }
        return guitarrasObtenidas;
    }
    
        
}
