/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Conta;
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
public class ContaDAO extends ExecuteSQL{
    
    public ContaDAO(Connection con) {
        super(con);
    }
    
    public boolean Cadastro(Conta c){
        try {
                //String resulta = "Cadsatro";
                String consulta = "insert into conta values (0,?,?,?,?,?,?)";
                PreparedStatement ps = getCon().prepareStatement(consulta);
                
                ps.setInt(1, c.getIdfuncionario());
                ps.setInt(2, c.getIdmesa());
                ps.setString(3, c.getData());
                ps.setString(4, c.getHora());
                ps.setString(5, c.getTotal());
                ps.setString(6, c.getSituacao());
                
                
                if(ps.executeUpdate() > 0){
                  return true;
                }
            
        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    public int idConta(Conta f){
        try {
            String consulta2 = "select LAST_INSERT_ID()";
            PreparedStatement ps2 = getCon().prepareStatement(consulta2);
            ResultSet rs = ps2.executeQuery();
            
            while (rs != null) {
                if(rs.next()){
                    f.setId(rs.getInt(1));
                    //System.out.println("IdFatura = "+f.getId());
                    return f.getId();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Conta> Consulta(){
        String consulta = "select idconta,idfuncionario,idmesa,Total,situacao from conta where situacao = 'Aberta'";
            List<Conta> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Conta c = new Conta();
                    c.setId(rs.getInt(1));
                    c.setIdfuncionario(rs.getInt(2));
                    c.setIdmesa(rs.getInt(3));
                    c.setTotal(rs.getString(4));
                    c.setSituacao(rs.getString(5));
                    
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
    
    public List<Conta> ConsultaVendas(int id){
        String consulta = "select idconta,idfuncionario,idmesa,data,hora,Total,situacao from conta where idmesa = '"+id+"'";
            List<Conta> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Conta c = new Conta();
                    c.setId(rs.getInt(1));
                    c.setIdfuncionario(rs.getInt(2));
                    c.setIdmesa(rs.getInt(3));
                    c.setData(rs.getString(4));
                    c.setHora(rs.getString(5));
                    c.setTotal(rs.getString(6));
                    c.setSituacao(rs.getString(7));
                    
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
    
    public List<Conta> ConsultaVendasAberto(int id){
        String consulta = "select idconta,idfuncionario,idmesa,Total,situacao from conta where idmesa = '"+id+"' and situacao = 'Aberta'";
            List<Conta> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Conta c = new Conta();
                    c.setId(rs.getInt(1));
                    c.setIdfuncionario(rs.getInt(2));
                    c.setIdmesa(rs.getInt(3));
                    c.setTotal(rs.getString(4));
                    c.setSituacao(rs.getString(5));
                    
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
    
    public List<Conta> ConsultaUnica(int id){
        String consulta = "select idconta,idfuncionario,idmesa,data,hora,Total,situacao from conta where idconta = '"+id+"'";
            List<Conta> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Conta c = new Conta();
                    c.setId(rs.getInt(1));
                    c.setIdfuncionario(rs.getInt(2));
                    c.setIdmesa(rs.getInt(3));
                    c.setData(rs.getString(4));
                    c.setHora(rs.getString(5));
                    c.setTotal(rs.getString(6));
                    c.setSituacao(rs.getString(7));
                    
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
    
    public List<Conta> ConsultaFun(int id){
        String consulta = "select idconta,idfuncionario,idmesa,data,hora,Total,situacao from conta where idfuncionario = '"+id+"'";
            List<Conta> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Conta c = new Conta();
                    c.setId(rs.getInt(1));
                    c.setIdfuncionario(rs.getInt(2));
                    c.setIdmesa(rs.getInt(3));
                    c.setData(rs.getString(4));
                    c.setHora(rs.getString(5));
                    c.setTotal(rs.getString(6));
                    c.setSituacao(rs.getString(7));
                    
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
    
    public String Alterar(int id){
        try {
            String consulta = "update conta set situacao = 'Fechada' where idconta = '"+id+"'";
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
            if(ps.executeUpdate()> 0){
                return "Conta Fechada!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
        return null;
    }
    
    public boolean AlterarTotal(Conta c){
        try {
            String consulta = "update conta set Total = '"+c.getTotal()+"' where idconta = '"+c.getId()+"'";
            
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
