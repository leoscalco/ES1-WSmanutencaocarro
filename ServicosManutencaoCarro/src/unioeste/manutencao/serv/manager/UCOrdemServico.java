/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.bo.veiculo.Veiculo;
import unioeste.manutencao.bo.cliente.Cliente;
import unioeste.manutencao.bo.ordemServico.OrdemServico;
import unioeste.manutencao.bo.ordemServico.TipoServico;
import unioeste.manutencao.infra.configuracao.ConexaoMySQL;
import unioeste.manutencao.serv.cliente.DaoCliente;
import unioeste.manutencao.serv.ordemservico.DaoOrdemServico;
import unioeste.manutencao.serv.ordemservico.DaoTipoServico;
import unioeste.manutencao.serv.veiculo.DaoVeiculo;

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
        DaoVeiculo daoveiculo = new DaoVeiculo(conn);
        
        Veiculo veiculo = daoveiculo.veiculoByPlaca(os.getVeiculo().getPlaca());
        os.setCliente(veiculo.getCliente());
        os.setVeiculo(veiculo);
        
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
    
    public ArrayList<OrdemServico> getOSbySituacao(String situacao) throws SQLException, Exception{
        conn = abrirConexao();
        
        DaoOrdemServico dao = new DaoOrdemServico(conn);
        ArrayList<OrdemServico> lista = dao.getOSbySituacao(situacao);
        
        fecharConexao();
        return lista;
    }

    public OrdemServico getOSbyCodigo(int codigo) throws SQLException, Exception {
        conn = abrirConexao();
        
        DaoOrdemServico dao = new DaoOrdemServico(conn);
        DaoVeiculo daov = new DaoVeiculo(conn);
        DaoCliente daoc = new DaoCliente(conn);
        
        OrdemServico os = dao.getOSbyCodigo(codigo);
        Veiculo veiculo = daov.veiculoByCodigo(os.getVeiculo().getCodigo());
        Cliente cliente = daoc.clienteByCodigo(os.getCliente().getIdCliente());
        os.setVeiculo(veiculo);
        os.setCliente(cliente);
        
        fecharConexao();
        return os;
    }

    public void update(OrdemServico os) throws SQLException, Exception {
        conn = abrirConexao();
        
        DaoOrdemServico dao = new DaoOrdemServico(conn);
        
        dao.update(os);
        
        DaoTipoServico daots = new DaoTipoServico(conn);
        
        for (TipoServico tipoServico : os.getTipoServico()) {
            TipoServico tsaux = daots.servivoByNome(tipoServico.getNome());
            tipoServico.setCodigo(tsaux.getCodigo());
            dao.addServicos(os.getCodigo(), tipoServico);
        }
        
        fecharConexao();
        
    }
}
