/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.endereco;

import unioeste.geral.bo.endereco.Bairro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leoscalco
 */
public class DaoBairro {
    
     
    private Connection connection;
    
    public DaoBairro(Connection connection) throws SQLException{
        this.connection = connection;
    }
       
    public void save(Bairro bairro) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into bairro(nome_bairro)"
                + " values (?)");
        stmt.setString(1,bairro.getNome());
        /*stmt.setInt(3,getRuaRows());
        stmt.setInt(4,getBairroRows());
        stmt.setInt(5,getCidadeRows());*/
        
        stmt.execute();
        stmt.close();
                
    }
    
    public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from bairro");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
          
        return rowCount;
    }

    public List<Bairro> list() throws SQLException {
        // executa um select
        List<Bairro> bairros = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from bairro ")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {  
                                     
               Bairro bairro = new Bairro();
               bairro.setCodigo(rs.getInt("idbairro"));
               bairro.setNome(rs.getString("nome_bairro"));
               bairros.add(bairro);
            }
        }
        return bairros;
    }

    public Bairro bairroByCodigo(int id) throws Exception {
         // executa um select
        Bairro bairro = new Bairro();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from bairro where idbairro='" + id +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               bairro.setCodigo(rs.getInt("idbairro"));
               bairro.setNome(rs.getString("nome_bairro"));             

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return bairro;
    }
    
    public Bairro bairroByNome(String nome) throws Exception {
         // executa um select
        Bairro bairro = new Bairro();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from bairro where nome_bairro='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               bairro.setCodigo(rs.getInt("idbairro"));
               bairro.setNome(rs.getString("nome_bairro"));             

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return bairro;
    }
    
}
