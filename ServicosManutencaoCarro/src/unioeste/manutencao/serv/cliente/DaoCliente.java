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
import unioeste.geral.bo.pessoafisica.*;

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
    
}