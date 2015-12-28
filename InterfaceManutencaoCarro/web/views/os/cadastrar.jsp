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
        <title>Abertura Ordem de Serviço</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>      
                    <script type="text/javascript">
                        function getNumOS(){
                            $.ajax({
                                url: "../../os/getNumOS",
                                dataType: "json",
                                context: document.body,
                                success: function( data, textStatus, jqXHR) {
                                    console.log(data);                                   
                                    $("#codigo").val(data);
                                },
                                error: function(jqXHR, textStatus, errorThrown){
                                     console.log( textStatus);
                                }
                            });
                        }
                        function getPlacaCliente(){
                            var result;
                            $("input#placa").autocomplete({
                                width: 300,
                                max: 10,
                                delay: 100,
                                minLength: 1,
                                autoFocus: true,
                                cacheLength: 1,
                                scroll: true,
                                highlight: false,
                                source: function(request, response) {
                                    $.ajax({
                                        url: "../../veiculo/autocomplete",
                                        dataType: "json",
                                        data: {
                                            term: request.term,                         
                                        },
                                        success: function( data, textStatus, jqXHR) {
                                            console.log(data);
                                            var items = [];
                                            result = data;
                                            $.each(data, function(key, value){
                                                items.push(key);
                                            });
                                            response(items);
                                        },
                                        error: function(jqXHR, textStatus, errorThrown){
                                             console.log( textStatus);
                                        }
                                    });
                                },
                                select: function(e, ui) {
                                    $("#cliente").val(result[ui.item.label]);
                                }
                            });
                        }
                        $(document).ready(function() {
                           getNumOS();
                           getPlacaCliente();
                        });
                    </script>
                    <h1>Abertura Ordem de Serviço</h1>        
                    <hr>
                    <form action="../../os/cadastrar" method="post">
                         <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-asterisk"></span> 
                                    </span>
                                    <input type="text" name="codigo" readonly id="codigo" class="form-control" placeholder="Nro. OS" aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-calendar"></span> 
                                    </span>
                                    <input type="date" name="dataabertura" id="dataabertura" class="form-control" placeholder="Data Abertura OS" aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-road"></span> 
                                    </span>
                                    <input type="text" name="placa" id="placa" class="form-control" placeholder="Placa" aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-user"></span> 
                                    </span>
                                    <input type="text" name="cliente" id="cliente" class="form-control" placeholder="Cliente" aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-sm-2">
                                <h5>Descrição do Problema:</h5>
                            </div>
                            <div class="col-sm-10">
                                <textarea name="descricao" style="width: 100%; height: 45px;"></textarea>
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
