package DAO;

import Classes.Produto;
import Classes.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO extends ExecuteSQL {
    
    public UsuarioDAO(Connection con) {
        super(con);
    }
    
    public boolean Logar(Usuario fun){
        boolean finalResult = false;
        try{
            String consulta = "select idfuncionario,nome,login,senha,nivel from funcionario "
                    +"where login = '"+fun.getLogin()+"' and senha = '"+fun.getSenha()+"'";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    fun.setId(rs.getInt(1));
                    fun.setNome(rs.getString(2));
                    fun.setLogin(rs.getString(3));
                    fun.setSenha(rs.getString(4));
                    fun.setNivel(rs.getInt(5));
                    
                    
                    finalResult = true;
                }
            }
                    
            
        }catch(SQLException ex){
            ex.getMessage();
        }
        return finalResult;
    }
    
    public String Cadastrar(Usuario u){
        
        try{
            String consulta = "insert into funcionario values (0,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            
                ps.setString(1, u.getNome());
                ps.setString(2,u.getEmail());
                ps.setString(3,u.getTel());
                ps.setString(4,u.getEnd());
                ps.setString(5,u.getCpf());
                ps.setString(6,u.getRg());
                ps.setString(7,u.getLogin());
                ps.setString(8,u.getSenha());
                ps.setString(9,u.getEscola());
                ps.setInt(10,u.getNivel());
                
                if(ps.executeUpdate() > 0){
                 return "Cadastrado!";
                }else{
                    return "Não cadastrado";
                }
        } catch (SQLException ex) {
            return "Não cadastrado: Login já usado por outro funcionário!";//+ex.getMessage();
           // Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Usuario> ConsultaGarcom(){
        String consulta = "select idfuncionario,nome,email,cpf,escolaridade from funcionario where nivel = '3'";
            List<Usuario> lista = new ArrayList<>();
        try {
 
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Usuario c = new Usuario();
                    c.setId(rs.getInt(1));
                    c.setNome(rs.getString(2));
                    c.setEmail(rs.getString(3));
                    c.setCpf(rs.getString(4));
                    c.setEscola(rs.getString(5));
    
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
    
    public List<Usuario> ConsultaGerente(){
        String consulta = "select idfuncionario,nome,email,cpf,escolaridade from funcionario where nivel = '2'";
            List<Usuario> lista = new ArrayList<>();
        try {
 
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Usuario c = new Usuario();
                    c.setId(rs.getInt(1));
                    c.setNome(rs.getString(2));
                    c.setEmail(rs.getString(3));
                    c.setCpf(rs.getString(4));
                    c.setEscola(rs.getString(5));
    
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
    
    public List<Usuario> ConsultaCompleta(int id){
        String consulta = "select nome,email,tel,endereco,cpf,rg,login,senha,escolaridade from funcionario where idfuncionario = '"+id+"'";
            List<Usuario> lista = new ArrayList<>();
        try {
 
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()) {                    
                    Usuario c = new Usuario();
                    c.setNome(rs.getString(1));
                    c.setEmail(rs.getString(2));
                    c.setTel(rs.getString(3));
                    c.setEnd(rs.getString(4));
                    c.setCpf(rs.getString(5));
                    c.setRg(rs.getString(6));
                    c.setLogin(rs.getString(7));
                    c.setSenha(rs.getString(8));
                    c.setEscola(rs.getString(9));
    
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
    
    public String Deletar(Usuario p){
        try {
            String consulta = "delete from funcionario where idfuncionario = ?";
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
    
    public String Alterar(Usuario pro){
        try {
            String consulta = "update funcionario set nome = '"+pro.getNome()+"', "
                    + "email = '"+pro.getEmail()+"', "
                    + "tel = '"+pro.getTel()+"', "
                    + "endereco = '"+pro.getEnd()+"', "
                    + "cpf = '"+pro.getCpf()+"', "
                    + "rg = '"+pro.getRg()+"', "
                    + "login = '"+pro.getLogin()+"', "
                    + "senha = '"+pro.getSenha()+"', "
                    + "escolaridade = '"+pro.getEscola()+"', "
                    + "nivel = '"+pro.getNivel()+"'"
                    + " where idfuncionario = '"+pro.getId()+"'";
            
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
    
}
