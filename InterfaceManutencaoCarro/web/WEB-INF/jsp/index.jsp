<%-- 
    Document   : index
    Created on : 20/04/2015, 15:24:41
    Author     : leoscalco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Estoque</title>
    </head>
    <body>
        <div class="container">
            <%--<jsp:include page="views/structure/cabecalho.jsp"></jsp:include>--%> 
            <h1>Sistema de Manutenção de Veículos - es1</h1>
            <div class="row">
                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">Clientes</div>
                        <div class="panel-body">
                          <ul>
                              <li><a href="views/cliente/cadastrar.jsp">Cadastrar cliente Fisíco</a></li>      
                              <li><a href="cliente/listar">Listar clientes</a></li>        
                          </ul>
                          
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">Serviços</div>
                        <div class="panel-body">
                            <li><a href="views/servico/cadastrar.jsp">Cadastrar</a></li>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">Veículos</div>
                        <div class="panel-body">
                          <ul>                                                   
                              <li><a href="views/veiculo/cadastrar.jsp">Cadastrar veículo</a></li>
                          </ul>                          
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">Ordem de Serviço</div>
                        <div class="panel-body">
                            <li><a href="views/os/cadastrar.jsp">Cadastrar</a></li>
                        </div>
                    </div>
                </div>
            </div>
            <%--<jsp:include page="views/structure/rodape.jsp"></jsp:include>--%>
        </div>
    </body>
</html>
