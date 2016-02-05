/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.webservice;

import com.google.gson.Gson;
import java.rmi.Naming;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import unioeste.manutencao.bo.ordemServico.TipoServico;
import unioeste.manutencao.serv.manager.UCServico;

/**
 * REST Web Service
 *
 * @author leoscalco
 */
@Path("servicoJSON")
public class ServicoJSON {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of servicoWS
     */
    public ServicoJSON() {
    }

    /**
     * Retrieves representation of an instance of unioeste.manutencao.webservice.ServicoJSON
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() throws Exception {
        UCServico ucservico = new UCServico();
        String jSON = new Gson().toJson(ucservico.listar());
        return jSON;      
    }

    /**
     * PUT method for updating or creating an instance of ServicoJSON
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) throws Exception {
        UCServico ucservico = new UCServico();
        TipoServico tipo = new Gson().fromJson(content, TipoServico.class);
        ucservico.cadastrar(tipo);
    }
}
