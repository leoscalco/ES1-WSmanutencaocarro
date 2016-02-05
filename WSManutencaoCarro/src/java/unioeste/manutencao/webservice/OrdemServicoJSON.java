/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.webservice;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import unioeste.manutencao.serv.manager.UCOrdemServico;

/**
 * REST Web Service
 *
 * @author leoscalco
 */
@Path("osJSON")
public class OrdemServicoJSON {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OrdemServicoJSON
     */
    public OrdemServicoJSON() {
    }

    /**
     * Retrieves representation of an instance of unioeste.manutencao.webservice.OrdemServicoJSON
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() throws Exception {
        UCOrdemServico uc = new UCOrdemServico();
        String jSON = new Gson().toJson(uc.listar());
        return jSON;      

    }

    /**
     * PUT method for updating or creating an instance of OrdemServicoJSON
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
