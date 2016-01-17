/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.cliente;

/**
 *
 * @author leoscalco
 */

import unioeste.manutencao.bo.cliente.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.bo.pessoa.NomePessoa;
import unioeste.geral.bo.pessoa.Pessoa;
import unioeste.geral.bo.pessoafisica.*;
import unioeste.geral.bo.pessoajuridica.CNPJ;

/**
 *
 * @author leoscalco
 */
public class DaoCliente {
    
     
    private Connection connection;
    
    public DaoCliente(Connection connection) throws SQLException{
        this.connection = connection;
    }
       
    public void save(Cliente cliente, Sexo s, CPF cpf, DocIdentidade di) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into cliente("
                + "endereco_idendereco, primeiro_nome_cliente, cpf_cliente, rg_cliente, sexo_cliente, numero_endereco, complemento_endereco)"
                + " values (?, ?, ?, ?, ?, ?, ?)");
        
        
        stmt.setInt(1, cliente.getEnderecoEspecifico().get(0).getEndereco().getCodigo());
        stmt.setString(2, cliente.getNomePessoa().getNomeCompleto());
        stmt.setInt(3, cpf.getNroCPF());
        stmt.setInt(4, di.getRg());
        stmt.setString(5, s.getNomeSexo());
        stmt.setInt(6, cliente.getEnderecoEspecifico().get(0).getNroEndereco());
        stmt.setString(7, cliente.getEnderecoEspecifico().get(0).getComplementoEndereco());
        
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
      
      public ArrayList<String> clientesByNome(String nome){
          ArrayList<String> list = new ArrayList<>();
          String data;
          try{
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM cliente WHERE primeiro_nome_cliente LIKE ?");
            stmt.setString(1, nome + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                data = rs.getString("primeiro_nome_cliente");
                list.add(data);
            }
          }catch(Exception e){
              System.out.println(e.getMessage());
          }
          return list;
      }
      
      public Cliente clienteByNome(String nome) throws Exception {
         // executa um select
        Cliente cliente = new Cliente();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from cliente where primeiro_nome_cliente ='" + nome +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        

               cliente.setIdCliente(rs.getInt("idcliente"));        

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return cliente;
    }

    public List<Cliente> listar() throws Exception {
        // executa um select
        List<Cliente> clientes = new ArrayList<>();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from cliente")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
              //  String nome = rs.getString("nomeAluno");
             //   String email = rs.getString("email");                        
               Cliente cliente = new Cliente();
               NomePessoa np = new NomePessoa();
               
               cliente.setIdCliente(rs.getInt("idcliente"));
               if (rs.getString("primeiro_nome_cliente") == null){
                   np.setRazaoSocial(rs.getString("razao_social_cliente"));
                   np.setNomeFantasia(rs.getString("nome_fantasia_cliente"));
                   cliente.setTipoPessoa('J');
               }else{
                   np.setPrimeiroNome(rs.getString("primeiro_nome_cliente"));
                   np.setMeioNome(rs.getString("segundo_nome_cliente"));
                   np.setUltimoNome(rs.getString("ultimo_nome_cliente"));
                   cliente.setTipoPessoa('F');
               }              
               
               cliente.setNomePessoa(np);
               
               
//               end.setRua(dl.logradouroByCodigo(rs.getInt("logradouro_idlogradouro")));
//               end.setBairro(db.bairroByCodigo(rs.getInt("bairro_idbairro")));
//               end.setCidade(dc.cidadeByCodigo(rs.getInt("cidade_idcidade")));
//      
//               enderecos.add(end);
               clientes.add(cliente);
            }
        } catch (Exception e){
            throw new Exception(e.toString());
        } 
        return clientes;
    }

    public Cliente clienteByCodigo(int idCliente) throws Exception {
        Cliente cliente = new Cliente();
        try ( // pega a conexão e o Statement
            
            PreparedStatement stmt = this.connection.prepareStatement("select * from cliente where idcliente ='" + idCliente +"'")) {
            // executa um select
            ResultSet rs = stmt.executeQuery();
           
            
            // itera no ResultSet
            while (rs.next()) {
               NomePessoa np = new NomePessoa();
               
               cliente.setIdCliente(rs.getInt("idcliente"));
               if (rs.getString("primeiro_nome_cliente") == null){
                   np.setRazaoSocial(rs.getString("razao_social_cliente"));
                   np.setNomeFantasia(rs.getString("nome_fantasia_cliente"));
                   cliente.setTipoPessoa('J');
               }else{
                   np.setPrimeiroNome(rs.getString("primeiro_nome_cliente"));
                   np.setMeioNome(rs.getString("segundo_nome_cliente"));
                   np.setUltimoNome(rs.getString("ultimo_nome_cliente"));
                   cliente.setTipoPessoa('F');
               }              
               
               cliente.setNomePessoa(np);

            }
        }catch (Exception e){
            throw new Exception(e.toString());
        }
        return cliente;
    }
    
}