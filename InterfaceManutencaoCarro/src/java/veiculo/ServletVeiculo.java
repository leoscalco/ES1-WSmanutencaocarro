/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiculo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.bo.pessoa.NomePessoa;
import unioeste.geral.bo.veiculo.Marca;
import unioeste.geral.bo.veiculo.Modelo;
import unioeste.geral.bo.veiculo.Veiculo;
import unioeste.manutencao.bo.cliente.Cliente;
import unioeste.manutencao.serv.manager.UCVeiculo;

/**
 *
 * @author leoscalco
 */
public class ServletVeiculo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String action = request.getServletPath().substring(request.getServletPath().lastIndexOf("/")+1, 
                    request.getServletPath().length());
        if(action.equals("cadastrar")){
            cadastrar(request, response);
                response.sendRedirect("/InterfaceManutencaoCarro/"); 
        }
    }
    
    void cadastrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{
        Veiculo veiculo = new Veiculo();
        Modelo modelo = new Modelo();
        Marca marca = new Marca();
        Cliente cliente = new Cliente();
        NomePessoa np = new NomePessoa();
        np.setPrimeiroNome(request.getParameter("cliente"));
        
        cliente.setNomePessoa(np);
        
        marca.setNome(request.getParameter("marca"));
        modelo.setMarca(marca);
        modelo.setNome(request.getParameter("modelo"));
        
        veiculo.setPlaca(request.getParameter("placa"));
        veiculo.setChassi(request.getParameter("chassi"));
        veiculo.setAno(Integer.parseInt(request.getParameter("ano")));
        veiculo.setCor(request.getParameter("cor"));
        veiculo.setModelo(modelo);
        veiculo.setCliente(cliente);
        
        
        UCVeiculo ucv = new UCVeiculo();
        ucv.cadastrar(veiculo);
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
