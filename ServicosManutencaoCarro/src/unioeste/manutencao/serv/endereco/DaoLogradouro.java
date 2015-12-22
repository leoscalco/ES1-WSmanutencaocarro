/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.endereco;

import unioeste.geral.bo.endereco.Logradouro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.bo.endereco.TipoLogradouro;

/**
 *
 * @author leoscalco
 */
public class DaoLogradouro {
    
    private Connection connection;
    
    public DaoLogradouro(Connection connection) throws SQLException{
        this.connection = connection;
    }
    
    
    public void save(Logradouro rua) throws SQLException, Exception{
        PreparedStatement stmt = this.connection.prepareStatement("insert into logradouro(tipo_logradouro_idtipo_logradouro, nome_logradouro)"
                + " values (?, ?)");
        stmt.setInt(1, rua.getTipo().getCodigo());
        stmt.setString(2,rua.getNome());
        /*stmt.setInt(3,getRuaRows());
        stmt.setInt(4,getBairroRows());
        stmt.setInt(5,getCidadeRows());*/
        
        stmt.execute();
        stmt.close();
                 
    }
    
    public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from logradouro");
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
    public List<Logradouro> list() throws SQLException, Exception{
        // executa um select
        List<Logradouro> tipos = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");    
               DaoTipoLogradouro daoTL = new DaoTipoLogradouro(connection);
               
               Logradouro ma = new Logradouro();
               ma.setCodigo(rs.getInt("idlogradouro"));
               ma.setNome(rs.getString("nome_logradouro"));
               ma.setTipo(daoTL.tipoLogradouroByCodigo(rs.getInt("tipo_logradouro_idtipo_logradouro")));
      
               tipos.add(ma);
            }
        } catch (Exception e){
            throw new Exception(e.toString());
        } 
        return tipos;
    }
    
     public Logradouro logradouroByCodigo(int id) throws Exception {
         // executa um select
        Logradouro ma = new Logradouro();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro where idlogradouro='" + id +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               DaoTipoLogradouro daoTL = new DaoTipoLogradouro(connection);
               
               ma.setCodigo(rs.getInt("idlogradouro"));
               ma.setNome(rs.getString("nome_logradouro"));
               ma.setTipo(daoTL.tipoLogradouroByCodigo(rs.getInt("tipo_logradouro_idtipo_logradouro")));           

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return ma;
    }
     
    public Logradouro logradouroByNome(String nome) throws Exception {
         // executa um select
        Logradouro ma = new Logradouro();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro where nome_logradouro='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               DaoTipoLogradouro daoTL = new DaoTipoLogradouro(connection);
               
               ma.setCodigo(rs.getInt("idlogradouro"));
               ma.setNome(rs.getString("nome_logradouro"));
               ma.setTipo(daoTL.tipoLogradouroByCodigo(rs.getInt("tipo_logradouro_idtipo_logradouro")));           

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return ma;
    }
    
}
