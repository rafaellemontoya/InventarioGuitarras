/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Madera;

/**
 *
 * @author Rafa
 */
public class SqlMadera {
    
private SqlConn conn;
    public SqlMadera(){
        try {
            conn = new SqlConn();
        } catch (IOException ex) {
            Logger.getLogger(SqlMadera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Madera add(Madera madera){
        Madera nuevoMadera = new Madera("","");
        try {
            
            
            conn.connect();
            String query = "INSERT INTO maderas VALUES(0,?,?)";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            statement.setString(1, madera.getNombre());
            statement.setString(2, "" + madera.getClave());
            int inserted = statement.executeUpdate();
            if (inserted > 0) {
                String querySelect = "SELECT * FROM maderas Order by id desc";
                ResultSet result = statement.executeQuery(querySelect);

                if (result.next()) {

                    nuevoMadera.setId(result.getLong("id"));
                    nuevoMadera.setNombre(result.getString("nombre"));
                    nuevoMadera.setClave(result.getString("clave"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMadera.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            conn.disconnect();
        }
        return nuevoMadera;
                
    }
    
    
    public ArrayList<Madera> getMaderas(){
        ArrayList<Madera> maderas = new ArrayList<Madera>();
        try{
            conn.connect();
            maderas = new ArrayList<Madera>();
            

            String query = "SELECT * FROM maderas";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Madera madera = new Madera();
                madera.setId(result.getLong("id"));
                madera.setNombre(result.getString("nombre"));
                madera.setClave(result.getString("clave"));
                
                
                maderas.add(madera);
                //TODO: Recuperar el registro de la tabla partido para hacer un objeto Party
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMadera.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return maderas;

    }
    public ArrayList<Madera> getMaderasNombre(Madera maderaParam){
        ArrayList<Madera> maderas = new ArrayList<Madera>();
        try{
            conn.connect();
            maderas = new ArrayList<Madera>();
            

            String query = "SELECT * FROM maderas WHERE nombre LIKE '"+maderaParam.getNombre()+"%'";
            CallableStatement statement = (CallableStatement)
            conn.getConn().prepareCall(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Madera madera = new Madera();
                madera.setId(result.getLong("id"));
                madera.setNombre(result.getString("nombre"));
                madera.setClave(result.getString("clave"));
                
                
                maderas.add(madera);
                //TODO: Recuperar el registro de la tabla partido para hacer un objeto Party
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMadera.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return maderas;

    }
    
    
    public Madera getMadera (Madera madera){
        Madera maderaObtenido = null;
        try {
            conn.connect();
            
            String query = "SELECT * FROM maderas WHERE id =" + madera.getId();
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                maderaObtenido = new Madera("","");
                maderaObtenido.setId(result.getLong("id"));
                maderaObtenido.setNombre(result.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMadera.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return maderaObtenido;
    }
    
    public int eliminarMadera(Madera madera) {
        int estado = -1;
        try {
            conn.connect();
            String query = "DELETE FROM maderas WHERE id = ?";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            statement.setLong(1, madera.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlMadera.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }

    public int modificarMadera(Madera madera) {
        int estado = -1;
        try {
            conn.connect();
            String query = "UPDATE maderas SET nombre= ?, clave=? WHERE id = ?";
           CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            statement.setString(1, madera.getNombre());
            statement.setString(2, madera.getClave());
            statement.setLong(3, madera.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlMadera.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }
    
    
}

