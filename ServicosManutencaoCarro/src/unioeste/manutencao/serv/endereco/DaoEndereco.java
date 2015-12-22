/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.endereco;

/**
 *
 * @author William
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.bo.endereco.*;


public class DaoEndereco {
    
    private Connection connection;
    
    public DaoEndereco(Connection conn) throws SQLException{
        this.connection = conn;
    }
    
    
    public void save(Endereco end) throws SQLException, Exception{
        PreparedStatement stmt = this.connection.prepareStatement("insert into endereco(logradouro_idlogradouro, bairro_idbairro, cidade_idcidade,cep)"
                + " values (?,?,?,?)");
        

        stmt.setInt(1, end.getRua().getCodigo());
        stmt.setInt(2, end.getBairro().getCodigo());
        stmt.setInt(3, end.getCidade().getCodigo());
        stmt.setString(4,end.getCep());
        /*stmt.setInt(3,getRuaRows());
        stmt.setInt(4,getBairroRows());
        stmt.setInt(5,getCidadeRows());*/
        
        stmt.execute();
        stmt.close();
         
    }
    
    public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from endereco");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
         
        return rowCount;
    }
    
     public List<Endereco> list() throws SQLException, Exception{
        // executa um select
        List<Endereco> enderecos = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from endereco")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        
               Endereco end = new Endereco();
               DaoLogradouro dl = new DaoLogradouro(connection);
               DaoBairro db = new DaoBairro(connection);
               DaoCidade dc = new DaoCidade(connection);
               
               end.setCodigo(rs.getInt("idendereco"));
               end.setCep(rs.getString("cep"));
               end.setRua(dl.logradouroByCodigo(rs.getInt("logradouro_idlogradouro")));
               end.setBairro(db.bairroByCodigo(rs.getInt("bairro_idbairro")));
               end.setCidade(dc.cidadeByCodigo(rs.getInt("cidade_idcidade")));
      
               enderecos.add(end);
            }
        } catch (Exception e){
            throw new Exception(e.toString());
        } 
        return enderecos;
    }
     
      public Endereco enderecoByCodigo(int id) throws Exception {
         // executa um select
        Endereco end = new Endereco();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from endereco where idendereco='" + id +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               DaoLogradouro dl = new DaoLogradouro(connection);
               DaoBairro db = new DaoBairro(connection);
               DaoCidade dc = new DaoCidade(connection);
               
               end.setCodigo(rs.getInt("idendereco"));
               end.setCep(rs.getString("cep"));
               end.setRua(dl.logradouroByCodigo(rs.getInt("logradouro_idlogradouro")));
               end.setBairro(db.bairroByCodigo(rs.getInt("bairro_idbairro")));
               end.setCidade(dc.cidadeByCodigo(rs.getInt("cidade_idcidade")));
                   

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return end;
    }
      
    public List<Endereco> enderecoByCep(String cep) throws Exception {
         // executa um select
         List<Endereco> enderecos = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from endereco where cep='" + cep +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        
               Endereco end = new Endereco();
               DaoLogradouro dl = new DaoLogradouro(connection);
               DaoBairro db = new DaoBairro(connection);
               DaoCidade dc = new DaoCidade(connection);
               
               end.setCodigo(rs.getInt("idendereco"));
               end.setCep(rs.getString("cep"));
               end.setRua(dl.logradouroByCodigo(rs.getInt("logradouro_idlogradouro")));
               end.setBairro(db.bairroByCodigo(rs.getInt("bairro_idbairro")));
               end.setCidade(dc.cidadeByCodigo(rs.getInt("cidade_idcidade")));
      
               enderecos.add(end);
            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return enderecos;
    }
    
    public void delete(Endereco end) throws Exception {  
 
        try (PreparedStatement stmt = this.connection.prepareStatement("delete * from endereco where idendereco='"+ end.getCodigo() +"'")) {
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            throw new Exception(e.toString());
        }  
        
    } 

}
