/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.serv.ordemservico;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import unioeste.geral.bo.veiculo.Veiculo;
import unioeste.manutencao.bo.cliente.Cliente;
import unioeste.manutencao.bo.ordemServico.OrdemServico;
import unioeste.manutencao.bo.ordemServico.TipoServico;
import unioeste.manutencao.serv.cliente.DaoCliente;
import unioeste.manutencao.serv.veiculo.DaoVeiculo;

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
                + "data_abertura, descricao, situacao, veiculo_idveiculo, cliente_idcliente)"
                + " values (?, ?, ?, ?, ?)");
        
        stmt.setDate(1, new java.sql.Date(os.getDataAbertura().getTime()));
        stmt.setString(2, os.getDescricao());
        stmt.setString(3, os.getSituacao()+"");
        stmt.setInt(4, os.getVeiculo().getCodigo());
        stmt.setInt(5, os.getCliente().getIdCliente());
        
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
    
    public ArrayList<OrdemServico> getOSbySituacao(String situacao) throws Exception{
         ArrayList<OrdemServico> list = new ArrayList<>();
         
          try(PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM ordem_servico WHERE situacao='"+ situacao +"'")){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                OrdemServico os = new OrdemServico();
                os.setCodigo(rs.getInt("idordem_servico"));
                os.setDataAbertura(rs.getDate("data_abertura"));
                os.setDescricao(rs.getString("descricao"));
                list.add(os);
            }
            }catch(Exception e){
              System.out.println(e.getMessage());
            }
            return list;
    }
    
    public ArrayList<OrdemServico> listar() throws SQLException{
        ArrayList<OrdemServico> list = new ArrayList<>();
        DaoVeiculo daoveiculo = new DaoVeiculo(connection);
        DaoCliente daocliente = new DaoCliente(connection);
         
        try(PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM ordem_servico")){
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              OrdemServico os = new OrdemServico();
              os.setCodigo(rs.getInt("idordem_servico"));
              os.setDataAbertura(rs.getDate("data_abertura"));
              os.setDescricao(rs.getString("descricao"));
              os.setDataInicio(rs.getDate("data_inicio"));
              os.setDataFim(rs.getDate("data_fim"));
              os.setValorTotal(rs.getDouble("valor_total"));
              Veiculo veiculo = daoveiculo.veiculoByCodigo(rs.getInt("veiculo_idveiculo"));
              if (rs.getString("situacao") != null){
                os.setSituacao(rs.getString("situacao").charAt(0));
              }
              
              os.setVeiculo(veiculo);
              Cliente cliente = daocliente.clienteByCodigo(rs.getInt("cliente_idcliente"));
              os.setCliente(cliente);
              os.setTipoServico(getServicos(os.getCodigo()));
              list.add(os);
          }
          }catch(Exception e){
            System.out.println(e.getMessage());
          }
          return list;
    }

    public OrdemServico getOSbyCodigo(int codigo) {
        OrdemServico os = new OrdemServico();
        Veiculo veiculo = new Veiculo();
        Cliente cliente = new Cliente();
         
        try(PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM ordem_servico WHERE idordem_servico='"+ codigo +"'")){
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              os.setCodigo(rs.getInt("idordem_servico"));
              os.setDataAbertura(rs.getDate("data_abertura"));
              os.setDescricao(rs.getString("descricao"));
              os.setDataInicio(rs.getDate("data_inicio"));
              os.setDataFim(rs.getDate("data_fim"));
              os.setValorTotal(rs.getDouble("valor_total"));
              os.setSituacao(rs.getString("situacao").charAt(0));
              veiculo.setCodigo(rs.getInt("veiculo_idveiculo"));
              cliente.setIdCliente(rs.getInt("cliente_idcliente"));
              os.setTipoServico(getServicos(codigo));
              os.setVeiculo(veiculo);
              os.setCliente(cliente);
          }
          }catch(Exception e){
            System.out.println(e.getMessage());
          }
          return os;
    }

    public void update(OrdemServico os) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("update ordem_servico set "
                + "data_inicio = ?, data_fim = ?, valor_total = ?, situacao = ? where idordem_servico = ?");
        
        stmt.setDate(1, new java.sql.Date(os.getDataInicio().getTime()));
        stmt.setDate(2, new java.sql.Date(os.getDataFim().getTime()));
        stmt.setDouble(3, os.getValorTotal());
        stmt.setString(4, os.getSituacao()+"");
        stmt.setInt(5, os.getCodigo());
        
        
        stmt.execute();
        stmt.close();
    }
    
    public void addServicos(int codigoOS, TipoServico ts) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("insert into ordem_tipo_servico("
                + "tipo_servico_idservico, ordem_servico_idordem_servico, valor_tipo_servico)"
                + " values (?, ?, ?)");
            
            stmt.setInt(2, codigoOS);
            stmt.setInt(1, ts.getCodigo());
            stmt.setDouble(3, ts.getValor());
           // stmt.setInt(4, ts.getTempo());
            
            stmt.execute();
            stmt.close();
        
    }
    
    public List<TipoServico> getServicos(int codigoOS) throws SQLException{
        DaoTipoServico dts = new DaoTipoServico(connection);
        List<TipoServico> lts = new ArrayList<>();
        try(PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM ordem_tipo_servico WHERE ordem_servico_idordem_servico='"+ codigoOS +"'")){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){                
                TipoServico ts = dts.servicoByCodigo(rs.getInt("tipo_servico_idservico"));
                ts.setValor(rs.getDouble("valor_tipo_servico"));
               
                lts.add(ts);
            }
            }catch(Exception e){
              System.out.println(e.getMessage());
            }
            return lts;
    }
}
