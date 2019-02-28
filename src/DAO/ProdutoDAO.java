/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class ProdutoDAO extends ExecuteSQL{
    
    public ProdutoDAO(Connection con) {
        super(con);
    }
    //Compra Cliente
    public boolean Confere(Produto p){
        
        try {
            String consulta = "select idproduto,porcao,valor,categoria,estoque from produto where idproduto = '"+p.getId()+"'";
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while(rs != null){
                if(rs.next()){
                    p.setPorcao(rs.getString(2));
                    p.setValor(rs.getString(3));
                    p.setCategoria(rs.getString(4));
                    p.setEstoque(rs.getString(5));
                    return true;
                    
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    public void Pegar(Produto p){
        
        try {
            String consulta = "select porcao,valor,categoria,estoque from produto where idproduto = '"+p.getId()+"'";

            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while(rs != null){
                if(rs.next()){
                    p.setPorcao(rs.getString(1));
                    p.setValor(rs.getString(2));
                    p.setCategoria(rs.getString(3));
                    p.setEstoque(rs.getString(4));               
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
    }
    
    
    public String Cadastro (Produto c){
        try {
                //String resulta = "Cadsatro";
                String consulta = "insert into produto values (0,?,?,?,?,?)";
                PreparedStatement ps = getCon().prepareStatement(consulta);
                
                ps.setString(1, c.getNome());
                ps.setString(2, c.getPorcao());
                ps.setString(3, c.getValor());
                ps.setString(4, c.getCategoria());
                ps.setString(5, c.getEstoque());
                
                
                if(ps.executeUpdate() > 0){
                  return "Cadastrado!";
                }
            
        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "ERRO!"+ex.getMessage();
        }
    return null;
    }
    
    public List<Produto> Consulta(){
        String consulta = "select idproduto,nome,porcao,valor,categoria,estoque from produto";
            List<Produto> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Produto c = new Produto();
                    c.setId(rs.getInt(1));
                    c.setNome(rs.getString(2));
                    c.setPorcao(rs.getString(3));
                    c.setValor(rs.getString(4));
                    c.setCategoria(rs.getString(5));
                    c.setEstoque(rs.getString(6));
                    
                    
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
    
    
    public List<Produto> ConsultaPro(){
        String consulta = "select nome,estoque,valor,idproduto from produto";
            List<Produto> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    if(Integer.parseInt(rs.getString(2)) >= 5){
                        Produto c = new Produto();
                        c.setNome(rs.getString(1));                 
                        c.setEstoque(rs.getString(2));
                        c.setValor(rs.getString(3));
                        c.setId(rs.getInt(4));
                        lista.add(c);
                    }
                    
                    
                }return lista;
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
           // Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    
    public List<Produto> PreAlterar(Produto p){
        String consulta = "select idproduto,porcao,valor,categoria,estoque from produto where idproduto = '"+p.getId()+"' ";
            List<Produto> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Produto c = new Produto();
                    c.setId(rs.getInt(1));
                    c.setPorcao(rs.getString(2));
                    c.setValor(rs.getString(3));
                    c.setCategoria(rs.getString(4));
                    c.setEstoque(rs.getString(5));
                    
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
    
    public String Alterar(Produto pro){
        try {
            String consulta = "update produto set porcao = '"+pro.getPorcao()+"', "
                    + "valor = '"+pro.getValor()+"', "
                    + "categoria = '"+pro.getCategoria()+"', "
                    + "estoque = '"+pro.getEstoque()+"'"
                    + " where idproduto = '"+pro.getId()+"'";
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
            if(ps.executeUpdate()> 0){
                return "Alterado!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
        return null;
    }
    
    public String Deletar(Produto p){
        try {
            String consulta = "delete from produto where idproduto = ?";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ps.setInt(1, p.getId());
            
            if(ps.executeUpdate()>0){
                return "Deletado!";
            }else{
                return "ERRO!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
        
    }
    
    
    
    
}
