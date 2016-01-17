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
        <title>Balanço</title>
    </head>
    <body>
        <div>
            <div class="container">
                <jsp:include page="../structure/cabecalho.jsp"></jsp:include>        
                    <h1>Balanço</h1>        
                    <hr>
                     <div class="row">
		<div class="col-md-12">
			<h3>Ordens de Serviço</h3>

			<div class="tabbable-panel">
				<div class="tabbable-line">
					<ul class="nav nav-tabs ">
						<li class="active">
							<a href="#tab_default_1" data-toggle="tab">
							Abertas </a>
						</li>
						<li>
							<a href="#tab_default_2" data-toggle="tab">
							Prontas para executar </a>
						</li>
						<li>
							<a href="#tab_default_3" data-toggle="tab">
							Canceladas </a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab_default_1">
                                                    <table class="table">
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Data Abertura </th>
                                                            <th>Descricao</th>
                                                        </tr>                      
                                                        <c:forEach var="osa" items="${ossabertas}" varStatus="cont">
                                                            <tr>
                                                                <td><a href="../os/addservicos?id=${osa.codigo}">${osa.codigo}</a></td>
                                                                <td>${osa.dataAbertura}</td>
                                                                <td>${osa.descricao}</td>
                                                            </tr>
                                                         </c:forEach>
                                                    </table>							
						</div>
						<div class="tab-pane" id="tab_default_2">
							<table class="table">
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Data Abertura </th>
                                                            <th>Descricao</th>
                                                        </tr>                      
                                                        <c:forEach var="ose" items="${ossexecutar}" varStatus="cont">
                                                            <tr>
                                                                <td><a href="../os/detalhe?id=${ose.codigo}">${ose.codigo}</a></td>
                                                                <td>${ose.dataAbertura}</td>
                                                                <td>${ose.descricao}</td>
                                                            </tr>
                                                         </c:forEach>
                                                    </table>		
						</div>
						<div class="tab-pane" id="tab_default_3">
							<table class="table">
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Data Abertura </th>
                                                            <th>Descricao</th>
                                                        </tr>                      
                                                        <c:forEach var="osc" items="${osscanceladas}" varStatus="cont">
                                                            <tr>
                                                                <td><a href="../os/detalhe?id=${osc.codigo}">${osc.codigo}</a></td>
                                                                <td>${osc.dataAbertura}</td>
                                                                <td>${osc.descricao}</td>
                                                            </tr>
                                                         </c:forEach>
                                                    </table>		
						</div>
					</div>
				</div>
			</div>
                    <jsp:include page="../structure/rodape.jsp"></jsp:include>
                      
            </div>
        </div>
    </body>
</html>
