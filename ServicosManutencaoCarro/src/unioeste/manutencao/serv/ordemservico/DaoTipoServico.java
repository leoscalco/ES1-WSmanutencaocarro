/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.ordemservico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.manutencao.bo.ordemServico.TipoServico;

/**
 *
 * @author leoscalco
 */
public class DaoTipoServico {
    
    private Connection connection;
    
    public DaoTipoServico(Connection connection) throws SQLException{
        this.connection = connection;
    }
       
    public void save(TipoServico tipo) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into tipo_servico("
                + "nome_servico, tempo_ref, valor_ref)"
                + " values (?, ?, ?)");
        
        
        stmt.setString(1, tipo.getNome());
        stmt.setInt(2, tipo.getTempo());
        stmt.setDouble(3, tipo.getValor());
        
        stmt.execute();
        stmt.close();
                
    }
    
      public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from tipo_servico");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
          
        return rowCount;
    }
      
      public TipoServico servivoByNome(String nome) throws Exception {
         // executa um select
        TipoServico ts = new TipoServico();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from tipo_servico where nome_servico ='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               ts.setCodigo(rs.getInt("idservico"));
               ts.setNome(rs.getString("nome_servico"));             

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return ts;
    }
}
