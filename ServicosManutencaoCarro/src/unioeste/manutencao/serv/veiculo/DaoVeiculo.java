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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import unioeste.geral.bo.veiculo.Veiculo;

/**
 *
 * @author leoscalco
 */
public class DaoVeiculo {
    
    private Connection connection;
    
    public DaoVeiculo(Connection connection) throws SQLException{
        this.connection = connection;
    }
       
    public void save(Veiculo veiculo) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into veiculo("
                + "placa, chassi, ano, cor, modelo_idmodelo, cliente_idcliente)"
                + " values (?, ?, ?, ?, ?, ?)");

        stmt.setString(1, veiculo.getPlaca());
        stmt.setString(2, veiculo.getChassi());
        stmt.setInt(3, veiculo.getAno());
        stmt.setString(4, veiculo.getCor());
        stmt.setInt(5, veiculo.getModelo().getCodigo());
        stmt.setInt(6, veiculo.getCliente().getIdCliente());
        
        
        stmt.execute();
        stmt.close();
                
    }
    
      public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from cliente");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
          
        return rowCount;
    }

    public Map<String, String> VeiculosByPlaca(String term) {
        Map<String, String> map = new LinkedHashMap<String, String>();
      //  list.add("{");
        String placa, cliente;
        try{
          PreparedStatement stmt = this.connection.prepareStatement("select placa, primeiro_nome_cliente from veiculo, cliente "
                  + "where placa LIKE ? AND cliente_idcliente = idcliente;");
          stmt.setString(1, term + "%");
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              placa = rs.getString("placa") ;
              cliente = rs.getString("primeiro_nome_cliente");
              map.put(placa, cliente);
          }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    //    list.add("}");
        return map;
    }
    
}
