<%-- 
    Document   : cadastroMatricula
    Created on : 22/04/2015, 10:30:48
    Author     : leoscalco
--%>

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
        <title>Cadastro de Produto</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Cadastro de Produto</h1>        
                    <hr>
                    <form action="../../produto/cadastrar" method="post">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="basic-addon1">
                                <span class="glyphicon glyphicon-shopping-cart"></span>                        
                            </span>
                            <input type="text" name="nome" id="nome" class="form-control" placeholder="Nome Produto" aria-describedby="basic-addon1">
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-barcode"></span> 
                                    </span>
                                    <input type="text" name="codigoBarras" id="email" class="form-control" placeholder="Código de Barras" aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-asterisk"></span> 
                                    </span>
                                    <input type="text" name="quantidade" id="telefone" class="form-control" placeholder="Quantidade" aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="precoVendaAtual" class="form-control" placeholder="Preço Venda Atual" aria-describedby="basic-addon4">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="precoCustoAtual" id="telefone" class="form-control" placeholder="Preço Custo Atual" aria-describedby="basic-addon3">
                                </div>
                            </div>
                            
                        </div>

                        
                         <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-adjust"></span> 
                                    </span>
                                    <input type="text" name="nomeTipoProduto" class="form-control" placeholder="Tipo Produto" aria-describedby="basic-addon4">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon5">
                                        <span class="glyphicon glyphicon-picture"></span> 
                                    </span>
                                    <input type="text" name="caminhoImagem" class="form-control" placeholder="Caminho Imagem" aria-describedby="basic-addon5">
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
