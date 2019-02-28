/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Mesa;
import Classes.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoope
 */
public class MesaDAO extends ExecuteSQL{
    
    public MesaDAO(Connection con) {
        super(con);
    }
    
    public List<Mesa> Consulta(){
        String consulta = "select idmesa from mesa where situacao = 'livre'";
            List<Mesa> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Mesa c = new Mesa();
                    c.setId(rs.getInt(1));
                  
                    lista.add(c);
                }return lista;
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
           // Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public List<Mesa> ConsultaMesa(){
        String consulta = "select idmesa from mesa";
            List<Mesa> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Mesa c = new Mesa();
                    c.setId(rs.getInt(1));
                  
                    lista.add(c);
                }return lista;
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
           // Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public boolean Alterar(Mesa m){
        try {
            String consulta = "update mesa set situacao = 'ocupada' where idmesa = '"+m.getId()+"' "; 
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
            if(ps.executeUpdate()> 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    public boolean Alterar2(int m){
        try {
            String consulta = "update mesa set situacao = 'livre' where idmesa = '"+m+"' "; 
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
            if(ps.executeUpdate()> 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
}
