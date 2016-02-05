/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.manutencao.bo.cliente.*;
import unioeste.manutencao.serv.manager.UCCliente;
import unioeste.geral.bo.endereco.*;
import unioeste.geral.bo.pessoa.*;
import unioeste.geral.bo.pessoafisica.*;
import unioeste.geral.bo.telefone.*;

/**
 *
 * @author leoscalco
 */
public class ServletCliente extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
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
        } else if (action.equals("autocomplete")) {
            autoComplete(request, response);
        }else if(action.equals("listar")){
            listar(request, response);
            RequestDispatcher view = request.getRequestDispatcher("/views/cliente/listar.jsp");    
            view.forward(request,response); 
        }
    }

    void cadastrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        Cliente cliente = new Cliente();
        NomePessoa np = new NomePessoa();
        List<EnderecoEspecifico> lee = new ArrayList<>();
        List<Fone> lf = new ArrayList<>();
        Fone f = new Fone();
        TipoFone tf = new TipoFone();
        EnderecoEspecifico ee = new EnderecoEspecifico();
        Endereco endereco = new Endereco();
        Bairro b = new Bairro();
        TipoLogradouro tl = new TipoLogradouro();
        Logradouro l = new Logradouro();
        Cidade c = new Cidade();
        Uf u = new Uf();

        CPF cpf = new CPF();
        DocIdentidade di = new DocIdentidade();
        Sexo sexo = new Sexo();

        cpf.setNroCPF(Integer.parseInt(request.getParameter("cpf")));
        sexo.setNomeSexo("m");
        di.setRg(Integer.parseInt(request.getParameter("reg")));

        String rua = request.getParameter("rua");

        b.setNome(request.getParameter("bairro"));
        tl.setNome(rua.substring(0, rua.indexOf(" ")));
        l.setNome(rua.substring(rua.indexOf(" ") + 1, rua.length()));
        c.setNome(request.getParameter("cidade"));
        u.setNome(request.getParameter("estado"));
        u.setSiglaUf(request.getParameter("estado"));
        l.setTipo(tl);
        c.setUf(u);

        endereco.setBairro(b);
        endereco.setCidade(c);
        endereco.setRua(l);
        endereco.setCep(request.getParameter("cep"));
        ee.setEndereco(endereco);
        ee.setComplementoEndereco(request.getParameter("complemento"));
        ee.setNroEndereco(Integer.parseInt(request.getParameter("numero")));
        lee.add(ee);

        np.setNomeCompleto(request.getParameter("nome"));
        f.setNroFone(Integer.parseInt(request.getParameter("telefone")));
        tf.setNomeTipoFone(request.getParameter("tipofone"));
        lf.add(f);

        cliente.setNomePessoa(np);
        cliente.setTipoPessoa('f');
        cliente.setEnderecoEspecifico(lee);
        cliente.setFone(lf);

        UCCliente uc = new UCCliente();

        uc.cadastrar(cliente, sexo, cpf, di);

//        UCEnderecoGeralServicos ub = new UCEnderecoGeralServicos();
//        ub.cadastrar(request.getParameter("cep"), b, c,l, tl, u);   
    }

    void teste(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
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

    private void autoComplete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, Exception {
       
        response.setContentType("application/json");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        String query;
        query = request.getParameter("term");
         
        UCCliente uc = new UCCliente();
        ArrayList<String> result = new ArrayList<>();
        
        result = uc.autoComplete(query);
        String searchList = new Gson().toJson(result);
        response.getWriter().write(searchList);
      
            
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        Registry registry = LocateRegistry.getRegistry(1099);
        RmiServer rmiserver = (RmiServer) registry.lookup("clientesRMI");
        request.setAttribute("clientes", rmiserver.listarClientes());
    }

}
