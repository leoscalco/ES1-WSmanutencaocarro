/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.webservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import unioeste.manutencao.bo.cliente.Cliente;
import unioeste.manutencao.serv.cliente.DaoCliente;
import unioeste.manutencao.serv.manager.UCCliente;

/**
 * REST Web Service
 *
 * @author leoscalco
 */
@Path("cliente")
public class ClienteWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClienteWS
     */
    public ClienteWS() {
    }

    /**
     * Retrieves representation of an instance of unioeste.manutencao.webservice.ClienteWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() throws SQLException, Exception {
        //TODO return proper representation object
        UCCliente uc = new UCCliente();
        Cliente cli;
        cli = uc.listar().get(0);
        
        return "<html><body><h1>"+cli.getIdCliente()+":"+cli.getTipoPessoa()+"</body></h1></html>";
    }
    
    /**
     * PUT method for updating or creating an instance of ClienteWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }
}
