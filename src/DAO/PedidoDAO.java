/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Conta;
import Classes.Pedido;
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
public class PedidoDAO extends ExecuteSQL{
    
    public PedidoDAO(Connection con) {
        super(con);
    }
    
    
    public boolean Cadastro(Pedido c){
        try {
                //String resulta = "Cadsatro";
                String consulta = "insert into pedido values (0,?,?,?,?)";
                PreparedStatement ps = getCon().prepareStatement(consulta);
                
                ps.setInt(1, c.getIdconta());
                ps.setInt(2, c.getIdproduto());
                ps.setString(3, c.getQtn());
                ps.setString(4, c.getTotal());
                
                if(ps.executeUpdate() > 0){
                  return true;
                }
            
        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    public List<Pedido> ConsultaUnica(int id){
        String consulta = "select idpedido,idproduto,qtd,total from pedido where idconta = '"+id+"'";
            List<Pedido> lista = new ArrayList<>();
        try {
            
            
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Pedido c = new Pedido();
                    c.setId(rs.getInt(1));
                    c.setIdconta(id);
                    c.setIdproduto(rs.getInt(2));
                    c.setQtn(rs.getString(3));
                    c.setTotal(rs.getString(4));
                    
                    
                    
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
    
    public boolean Deletar(int idpedido){
        try {
            String consulta = "delete from pedido where idpedido = ?";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ps.setInt(1, idpedido);
            
            if(ps.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
}
