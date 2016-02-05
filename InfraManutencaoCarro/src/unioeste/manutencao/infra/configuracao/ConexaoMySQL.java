/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.infra.configuracao;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/**
 *
 * @author leoscalco
 */
public class ConexaoMySQL {

    public static String status = "Não conectou...";

    //Método Construtor da Classe// 
    public ConexaoMySQL() {
    }

    //Método de Conexão// 
    public static java.sql.Connection getConexaoMySQL() throws Exception {
        Connection connection = null;
        //atributo do tipo Connection 
            // Carregando o JDBC Driver padrão 
//            String driverName = "com.mysql.jdbc.Driver";
//            Class.forName(driverName);
//
//            // Configurando a nossa conexão com um banco de dados// 
//            String serverName = "localhost"; //caminho do servidor do BD 
//            String mydatabase = "SistemaManutencao"; //nome do seu banco de dados 
//            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
//            String username = "root"; //nome de um usuário de seu BD 
//            String password = "root"; //sua senha de acesso 
//            connection = DriverManager.getConnection(url, username, password);
            
//            InitialContext context = new InitialContext();
//            DataSource ds = (DataSource)context.lookup("MysqlDS");

            Context context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:jboss/datasources/MySqlDS");

            connection = ds.getConnection();

            //Testa sua conexão// 
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return connection;
      
    }

//Método que retorna o status da sua conexão// 
    public static String statusConection() {
        return status;
    }

//Método que fecha sua conexão// 
    public static boolean FecharConexao() throws Exception {
        try {
            ConexaoMySQL.getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

//Método que reinicia sua conexão// 
    public static java.sql.Connection ReiniciarConexao() throws Exception {
        FecharConexao();
        return ConexaoMySQL.getConexaoMySQL();
    }

}

