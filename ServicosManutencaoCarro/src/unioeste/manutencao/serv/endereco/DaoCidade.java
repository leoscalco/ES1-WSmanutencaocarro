/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.endereco;

import unioeste.geral.bo.endereco.Cidade;
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
public class DaoCidade {
    
     
    private Connection connection;
    
    public DaoCidade(Connection connection) throws SQLException{
        this.connection = connection;
    }
    
    
    public void save(Cidade cidade) throws SQLException, Exception{
        PreparedStatement stmt = this.connection.prepareStatement("insert into cidade(uf_iduf, nome_cidade)"
                + " values (?,?)");
        stmt.setInt(1, cidade.getUf().getCodigo());
        stmt.setString(2, cidade.getNome());
        /*stmt.setInt(3,getRuaRows());
        stmt.setInt(4,getBairroRows());
        stmt.setInt(5,getCidadeRows());*/
        
        stmt.execute();
        stmt.close();
               
    }
    
    public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from cidade");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
        
        return rowCount;
    }
    
     public List<Cidade> list() throws SQLException, Exception {
        // executa um select
        List<Cidade> cidades = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from cidade ")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {  
               DaoUF du = new DaoUF(connection);
               
               Cidade cidade = new Cidade();
               cidade.setCodigo(rs.getInt("idcidade"));
               cidade.setNome(rs.getString("nome_cidade"));
               cidade.setUf(du.ufByCodigo(rs.getInt("uf_iduf")));
               cidades.add(cidade);
            }
        }
        return cidades;
    }

    public Cidade cidadeByCodigo(int id) throws Exception {
         // executa um select
        Cidade cidade = new Cidade();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from cidade where idcidade='" + id +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");  
               DaoUF du = new DaoUF(connection);

               cidade.setCodigo(rs.getInt("idcidade"));
               cidade.setNome(rs.getString("nome_cidade"));   
               cidade.setUf(du.ufByCodigo(rs.getInt("uf_iduf")));

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return cidade;
    }
    
    public Cidade cidadeByNome(String nome) throws Exception {
         // executa um select
        Cidade cidade = new Cidade();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from cidade where nome_cidade='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");  
               DaoUF du = new DaoUF(connection);

               cidade.setCodigo(rs.getInt("idcidade"));
               cidade.setNome(rs.getString("nome_cidade"));   
               cidade.setUf(du.ufByCodigo(rs.getInt("uf_iduf")));

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return cidade;
    }
    
}
