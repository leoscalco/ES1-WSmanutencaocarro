<%-- 
    Document   : cadastroMatricula
    Created on : 22/04/2015, 10:30:48
    Author     : leoscalco
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .input-group{
                margin-bottom: 20px
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Serviço</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Cadastro de Serviço</h1>        
                    <hr>
                    <form action="../../servico/cadastrar" method="post">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="basic-addon1">
                                <span class="glyphicon glyphicon-user"></span>                        
                            </span>
                            <input type="text" name="nome" id="nome" class="form-control" placeholder="Nome" aria-describedby="basic-addon1">
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-envelope"></span> 
                                    </span>
                                    <input type="text" name="tempo" id="tempo" class="form-control" placeholder="Tempo" aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-phone-alt"></span> 
                                    </span>
                                    <input type="text" name="valor" id="valor" class="form-control" placeholder="Valor de referência" aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <button type="reset" class="btn btn-danger ">Limpar</button>
                    </form>

                    <jsp:include page="../structure/rodape.jsp"></jsp:include>
            </div>
        </div>
    </body>
</html>
