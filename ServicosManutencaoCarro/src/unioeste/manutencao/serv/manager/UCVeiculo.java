/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import unioeste.geral.bo.veiculo.Marca;
import unioeste.geral.bo.veiculo.Modelo;
import unioeste.geral.bo.veiculo.Veiculo;
import unioeste.manutencao.infra.configuracao.ConexaoMySQL;
import unioeste.manutencao.serv.cliente.DaoCliente;
import unioeste.manutencao.serv.veiculo.DaoMarca;
import unioeste.manutencao.serv.veiculo.DaoModelo;
import unioeste.manutencao.serv.veiculo.DaoVeiculo;

/**
 *
 * @author leoscalco
 */
public class UCVeiculo {
    
    Connection conn;
    
     public Connection abrirConexao(){
        return ConexaoMySQL.getConexaoMySQL();
    }
    
    public void fecharConexao(){
        ConexaoMySQL.FecharConexao();
    }
    
    public void cadastrar(Veiculo veiculo) throws SQLException, Exception{
        conn = abrirConexao();
        
        DaoCliente daocliente = new DaoCliente(conn);
        veiculo.setCliente(daocliente.clienteByNome(veiculo.getCliente().getNomePessoa().getPrimeiroNome()));
        
        DaoMarca daomarca = new DaoMarca(conn);
        Marca marcaBusca = daomarca.marcaByNome(veiculo.getModelo().getMarca().getNome());
        
         if(marcaBusca.getNome() == null){
            daomarca.save(veiculo.getModelo().getMarca());
            veiculo.getModelo().setMarca(daomarca.marcaByNome(veiculo.getModelo().getMarca().getNome()));
        }else{
            if(!marcaBusca.getNome().equals(veiculo.getModelo().getMarca().getNome())){
                daomarca.save(veiculo.getModelo().getMarca());
                veiculo.getModelo().setMarca(daomarca.marcaByNome(veiculo.getModelo().getMarca().getNome())); 
            }else{
                veiculo.getModelo().setMarca(marcaBusca);
            }
        }
         
        DaoModelo daomodelo = new DaoModelo(conn);
        Modelo modeloBusca = daomodelo.modeloByNome(veiculo.getModelo().getNome());
        
        if(modeloBusca.getNome() == null){
            daomodelo.save(veiculo.getModelo());
            veiculo.setModelo(daomodelo.modeloByNome(veiculo.getModelo().getNome()));
        }else{
            if(!modeloBusca.getNome().equals(veiculo.getModelo().getNome())){
                daomodelo.save(veiculo.getModelo());
                veiculo.setModelo(daomodelo.modeloByNome(veiculo.getModelo().getNome())); 
            }else{
                veiculo.setModelo(modeloBusca);
            }
        }  
        
        DaoVeiculo daoveiculo = new DaoVeiculo(conn);
        daoveiculo.save(veiculo);
        
        fecharConexao();
    }

    public Map<String, String> autoComplete(String term) throws SQLException {
        conn = abrirConexao();
        
        DaoVeiculo dao = new DaoVeiculo(conn);
        Map<String, String> ret;
        ret = dao.VeiculosByPlaca(term);
        
        fecharConexao();
        
        return ret;
    }
    
}
