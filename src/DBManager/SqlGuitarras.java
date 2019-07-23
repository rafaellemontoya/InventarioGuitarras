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
public class SqlGuitarras {

    private SqlConn conn;
    private SqlFabricante sqlFabricante;
    private SqlMadera sqlMadera;
    private SqlModelo sqlModelo;
    private SqlTipo sqlTipo;

    public SqlGuitarras(){
        sqlFabricante = new SqlFabricante();
        sqlMadera = new SqlMadera();
        sqlModelo = new SqlModelo();
        sqlTipo = new SqlTipo();
        try {
            conn = new SqlConn();
        } catch (IOException ex) {
            Logger.getLogger(SqlGuitarras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Guitarra add(Guitarra guitarra){
        Guitarra nuevoGuitarra = new Guitarra();
        try {
            
            
            conn.connect();
            String query = "INSERT INTO guitarras VALUES(0,?,?,?,?,?)";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareStatement(query);
            statement.setFloat(1, guitarra.getPrecio());
            statement.setLong(2, guitarra.getEspecificaciones().getFabricante().getId());
            statement.setLong(3, guitarra.getEspecificaciones().getMadera().getId());
            statement.setLong(4, guitarra.getEspecificaciones().getModelo().getId());
            statement.setLong(5, guitarra.getEspecificaciones().getTipo().getId());
            int inserted = statement.executeUpdate();
            if (inserted > 0) {
                String querySelect = "SELECT * FROM guitarras Order by id desc";
                ResultSet result = statement.executeQuery(querySelect);

                if (result.next()) {
                    
                    nuevoGuitarra.setId(result.getLong("id"));
                    nuevoGuitarra.setPrecio(result.getFloat("precio"));
                    nuevoGuitarra.setEspecificaciones(guitarra.getEspecificaciones());
                    
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlGuitarras.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            conn.disconnect();
        }
        return nuevoGuitarra;
                
    }
    
    
    public ArrayList<Guitarra> getGuitarras(){
        ArrayList<Guitarra> guitarras = new ArrayList<Guitarra>();
        try{
            conn.connect();
            guitarras = new ArrayList<Guitarra>();
            Statement statement = (Statement) conn.getConn().createStatement();

            String query = "SELECT * FROM guitarras";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Guitarra guitarra = new Guitarra();
                guitarra.setId(result.getLong("id"));
                guitarra.setPrecio(result.getFloat("precio"));
                
                Fabricante f = new Fabricante();
                f.setId(result.getLong("fabricante"));
                
                Madera m = new Madera();
                m.setId(result.getLong("madera"));
                
                Modelo mo = new Modelo();
                mo.setId(result.getLong("modelo"));
                
                Tipo t = new Tipo();
                t.setId(result.getLong("tipo"));
                
                Especificacion e = new Especificacion(sqlFabricante.getFabricante(f),
                        sqlMadera.getMadera(m), sqlModelo.getModelo(mo), sqlTipo.getTipo(t));

                guitarra.setEspecificaciones(e);
                
                guitarras.add(guitarra);
                //TODO: Recuperar el registro de la tabla partido para hacer un objeto Party
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlGuitarras.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return guitarras;

    }
    
    public Guitarra getGuitarra (Guitarra guitarraParam){
        Guitarra guitarra = null;
        try {
            conn.connect();
            Statement statement = (Statement) conn.getConn().createStatement();
            String query = "SELECT * FROM guitarras WHERE id =" + guitarraParam.getId();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                guitarra.setId(result.getLong("id"));
                guitarra.setPrecio(result.getFloat("precio"));
                
                Fabricante f = new Fabricante();
                f.setId(result.getLong("fabricante"));
                
                Madera m = new Madera();
                m.setId(result.getLong("madera"));
                
                Modelo mo = new Modelo();
                mo.setId(result.getLong("modelo"));
                
                Tipo t = new Tipo();
                t.setId(result.getLong("tipo"));
                
                Especificacion e = new Especificacion(sqlFabricante.getFabricante(f),
                        sqlMadera.getMadera(m), sqlModelo.getModelo(mo), sqlTipo.getTipo(t));

                guitarra.setEspecificaciones(e);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlGuitarras.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return guitarra;
    }
    
    public int eliminarGuitarra(Guitarra guitarra) {
        int estado = -1;
        try {
            conn.connect();
            String query = "DELETE FROM guitarras WHERE id = ?";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareStatement(query);
            statement.setLong(1, guitarra.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlGuitarras.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }

    public int modificarGuitarra(Guitarra guitarra) {
        int estado = -1;
        try {
            conn.connect();
            String query = "UPDATE guitarras SET precio= ?, fabricante=?, madera=?, modelo=?, tipo=? WHERE id = ?";
            PreparedStatement statement = (PreparedStatement) conn.getConn().prepareCall(query);
            statement.setFloat(0, guitarra.getPrecio());
            statement.setLong(1, guitarra.getEspecificaciones().getFabricante().getId());
            statement.setLong(2, guitarra.getEspecificaciones().getMadera().getId());
            statement.setLong(3, guitarra.getEspecificaciones().getModelo().getId());
            statement.setLong(4, guitarra.getEspecificaciones().getTipo().getId());
            statement.setLong(5, guitarra.getId());
            estado = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlGuitarras.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.disconnect();
        }
        return estado;
    }
    
    
}


