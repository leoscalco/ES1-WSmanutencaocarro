/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.endereco;

import unioeste.geral.bo.endereco.Uf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.bo.endereco.Bairro;

/**
 *
 * @author leoscalco
 */
public class DaoUF {
     
    private Connection connection;
    
    public DaoUF(Connection connection) throws SQLException{
        this.connection = connection;
    }
    
    
    public void save(Uf uf) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into uf(nome_uf, sigla_uf)"
                + " values (?,?)");
        stmt.setString(1,uf.getNome());
        stmt.setString(2,uf.getSiglaUf());
        /*stmt.setInt(3,getRuaRows());
        stmt.setInt(4,getBairroRows());
        stmt.setInt(5,getCidadeRows());*/
        
        stmt.execute();
        stmt.close();
                 
    }
    
    public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from uf");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
          
        return rowCount;
    }
    
     public List<Uf> list() throws SQLException {
        // executa um select
        List<Uf> ufs = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from uf ")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {  
                                     
               Uf uf = new Uf();
               uf.setCodigo(rs.getInt("iduf"));
               uf.setNome(rs.getString("nome_uf"));
               uf.setSiglaUf(rs.getString("sigla_uf"));
               ufs.add(uf);
            }
        }
        return ufs;
    }

    public Uf ufByCodigo(int id) throws Exception {
         // executa um select
        Uf uf = new Uf();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from uf where iduf='" + id +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               uf.setCodigo(rs.getInt("iduf"));
               uf.setNome(rs.getString("nome_uf"));         
               uf.setSiglaUf(rs.getString("sigla_uf"));

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return uf;
    }
    
     public Uf ufByNome(String nome) throws Exception {
         // executa um select
        Uf uf = new Uf();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from uf where nome_uf='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               uf.setCodigo(rs.getInt("iduf"));
               uf.setNome(rs.getString("nome_uf"));         
               uf.setSiglaUf(rs.getString("sigla_uf"));

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return uf;
    }
    
}
