/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.manager;

import unioeste.manutencao.serv.endereco.DaoBairro;
import unioeste.manutencao.serv.endereco.DaoLogradouro;
import unioeste.manutencao.serv.endereco.DaoUF;
import unioeste.manutencao.serv.endereco.DaoEndereco;
import unioeste.manutencao.serv.endereco.DaoTipoLogradouro;
import unioeste.manutencao.serv.endereco.DaoCidade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.manutencao.infra.configuracao.ConexaoMySQL;
import unioeste.manutencao.bo.cliente.Cliente;
import unioeste.manutencao.serv.cliente.DaoCliente;
import unioeste.geral.bo.endereco.*;
import unioeste.geral.bo.pessoafisica.*;

/**
 *
 * @author leoscalco
 */
public class UCCliente {
    
    Connection conn;
    
     public Connection abrirConexao() throws Exception{
        return ConexaoMySQL.getConexaoMySQL();
    }
    
    public void fecharConexao() throws Exception{
        ConexaoMySQL.FecharConexao();
    }
    
    public void cadastrar(Cliente cliente, Sexo s, CPF cpf, DocIdentidade di) throws SQLException, Exception{
        
        conn = abrirConexao();
        conn.setAutoCommit(false);
        
        try{
            DaoTipoLogradouro dtl = new DaoTipoLogradouro(conn);      

            TipoLogradouro tlBusca = dtl.tipoLogradouroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getTipo().getNome());

            if(tlBusca.getNome() == null){
                dtl.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getTipo());
                cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().setTipo(dtl.tipoLogradouroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getTipo().getNome())); 
            }else{
                if(!tlBusca.getNome().equals(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getTipo().getNome())){
                    dtl.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getTipo());
                    cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().setTipo(dtl.tipoLogradouroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getTipo().getNome())); 
                }else{
                    cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().setTipo(tlBusca);
                }
            }

            DaoUF duf = new DaoUF(conn);
            Uf ufBusca = duf.ufByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getUf().getNome());
            if(ufBusca.getNome() == null){
               duf.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getUf());
               cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().setUf(duf.ufByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getUf().getNome()));
            }else{
                if(!ufBusca.getNome().equals(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getUf().getNome())){
                    duf.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getUf());
                    cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().setUf(duf.ufByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getUf().getNome()));
                }else{
                    cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().setUf(ufBusca);
                }
            }

            DaoLogradouro dl = new DaoLogradouro(conn);
            Logradouro lgBusca = dl.logradouroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getNome());


            if(lgBusca.getNome() == null){
                dl.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua());
                cliente.getEnderecoEspecifico().get(0).getEndereco().setRua(dl.logradouroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getNome()));
            }else{
                if(!lgBusca.getNome().equals(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getNome())){
                    dl.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua());
                    cliente.getEnderecoEspecifico().get(0).getEndereco().setRua(dl.logradouroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getRua().getNome()));
                }
                cliente.getEnderecoEspecifico().get(0).getEndereco().setRua(lgBusca);
            }

            DaoBairro db = new DaoBairro(conn);
            Bairro bBusca = db.bairroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getBairro().getNome());
            if(bBusca.getNome() == null){
                db.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getBairro());
                cliente.getEnderecoEspecifico().get(0).getEndereco().setBairro(db.bairroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getBairro().getNome()));
            }else{
                if(!bBusca.getNome().equals(cliente.getEnderecoEspecifico().get(0).getEndereco().getBairro().getNome())){
                    db.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getBairro());
                    cliente.getEnderecoEspecifico().get(0).getEndereco().setBairro(db.bairroByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getBairro().getNome()));
                }
                cliente.getEnderecoEspecifico().get(0).getEndereco().setBairro(bBusca);
            }

            DaoCidade dc = new DaoCidade(conn);
            Cidade cBusca = dc.cidadeByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getNome());
            if(cBusca.getNome() == null){
                dc.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade());
                cliente.getEnderecoEspecifico().get(0).getEndereco().setCidade(dc.cidadeByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getNome()));
            }else{
                if(!cBusca.getNome().equals(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getNome())){
                    dc.save(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade());
                    cliente.getEnderecoEspecifico().get(0).getEndereco().setCidade(dc.cidadeByNome(cliente.getEnderecoEspecifico().get(0).getEndereco().getCidade().getNome()));
                }
                cliente.getEnderecoEspecifico().get(0).getEndereco().setCidade(cBusca);
            }

            DaoEndereco end = new DaoEndereco(conn);
            String cep = cliente.getEnderecoEspecifico().get(0).getEndereco().getCep();
            List<Endereco> endsBusca = end.enderecoByCep(cep);

            if(endsBusca.isEmpty()){
                cliente.getEnderecoEspecifico().get(0).getEndereco().setCep(cep);
                end.save(cliente.getEnderecoEspecifico().get(0).getEndereco()); 
                cliente.getEnderecoEspecifico().get(0).getEndereco().setCodigo(end.countRows());
            }else{
                if(!endsBusca.get(0).getCep().equals(cep)){
                    cliente.getEnderecoEspecifico().get(0).getEndereco().setCep(cep);
                    end.save(cliente.getEnderecoEspecifico().get(0).getEndereco());
                    cliente.getEnderecoEspecifico().get(0).getEndereco().setCodigo(end.countRows());
                }
            }


            DaoCliente dcli = new DaoCliente(conn);
            dcli.save(cliente, s, cpf, di);
            
            conn.commit();
        }catch(Exception e){
            throw e;
        }finally{
            fecharConexao();
        }
    }
 
    public ArrayList<String> autoComplete(String term) throws SQLException, Exception{
        conn = abrirConexao();
        conn.setAutoCommit(false);
        ArrayList<String> ret = new ArrayList<>();
        try{
            DaoCliente dao = new DaoCliente(conn);           
            ret = dao.clientesByNome(term);
            conn.commit();
        }catch(Exception e){
            throw e;
        }finally{
            fecharConexao();
        }
        
        return ret;
    }

    public List<Cliente> listar() throws SQLException, Exception {
        conn = abrirConexao();
        conn.setAutoCommit(false);
        List<Cliente> l = null;
        try{
            DaoCliente col = new DaoCliente(conn);
            l = col.listar();
            fecharConexao();
            conn.commit();
        }catch(Exception e){
            throw e;
        }finally{
            fecharConexao();
        }
        return l;
    }
    
}
