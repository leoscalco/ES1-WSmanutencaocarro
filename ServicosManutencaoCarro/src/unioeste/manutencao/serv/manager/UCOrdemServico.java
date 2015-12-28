/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.manager;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.manutencao.bo.ordemServico.OrdemServico;
import unioeste.manutencao.infra.configuracao.ConexaoMySQL;
import unioeste.manutencao.serv.ordemservico.DaoOrdemServico;

/**
 *
 * @author leoscalco
 */
public class UCOrdemServico {
    
    Connection conn;
    
     public Connection abrirConexao(){
        return ConexaoMySQL.getConexaoMySQL();
    }
    
    public void fecharConexao(){
        ConexaoMySQL.FecharConexao();
    }
    
    public void cadastrar(OrdemServico os) throws SQLException, Exception{
        conn = abrirConexao();
        
        DaoOrdemServico dao = new DaoOrdemServico(conn);
        dao.save(os);
        
        fecharConexao();
    }
    
    public int qtdOS() throws SQLException, Exception{
        conn = abrirConexao();
        
        DaoOrdemServico dao = new DaoOrdemServico(conn);
        int qtd = dao.countRows();
        
        fecharConexao();
        
        return qtd;
    }
    
}
