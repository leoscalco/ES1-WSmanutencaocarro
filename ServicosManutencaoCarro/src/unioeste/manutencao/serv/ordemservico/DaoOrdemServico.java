/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.ordemservico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.manutencao.bo.ordemServico.OrdemServico;

/**
 *
 * @author leoscalco
 */
public class DaoOrdemServico {
    
    private Connection connection;
    
    public DaoOrdemServico(Connection connection) throws SQLException{
        this.connection = connection;
    }
       
    public void save(OrdemServico os) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into ordem_servico("
                + "data_abertura, descricao, veiculo_idveiculo, cliente_idcliente)"
                + " values (?, ?, ?, ?)");
        
        
        stmt.setDate(1, (Date) os.getDataAbertura());
        stmt.setString(2, os.getDescricao());
        stmt.setInt(3, os.getVeiculo().getCodigo());
        stmt.setInt(4, os.getCliente().getIdCliente());
        
        stmt.execute();
        stmt.close();
                
    }
    
    public int countRows() throws Exception{
       int rowCount = -1;
        try {
            PreparedStatement stmt = this.connection.prepareCall("select COUNT(*) from ordem_servico");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception e){
            throw new Exception(e.toString());
        }
          
        return rowCount;
    }
    
}
