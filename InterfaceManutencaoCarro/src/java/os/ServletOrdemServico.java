package os;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.bo.pessoa.NomePessoa;
import unioeste.geral.bo.veiculo.Veiculo;
import unioeste.manutencao.bo.cliente.Cliente;
import unioeste.manutencao.bo.ordemServico.OrdemServico;
import unioeste.manutencao.bo.ordemServico.TipoServico;
import unioeste.manutencao.serv.manager.UCOrdemServico;

/**
 *
 * @author leoscalco
 */
@WebServlet(name = "OrdemServico", urlPatterns = {"/os"})
public class ServletOrdemServico extends HttpServlet {

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
        } else if(action.equals("listar")){
            listar(request, response);
            RequestDispatcher view = request.getRequestDispatcher("/views/os/listar.jsp");    
            view.forward(request,response); 
        }else if(action.equals("getNumOS")){
            getNumOS(request, response);
        }else if(request.getRequestURL().toString().contains("/os/addservicos")){
            addservicosOS(request, response);
        }else if(request.getRequestURL().toString().contains("/os/detalhe")){
            detalheOS(request, response);
        }else if(action.equals("update")){
            update(request, response);
            response.sendRedirect("/InterfaceManutencaoCarro/");
        }
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
            Logger.getLogger(ServletOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
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

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception {
        OrdemServico os = new OrdemServico();
        os.setCodigo(Integer.parseInt(request.getParameter("codigo")));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        os.setDataAbertura(formatter.parse(request.getParameter("dataabertura")));
        
        Cliente cliente = new Cliente();
        NomePessoa np = new NomePessoa();
        np.setPrimeiroNome(request.getParameter("cliente"));
        
        cliente.setNomePessoa(np);
        os.setCliente(cliente);
        
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(request.getParameter("placa"));
        os.setVeiculo(veiculo);
        
        os.setSituacao('A');
        os.setDescricao(request.getParameter("descricao"));
        
        UCOrdemServico uco = new UCOrdemServico();
        uco.cadastrar(os);
        
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        UCOrdemServico UC = new UCOrdemServico();
        request.setAttribute("ossabertas", UC.getOSbySituacao("A"));
        request.setAttribute("ossexecutar", UC.getOSbySituacao("E"));
        request.setAttribute("osscanceladas", UC.getOSbySituacao("C"));
    }

    private void getNumOS(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        UCOrdemServico uc = new UCOrdemServico();
        String result = new Gson().toJson(uc.qtdOS()+1);
        response.getWriter().write(result);
    }

    private void addservicosOS(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String var = request.getParameter("id");
        UCOrdemServico uc = new UCOrdemServico();
        request.setAttribute("os", uc.getOSbyCodigo(Integer.parseInt(var)));
        RequestDispatcher view = request.getRequestDispatcher("/views/os/addservicos.jsp");    
        view.forward(request,response);
    }
    
    private void detalheOS(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String var = request.getParameter("id");
        UCOrdemServico uc = new UCOrdemServico();
        request.setAttribute("os", uc.getOSbyCodigo(Integer.parseInt(var)));
        RequestDispatcher view = request.getRequestDispatcher("/views/os/detalhe.jsp");    
        view.forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception {
        UCOrdemServico ucos = new UCOrdemServico();
        
        OrdemServico os = new OrdemServico();
        os.setCodigo(Integer.parseInt(request.getParameter("codigoOS")));
        List<TipoServico> servicos = new ArrayList<>();
        
        String tipoServicos[] = request.getParameterValues("tipoServico[]");
        String temposServicos[] = request.getParameterValues("tempoServico[]");
        String valoresServicos[] = request.getParameterValues("valorServico[]");
        
        for(int i = 0; i < tipoServicos.length; i++){
            TipoServico ts = new TipoServico();
            ts.setNome(tipoServicos[i]);
            ts.setTempo(Integer.parseInt(temposServicos[i]));
            ts.setValor(Double.parseDouble(valoresServicos[i]));
            servicos.add(ts);
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        os.setDataInicio(formatter.parse(request.getParameter("datainicio")));
        os.setDataFim(formatter.parse(request.getParameter("datafinal")));
        os.setTipoServico(servicos);
        os.setValorTotal(Double.parseDouble(request.getParameter("totalOS")));
        os.setSituacao(request.getParameter("situacao").charAt(0));
        
        ucos.update(os);
    }
    

}
