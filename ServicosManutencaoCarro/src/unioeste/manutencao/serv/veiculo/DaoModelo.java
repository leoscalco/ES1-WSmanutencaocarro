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
import unioeste.geral.bo.veiculo.Modelo;

/**
 *
 * @author leoscalco
 */
public class DaoModelo {
    
    private Connection connection;
    
    public DaoModelo(Connection connection) throws SQLException{
        this.connection = connection;
    }
       
    public void save(Modelo modelo) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into modelo("
                + "nome_modelo, marca_idmarca)"
                + " values (?, ?)");
        
        
        stmt.setString(1, modelo.getNome());
        stmt.setInt(2, modelo.getMarca().getCodigo());
        
        stmt.execute();
        stmt.close();
                
    }
    
      public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from modelo");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
          
        return rowCount;
    }
      
      public Modelo modeloByNome(String nome) throws Exception {
         // executa um select
        Modelo modelo = new Modelo();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from modelo where nome_modelo ='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               modelo.setCodigo(rs.getInt("idmodelo"));
               modelo.setNome(rs.getString("nome_modelo"));             

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return modelo;
    }
    
}
