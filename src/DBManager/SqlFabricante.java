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
import modelo.Fabricante;

/**
 *
 * @author Rafa
 */
public class SqlFabricante {
    private SqlConn conn;
    public SqlFabricante(){
        try {
            conn = new SqlConn();
        } catch (IOException ex) {
            Logger.getLogger(SqlFabricante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Fabricante add(Fabricante fabricante){
        Fabricante nuevoFabricante = new Fabricante("","");
        try {
            
            
            conn.connect();
            String query = "INSERT INTO Fabricantes VALUES(0,?,?)";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareStatement(query);
            statement.setString(1, fabricante.getNombre());
            statement.setString(2, "" + fabricante.getClave());
            int inserted = statement.executeUpdate();
            if (inserted > 0) {
                String querySelect = "SELECT * FROM fabricantes Order by id desc";
                ResultSet result = statement.executeQuery(querySelect);

                if (result.next()) {

                    nuevoFabricante.setId(result.getLong("id"));
                    nuevoFabricante.setNombre(result.getString("nombre"));
                    nuevoFabricante.setClave(result.getString("clave"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlFabricante.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            conn.disconnect();
        }
        return nuevoFabricante;
                
    }
    
    
    public ArrayList<Fabricante> getFabricantes(){
        ArrayList<Fabricante> fabricantes = new ArrayList<Fabricante>();
        try{
            conn.connect();
            fabricantes = new ArrayList<Fabricante>();
            Statement statement = (Statement) conn.getConn().createStatement();

            String query = "SELECT * FROM fabricantes";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Fabricante fabricante = new Fabricante("","");
                fabricante.setId(result.getLong("id"));
                fabricante.setNombre(result.getString("nombre"));
                fabricante.setClave(result.getString("clave"));
                
                fabricantes.add(fabricante);
                //TODO: Recuperar el registro de la tabla partido para hacer un objeto Party
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlFabricante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return fabricantes;

    }
    
    public Fabricante getFabricante (Fabricante fabricante){
        Fabricante fabricanteObtenido = null;
        try {
            conn.connect();
            Statement statement = (Statement) conn.getConn().createStatement();
            String query = "SELECT * FROM fabricantes WHERE id =" + fabricante.getId();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                fabricanteObtenido = new Fabricante("","");
                fabricanteObtenido.setId(result.getLong("id"));
                fabricanteObtenido.setNombre(result.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlFabricante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return fabricanteObtenido;
    }
    
    public int eliminarFabricante(Fabricante fabricante) {
        int estado = -1;
        try {
            conn.connect();
            String query = "DELETE FROM fabricantes WHERE id = ?";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareStatement(query);
            statement.setLong(1, fabricante.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlFabricante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }

    public int modificarFabricante(Fabricante fabricante) {
        int estado = -1;
        try {
            conn.connect();
            String query = "UPDATE fabricantes SET nombre= ?, clave=? WHERE id = ?";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareCall(query);
            statement.setString(1, fabricante.getNombre());
            statement.setString(2, fabricante.getClave());
            statement.setLong(3, fabricante.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlFabricante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }
    
    
}
