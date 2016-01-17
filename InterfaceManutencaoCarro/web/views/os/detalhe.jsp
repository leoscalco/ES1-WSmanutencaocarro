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
        <title>Detalhe</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Detalhe</h1>        
                    <hr>
                    <div class="row">
                        <div class="col-md-4">
                            <h3>OS</h3>
                            <h5>#: ${os.codigo}</h5>
                            <h5>Data de Abertura: ${os.dataAbertura}</h5>
                            <h5>Data de Início: ${os.dataInicio}</h5>
                            <h5>Previsão: ${os.dataFim}</h5>
                            <h5>Descrição: ${os.descricao}</h5>
                        </div>
                        <div class="col-md-4">
                            <h3>Cliente</h3>
                            <h5>#: ${os.cliente.idCliente}</h5>
                            <h5>Nome Cliente: ${os.cliente.nomePessoa.primeiroNome}${os.cliente.nomePessoa.nomeFantasia}</h5>
                            <h5>Tipo de Cliente: ${os.cliente.tipoPessoa}</h5>
                        </div>
                        <div class="col-md-4">
                            <h3>Veículo</h3>
                            <h5>#: ${os.veiculo.codigo}</h5>
                            <h5>Placa: ${os.veiculo.placa}</h5>
                            <h5>Descrição: ${os.veiculo.modelo.marca.nome} ${os.veiculo.modelo.nome} ${os.veiculo.cor} ${os.veiculo.ano}</h5>
                        </div>
                        
                    </div>
                    
                    <h3>Serviços Extras:</h3>                 
                        <table class="table">
                            <tr>
                                <th>#</th>
                                <th>Nome</th>
                                <th>Tempo ref</th>
                                <th>Valor</th>
                            </tr> 
                            <c:forEach var="servico" items="${os.tipoServico}" varStatus="cont">
                                <tr>
                                    <td>${servico.codigo}</a></td>
                                    <td>${servico.nome}</td>
                                    <td>${servico.tempo}</td>
                                    <td>${servico.valor}</td>
                                </tr>
                         </c:forEach>
                                <tr>
                                    <td> </td>
                                    <td> </td>
                                    <th>TOTAL:</th>
                                    <td>${os.valorTotal}</td>
                                </tr>
                        </table>
                  <jsp:include page="../structure/rodape.jsp"></jsp:include>
                      
            </div>
        </div>
    </body>
</html>
