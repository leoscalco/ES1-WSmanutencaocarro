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
        <title>Cadastro de Cliente</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Cadastro de Cliente</h1>        
                    <hr>
                    <form action="../../cliente/cadastrar" method="post">
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
                                    <input type="text" name="email" id="email" class="form-control" placeholder="E-mail" aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-phone-alt"></span> 
                                    </span>
                                    <input type="text" name="telefone" id="telefone" class="form-control" placeholder="Telefone" aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-credit-card"></span> 
                                    </span>
                                    <input type="text" name="cpf" class="form-control" placeholder="CPF" aria-describedby="basic-addon4">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon5">
                                        <span class="glyphicon glyphicon-credit-card"></span> 
                                    </span>
                                    <input type="text" name="reg" class="form-control" placeholder="RG" aria-describedby="basic-addon5">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-4">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon6">
                                        <span class="glyphicon glyphicon-globe"></span> 
                                    </span>
                                    <input type="text" name="rua" class="form-control" placeholder="Rua" aria-describedby="basic-addon6">
                                </div>
                            </div>
                            <div class="col-sm-3 ">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon7">
                                        <span class="glyphicon glyphicon-globe"></span> 
                                    </span>
                                    <input type="text" name="bairro" class="form-control" placeholder="Bairro" aria-describedby="basic-addon7">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon8">
                                        <span class="glyphicon glyphicon-globe"></span> 
                                    </span>
                                    <input type="text" name="cidade" class="form-control" placeholder="Cidade" aria-describedby="basic-addon8">
                                </div>
                            </div>
                            <div class="col-sm-2 ">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon9">
                                        <span class="glyphicon glyphicon-globe"></span> 
                                    </span>
                                    <input type="text" name="estado" class="form-control" placeholder="Estado" aria-describedby="basic-addon9">
                                </div>
                            </div>
                            <div class="col-sm-2 ">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon9">
                                        <span class="glyphicon glyphicon-globe"></span> 
                                    </span>
                                    <input type="text" name="numero" class="form-control" placeholder="Numero" aria-describedby="basic-addon9">
                                </div>
                            </div>
                           <div class="col-sm-2 ">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon9">
                                        <span class="glyphicon glyphicon-globe"></span> 
                                    </span>
                                    <input type="text" name="cep" class="form-control" placeholder="CEP" aria-describedby="basic-addon9">
                                </div>
                            </div>
                             <div class="col-sm-8 ">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon9">
                                        <span class="glyphicon glyphicon-globe"></span> 
                                    </span>
                                    <input type="text" name="complemento" class="form-control" placeholder="Complemento" aria-describedby="basic-addon9">
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
