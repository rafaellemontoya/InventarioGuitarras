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
import modelo.Tipo;

/**
 *
 * @author Rafa
 */
public class SqlTipo {
    
private SqlConn conn;
    public SqlTipo(){
        try {
            conn = new SqlConn();
        } catch (IOException ex) {
            Logger.getLogger(SqlTipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Tipo add(Tipo tipo){
        Tipo nuevoTipo = new Tipo("","","");
        try {
            
            
            conn.connect();
            String query = "INSERT INTO Tipos VALUES(0,?,?,?)";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            statement.setString(1, tipo.getNombre());
            statement.setString(2, "" + tipo.getClave());
            statement.setString(3, "" + tipo.getDescripcion());
            int inserted = statement.executeUpdate();
            if (inserted > 0) {
                String querySelect = "SELECT * FROM tipos Order by id desc";
                ResultSet result = statement.executeQuery(querySelect);

                if (result.next()) {

                    nuevoTipo.setId(result.getLong("id"));
                    nuevoTipo.setNombre(result.getString("nombre"));
                    nuevoTipo.setClave(result.getString("clave"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlTipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            conn.disconnect();
        }
        return nuevoTipo;
                
    }
    
    
    public ArrayList<Tipo> getTipos(){
        ArrayList<Tipo> tipos = new ArrayList<Tipo>();
        try{
            conn.connect();
            tipos = new ArrayList<Tipo>();
           

            String query = "SELECT * FROM tipos";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(result.getLong("id"));
                tipo.setNombre(result.getString("nombre"));
                tipo.setClave(result.getString("clave"));
                
                tipos.add(tipo);
                //TODO: Recuperar el registro de la tabla partido para hacer un objeto Party
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlTipo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return tipos;

    }
    
     
    public ArrayList<Tipo> getTiposNombre(Tipo tipoParam){
        ArrayList<Tipo> tipos = new ArrayList<Tipo>();
        try{
            conn.connect();
            tipos = new ArrayList<Tipo>();
           

            String query = "SELECT * FROM tipos WHERE nombre LIKE '"+tipoParam.getNombre()+"%'";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(result.getLong("id"));
                tipo.setNombre(result.getString("nombre"));
                tipo.setClave(result.getString("clave"));
                
                tipos.add(tipo);
                //TODO: Recuperar el registro de la tabla partido para hacer un objeto Party
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlTipo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return tipos;

    }
    
    public Tipo getTipo (Tipo tipo){
        Tipo tipoObtenido = null;
        try {
            conn.connect();
            
            String query = "SELECT * FROM tipos WHERE id =" + tipo.getId();
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                tipoObtenido = new Tipo("","","");
                tipoObtenido.setId(result.getLong("id"));
                tipoObtenido.setNombre(result.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlTipo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return tipoObtenido;
    }
    
    public int eliminarTipo(Tipo tipo) {
        int estado = -1;
        try {
            conn.connect();
            String query = "DELETE FROM tipos WHERE id = ?";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            statement.setLong(1, tipo.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlTipo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }

    public int modificarTipo(Tipo tipo) {
        int estado = -1;
        try {
            conn.connect();
            String query = "UPDATE tipos SET nombre= ?, clave=? WHERE id = ?";
            CallableStatement statement = (CallableStatement)
conn.getConn().prepareCall(query);
            statement.setString(1, tipo.getNombre());
            statement.setString(2, tipo.getClave());
            statement.setLong(3, tipo.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlTipo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }
    
    
}

