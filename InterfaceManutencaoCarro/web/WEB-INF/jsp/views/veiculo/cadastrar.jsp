<%-- 
    Document   : cadastroVeiculo
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
        <STYLE TYPE="text/css" media="all">
            .ui-autocomplete { 
                position: absolute; 
                cursor: default; 
                height: 200px; 
                overflow-y: scroll; 
                overflow-x: hidden;}
            </STYLE>

            <script type="text/javascript">
            $(document).ready(function() {
                $(function() {
                        $("#cliente").autocomplete({     
                        source : function(request, response) {
                        $.ajax({
                                url : "../../cliente/autocomplete",
                                type : "GET",
                                data : {
                                        term : request.term
                                },
                                dataType : "json",
                                success : function(data) {
                                        response(data);
                                }
                        });
                }
        });
        });
        });

            </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Veículo</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Cadastro de Veículo</h1>        
                    <hr>
                    <form action="../../veiculo/cadastrar" method="post">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="basic-addon1">
                                <span class="glyphicon glyphicon-shopping-cart"></span>                        
                            </span>
                            <input type="text" name="cliente" id="cliente" class="form-control" placeholder="Nome Cliente" aria-describedby="basic-addon1">
                        </div>
                        
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-barcode"></span> 
                                    </span>
                                    <input type="text" name="marca" id="marca" class="form-control" placeholder="Marca" aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-asterisk"></span> 
                                    </span>
                                    <input type="text" name="modelo" id="telefone" class="form-control" placeholder="Modelo" aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="placa" class="form-control" placeholder="Placa" aria-describedby="basic-addon4">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="text" name="chassi" id="telefone" class="form-control" placeholder="Chassi" aria-describedby="basic-addon3">
                                </div>
                            </div>
                            
                        </div>

                        
                         <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon4">
                                        <span class="glyphicon glyphicon-adjust"></span> 
                                    </span>
                                    <input type="text" name="ano" class="form-control" placeholder="Ano" aria-describedby="basic-addon4">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon5">
                                        <span class="glyphicon glyphicon-picture"></span> 
                                    </span>
                                    <input type="text" name="cor" class="form-control" placeholder="Cor" aria-describedby="basic-addon5">
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
