/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Modelo;

/**
 *
 * @author Rafa
 */
public class SqlModelo {
    
private SqlConn conn;
    public SqlModelo(){
        try {
            conn = new SqlConn();
        } catch (IOException ex) {
            Logger.getLogger(SqlModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Modelo add(Modelo modelo){
        Modelo nuevoModelo = new Modelo("","","");
        try {
            
            
            conn.connect();
            String query = "INSERT INTO Modelos VALUES(0,?,?)";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareStatement(query);
            statement.setString(1, modelo.getNombre());
            statement.setString(2, "" + modelo.getClave());
            int inserted = statement.executeUpdate();
            if (inserted > 0) {
                String querySelect = "SELECT * FROM modelos Order by id desc";
                ResultSet result = statement.executeQuery(querySelect);

                if (result.next()) {

                    nuevoModelo.setId(result.getLong("id"));
                    nuevoModelo.setNombre(result.getString("nombre"));
                    nuevoModelo.setClave(result.getString("clave"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            conn.disconnect();
        }
        return nuevoModelo;
                
    }
    
    
    public ArrayList<Modelo> getModelos(){
        ArrayList<Modelo> modelos = new ArrayList<Modelo>();
        try{
            conn.connect();
            modelos = new ArrayList<Modelo>();
            Statement statement = (Statement) conn.getConn().createStatement();

            String query = "SELECT * FROM modelos";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Modelo modelo = new Modelo("","","");
                modelo.setId(result.getLong("id"));
                modelo.setNombre(result.getString("nombre"));
                modelo.setClave(result.getString("clave"));
                
                modelos.add(modelo);
                //TODO: Recuperar el registro de la tabla partido para hacer un objeto Party
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlModelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return modelos;

    }
    
    public Modelo getModelo (Modelo modelo){
        Modelo modeloObtenido = null;
        try {
            conn.connect();
            Statement statement = (Statement) conn.getConn().createStatement();
            String query = "SELECT * FROM modelos WHERE id =" + modelo.getId();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                modeloObtenido = new Modelo("","","");
                modeloObtenido.setId(result.getLong("id"));
                modeloObtenido.setNombre(result.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlModelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return modeloObtenido;
    }
    
    public int eliminarModelo(Modelo modelo) {
        int estado = -1;
        try {
            conn.connect();
            String query = "DELETE FROM modelos WHERE id = ?";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareStatement(query);
            statement.setLong(1, modelo.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlModelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }

    public int modificarModelo(Modelo modelo) {
        int estado = -1;
        try {
            conn.connect();
            String query = "UPDATE modelos SET nombre= ?, clave=? WHERE id = ?";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareCall(query);
            statement.setString(1, modelo.getNombre());
            statement.setString(2, modelo.getClave());
            statement.setLong(3, modelo.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlModelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }
    
    
}

