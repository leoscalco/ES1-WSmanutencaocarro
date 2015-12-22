/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.endereco;

import unioeste.geral.bo.endereco.TipoLogradouro;
import unioeste.manutencao.infra.configuracao.ConexaoMySQL;
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
public class DaoTipoLogradouro {
    
     
    private Connection connection;  
      
    public DaoTipoLogradouro(Connection connection) throws SQLException{  
        this.connection = connection;
    }  
    
    public String status(){
        return ConexaoMySQL.status;
    } 
      
    public void save(TipoLogradouro ma) throws SQLException, Exception{  
        try(PreparedStatement stmt = this.connection.prepareStatement("insert into "
                + "tipo_logradouro(nome_tipo_logradouro) values(?)")){

            stmt.setString(1, ma.getNome());            
            
            stmt.execute();  
            stmt.close();     
        }catch (Exception e){
            throw new Exception("exception:" + e.toString());
        } 
        
    }
    
     public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from tipo_logradouro");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
        
        return rowCount;
    }
    
         /**
     *
     * @return
     * @throws SQLException
     */
    public List<TipoLogradouro> list() throws SQLException, Exception{
        // executa um select
        List<TipoLogradouro> tipos = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from tipo_logradouro")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        
               TipoLogradouro ma = new TipoLogradouro();
               ma.setCodigo(rs.getInt("idtipo_logradouro"));
               ma.setNome(rs.getString("nome_tipo_logradouro"));
      
               tipos.add(ma);
            }
        } catch (Exception e){
            throw new Exception(e.toString());
        } 
        return tipos;
    }

    public TipoLogradouro tipoLogradouroByCodigo(int id) throws Exception {
         // executa um select
        TipoLogradouro tpL = new TipoLogradouro();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from tipo_logradouro where idtipo_logradouro='" + id +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               tpL.setCodigo(rs.getInt("idtipo_logradouro"));
               tpL.setNome(rs.getString("nome_tipo_logradouro"));             

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return tpL;
    }
    
    public TipoLogradouro tipoLogradouroByNome(String nome) throws Exception {
         // executa um select
        TipoLogradouro tpL = new TipoLogradouro();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from tipo_logradouro where nome_tipo_logradouro='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               tpL.setCodigo(rs.getInt("idtipo_logradouro"));
               tpL.setNome(rs.getString("nome_tipo_logradouro"));             

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return tpL;
    }
}
