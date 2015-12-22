<%-- 
    Document   : cadastrar
    Created on : 21/09/2015, 21:02:41
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
        <title>Cadastro de Venda</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Cadastro de Venda</h1>        
                    <hr>
                    <form action="../../aluno/cadastrar" method="post">
                        <div class="row">
                         <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-asterisk"></span> 
                                    </span>
                                    <input type="text" name="nroNotaComercial" id="email" class="form-control" placeholder="Nota Comercial" aria-describedby="basic-addon2">
                                </div>
                            </div>     
                             <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-calendar"></span> 
                                    </span>
                                    <input type="text" name="dataEmissao" id="email" class="form-control" placeholder="Data de Emissão" aria-describedby="basic-addon2">
                                </div>
                            </div>     
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="totalNota" id="telefone" class="form-control" placeholder="Total" aria-describedby="basic-addon3">
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="descontoTotal" class="form-control" placeholder="Desconto Total" aria-describedby="basic-addon4">
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon5">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="valorLiquido" class="form-control" placeholder="Valor Liquído" aria-describedby="basic-addon5">
                                </div>
                            </div>
                        </div>
                        <hr>
                        
                         <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-user"></span> 
                                    </span>
                                    <input type="text" name="cliente" class="form-control" placeholder="Cliente" aria-describedby="basic-addon4">
                                </div>
                            </div>
                             <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-credit-card"></span> 
                                    </span>
                                    <input type="text" name="formaPgmto" class="form-control" placeholder="Forma de Pagamento" aria-describedby="basic-addon4">
                                </div>
                            </div>
                        </div>
                        
                        <hr>
                        
                         <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="basic-addon1">
                                <span class="glyphicon glyphicon-shopping-cart"></span>                        
                            </span>
                            <input type="text" name="produto" id="nome" class="form-control" placeholder="Produto" aria-describedby="basic-addon1">
                        </div>
                        
                         <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-equalizer"></span> 
                                    </span>
                                    <input type="text" name="qtdItemPedido" class="form-control" placeholder="Quantidade Item Pedido" aria-describedby="basic-addon4">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon5">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="precoTotal" class="form-control" placeholder="Preço Total Itens" aria-describedby="basic-addon5">
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
