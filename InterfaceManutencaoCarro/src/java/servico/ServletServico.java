/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;


import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.manutencao.bo.ordemServico.TipoServico;
import unioeste.manutencao.serv.manager.UCServico;


/**
 *
 * @author leoscalco
 */
public class ServletServico extends HttpServlet {

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
        String action = request.getServletPath().substring(request.getServletPath().lastIndexOf("/") + 1,
                request.getServletPath().length());
        if (action.equals("cadastrar")) {
            cadastrar(request, response);
            response.sendRedirect("/InterfaceManutencaoCarro/");
        }else if(action.equals("autocomplete")){
            autoComplete(request, response);
        }
    }

    void cadastrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        TipoServico ts = new TipoServico();
        ts.setNome(request.getParameter("nome"));
        ts.setTempo(Integer.parseInt(request.getParameter("tempo")));
        ts.setValor(Double.parseDouble(request.getParameter("valor")));
        
        UCServico ucs = new UCServico();
        ucs.cadastrar(ts);
    }
    
     private void autoComplete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, Exception {
       
        response.setContentType("application/json");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        String query;
        query = request.getParameter("term");
         
        UCServico uc = new UCServico();
        ArrayList<TipoServico> result;
        
        result = uc.autoComplete(query);
        Gson gson = new Gson();
        Type type = new TypeToken<List<TipoServico>>() {}.getType();
        String json = gson.toJson(result, type);

        response.getWriter().write(new Gson().toJson(json));
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
            Logger.getLogger(ServletServico.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletServico.class.getName()).log(Level.SEVERE, null, ex);
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
