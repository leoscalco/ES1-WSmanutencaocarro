/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.bo.veiculo.Marca;

/**
 *
 * @author leoscalco
 */
public class DaoMarca {
    private Connection connection;
    
    public DaoMarca(Connection connection) throws SQLException{
        this.connection = connection;
    }
       
    public void save(Marca marca) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into marca("
                + "nome_marca)"
                + " values (?)");
        
        
        stmt.setString(1, marca.getNome());
        
        stmt.execute();
        stmt.close();
                
    }
    
      public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from marca");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
          
        return rowCount;
    }
      
      public Marca marcaByNome(String nome) throws Exception {
         // executa um select
        Marca marca = new Marca();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from marca where nome_marca='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               marca.setCodigo(rs.getInt("idmarca"));
               marca.setNome(rs.getString("nome_marca"));             

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return marca;
    }
}
