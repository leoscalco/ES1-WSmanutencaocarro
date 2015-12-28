<%-- 
    Document   : listar.jsp
    Created on : 06/09/2015, 12:21:33
    Author     : leoscalco
--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Listagem de Clientes</h1>        
                    <hr>
                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>#</th>
                                <th>Nome </th>
                                <th>Tipo Pessoa</th>
                            </tr>                      
                            <c:forEach var="cliente" items="${clientes}" varStatus="cont">
                                <tr>
                                    <td>${cliente.idCliente}</td>
                                    <td>${cliente.nomePessoa.primeiroNome}${cliente.nomePessoa.nomeFantasia}</td>
                                    <td>${cliente.tipoPessoa}</td>
                                </tr>
                             </c:forEach>
                        </table>
                   </div>
                    <jsp:include page="../structure/rodape.jsp"></jsp:include>
                      
            </div>
        </div>
    </body>
</html>
