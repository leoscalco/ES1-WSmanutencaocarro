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
        <title>Add serviços</title>
         <style>
            .input-group{
                margin-bottom: 20px
            }
        </style>
        
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>    
                <script type="text/javascript">                       
                        function getTipoServico(){
                            var result;
                            var codigo;
                            $("input.tipoServico").autocomplete({
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
                                        url: "../servico/autocomplete",
                                        dataType: "json",
                                        data: {
                                            term: request.term,                         
                                        },
                                        success: function(data, textStatus, jqXHR) {
                                            var items = [];
                                            console.log(data);
                                            result = JSON.parse(data);
                                            $.each(JSON.parse(data), function( key, val ) {
                                                items.push(val.nome);
                                              });
//                                           res
                                            response(items);
                                        },
                                        error: function(jqXHR, textStatus, errorThrown){
                                             console.log( textStatus);
                                        }
                                    });
                                },
                                select: function(e, ui) {
                                    var obj = getObjects(result, "nome", ui.item.label);
                                    $.each(obj, function() {
                                        var value = this["tempo"];
                                        $("#tempoServico").val(value);
                                        value = this["valor"];
                                        $("#valorServico").val(value);
                                      }); 
                                    
                                }
                            });
                        }
                        function getObjects(obj, key, val) {
                            var objects = [];
                            for (var i in obj) {
                                if (!obj.hasOwnProperty(i)) continue;
                                if (typeof obj[i] == 'object') {
                                    objects = objects.concat(getObjects(obj[i], key, val));
                                } else if (i == key && obj[key] == val) {
                                    objects.push(obj);
                                }
                            }
                            return objects;
                        }
                        function addrmvServicos(){
                            var max_fields      = 10; //maximum input boxes allowed
                                var wrapper         = $(".input_fields_wrap"); //Fields wrapper
                                var add_button      = $(".add_field_button"); //Add button ID
                                
                                var x = 1; //initlal text box count
                                $(add_button).click(function(e){ //on add input button click
                                    e.preventDefault();
                                    if(x < max_fields){ //max input box allowed
                                        x++; //text box increment
                                        $(wrapper).append('<div class="row">'+
                            '<div class="col-sm-5">'+
                                '<div class="input-group input-group-lg">'+
                                    '<span class="input-group-addon" id="basic-addon2">'+
                                        '<span class="glyphicon glyphicon-wrench"></span>'+
                                    '</span>'+
                                    '<input type="text" name="tipoServico[]" class="tipoServico form-control" placeholder="Tipo Serviço" aria-describedby="basic-addon2">'+
                                '</div>'+
                            '</div>'+
                            '<div class="col-sm-3">'+
                                '<div class="input-group input-group-lg">'+
                                    '<span class="input-group-addon" id="basic-addon3">'+
                                        '<span class="glyphicon glyphicon-time"></span>'+
                                    '</span>'+
                                    '<input type="text" name="tempoServico[]" id="tempoServico" class="form-control" placeholder="Tempo de execução" aria-describedby="basic-addon3">'+
                                '</div>'+
                            '</div>'+
                             '<div class="col-sm-3">'+
                                '<div class="input-group input-group-lg">'+
                                    '<span class="input-group-addon" id="basic-addon3">'+
                                        '<span class="glyphicon glyphicon-usd"></span>'+ 
                                    '</span>'+
                                    '<input type="text" name="valorServico[]" class="valorServico form-control" placeholder="Valor do serviço" aria-describedby="basic-addon3">'+
                                '</div>'+
                            '</div>'+
                             '<div class="col-sm-1">'+
                                 '<div class="btn-group">'+
                                    '<button class="btn btn-default btn-lg remove_field" type="button" aria-expanded="false">'+
                                       '<span class="glyphicon glyphicon-minus"> </span>'+
                                   ' </button>'+
                                  '</div>'+
                             '</div>'); 
                                    }
                                });

                                $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
                                    e.preventDefault(); $(this).parent().parent().parent('div').remove(); x--;
                                })
                        }
                        function getDiasExecucao(){
                            var diasFolga = 2;
                            var total = 0;
                                $("input[name='tempoServico[]']").each(function(){                                   
                                    total += parseInt($(this).val());
                                 });
                            return total+diasFolga;
                        }
                        function getTotalServicos(){
                            $("#totalServicos").click(function(e){
                                var total = 0;
                                $("input[name='valorServico[]']").each(function(){                                   
                                    total += parseFloat($(this).val());
                                 });
                                $("#totalServicos").val(total);
                            });
                        }
                        function getTotal(){
                            var valorOSpadrao = 200.0;
                            $("#totalOS").click(function(e){
                                var total = 0;
                                $("#totalOS").val(valorOSpadrao + parseFloat($("input[name='totalServicos']").val()));
                            });
                        }
                        function adicionarDiasData(){
                            $("#datafinal").click(function() {       
                                var hoje        = new Date($("#datainicio").val());
                                var dataVenc    = new Date(hoje.getTime() + (getDiasExecucao() * 24 * 60 * 60 * 1000));
                                if(dataVenc.getMonth() < 10){
                                    if(dataVenc.getDate()<10){
                                        $("#datafinal").val(dataVenc.getFullYear() + "-0" + (dataVenc.getMonth() + 1) + "-0" + dataVenc.getDate());
                                    }else{
                                        $("#datafinal").val(dataVenc.getFullYear() + "-0" + (dataVenc.getMonth() + 1) + "-" + dataVenc.getDate());
                                    }
                                }else{
                                    if(dataVenc.getDate()<10){
                                        $("#datafinal").val(dataVenc.getFullYear() + "-0" + (dataVenc.getMonth() + 1) + "-0" + dataVenc.getDate());
                                    }else{
                                        $("#datafinal").val(dataVenc.getFullYear() + "-" + (dataVenc.getMonth() + 1) + "-" + dataVenc.getDate());
                                    }
                             }
                             $("#duracaoDias").text(getDiasExecucao()+" dias");
                            });	
                        }
                        $(document).ready(function() {
                           getTipoServico();
                           addrmvServicos();
                           getTotalServicos();
                           getTotal();
                           adicionarDiasData();
                        });
                    </script>
                    <h1>Adicionar Serviços</h1>        
                    <hr>
                     <div class="row">
                        <div class="col-md-4">
                            <h3>OS</h3>
                            <h5>#: ${os.codigo}</h5>
                            <h5>Data de Abertura: ${os.dataAbertura}</h5>
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
                <hr>
                    <form action="../os/update" method="post">
                        <input type="hidden" name="codigoOS" class="form-control" value="${os.codigo}"  aria-describedby="basic-addon2">

                        <h3>Serviços a serem adicionados: </h3>
                        <div class="input_fields_wrap">
                         <div class="row">
                            <div class="col-sm-5">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-wrench"></span> 
                                    </span>
                                    <input type="text" name="tipoServico[]" class="tipoServico form-control" placeholder="Tipo Serviço" aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-time"></span> 
                                    </span>
                                    <input type="number" name="tempoServico[]" id="tempoServico" class="form-control" placeholder="Tempo de execução" aria-describedby="basic-addon3">
                                </div>
                            </div>
                             <div class="col-sm-3">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="number" name="valorServico[]" id="valorServico" class="valorServico form-control" placeholder="Valor do serviço" aria-describedby="basic-addon3">
                                </div>
                            </div>
                             <div class="col-sm-1">
                                 <div class="btn-group">
                                    <button class="btn btn-default btn-lg add_field_button" type="button" aria-expanded="false">
                                       <span class="glyphicon glyphicon-plus"> </span>  
                                    </button>
                                  </div>
                             </div>
                         </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-8 col-sm-4">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="number" name="totalServicos" id="totalServicos" class="form-control" placeholder="Total Serviços" aria-describedby="basic-addon2">
                                </div>
                            </div>
                        </div>
                        <hr>
                        <h3>Previsão de realização dos serviços: <span id="duracaoDias"></span></h3>
                        <div class="row">
                            <div class="col-sm-1">
                                <h4>Dê:</h4>
                            </div>
                            <div class="col-sm-5">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-calendar"></span> 
                                    </span>
                                    <input type="date" name="datainicio" id="datainicio" class="form-control" placeholder="Data Início" aria-describedby="basic-addon3">
                                </div>
                            </div>
                            <div class="col-sm-1">
                                <h4>Até:</h4>
                            </div>
                            <div class="col-sm-5">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon3">
                                        <span class="glyphicon glyphicon-calendar"></span> 
                                    </span>
                                    <input type="date" name="datafinal" id="datafinal" class="form-control" placeholder="Data Final " aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>
                        <hr>
                        <h3>Finalização:</h3>
                         <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon" id="basic-addon2">
                                        <span class="glyphicon glyphicon-usd"></span> 
                                    </span>
                                    <input type="number" name="totalOS" id="totalOS" class="form-control" placeholder="Total OS" aria-describedby="basic-addon2">
                                </div>
                            </div>                          
                        </div>
                        <div class="row">
                            <div class="col-lg-2">
                                <h4>Situação:</h4>
                            </div>
                            <div class="col-lg-4">
                              <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <input type="radio" name="situacao" value="E">
                                </span>
                                  <input type="text" class="form-control" readonly aria-label="..." value="Pronto para executar">
                              </div><!-- /input-group -->
                            </div><!-- /.col-lg-6 -->   
                            <div class="col-lg-4">
                              <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <input type="radio" name="situacao" value="C">
                                </span>
                                  <input type="text" class="form-control" readonly aria-label="..." value="Cancelado">
                              </div><!-- /input-group -->
                            </div><!-- /.col-lg-6 -->   
                          </div><!-- /.row -->

                        <button type="submit" class="btn btn-primary">Enviar</button>
                        <button type="reset" class="btn btn-danger ">Limpar</button>
                    </form>       
                    <jsp:include page="../structure/rodape.jsp"></jsp:include>
                      
            </div>
        </div>
    </body>
</html>
