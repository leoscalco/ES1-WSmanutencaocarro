/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unioeste.manutencao.webservice;

import com.thoughtworks.xstream.XStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import unioeste.manutencao.serv.manager.UCServico;

/**
 * REST Web Service
 *
 * @author leoscalco
 */
@Path("servicoXML")
public class ServicoXML {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoXML
     */
    public ServicoXML() {
    }

    /**
     * Retrieves representation of an instance of unioeste.manutencao.webservice.ServicoXML
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() throws Exception {
        UCServico ucservico = new UCServico();
        String XML = new XStream().toXML(ucservico.listar());
        return XML;    
    }

    /**
     * PUT method for updating or creating an instance of ServicoXML
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
