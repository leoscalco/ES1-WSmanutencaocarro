<%-- 
    Document   : cabecalho
    Created on : 22/04/2015, 10:44:35
    Author     : leoscalco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <style>
        .cabecalho{
            margin-top: 10px;
        }
        #cabImg{
            height: 30px;
        }
    </style>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    
        <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    </head>
    <body class="cabecalho">
        <nav class="navbar navbar-default ">
            <div class="container-fluid">
              <!-- Brand and toggle get grouped for better mobile display -->
              <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                  <a class="navbar-brand" href="/InterfaceManutencaoCarro/"> Início </a>
                <!--<img id="cabImg" src="../recursos/images/graduation22.png" alt=""/></a>-->
              </div>

              <!-- Collect the nav links, forms, and other content for toggling -->
              <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                  <li><a href="/InterfaceSisToque/views/produto/cadastrar.jsp">Produto<span class="sr-only">(current)</span></a></li>
                  <li><a href="/InterfaceSisToque/views/cliente/cadastrar.jsp">Cliente</a></li>
                   <li><a href="/InterfaceSisToque/views/venda/cadastrar.jsp">Venda</a></li>
                   <li><a href="/InterfaceSisToque/views/compra/cadastrar.jsp">Compra</a></li>
                </ul>

                <div class="nav navbar-nav navbar-right">                     
                        
                    
                </div><!-- /.container-fluid -->
            </div>
        </nav>
    </body>
</html>
