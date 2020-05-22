<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Lista de Colaboradores</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="modal fade" id="cadastrarColaboradorModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Novo Colaborador</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success">Cadastrar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- importa o menu do sistema -->
	<c:import url="Menu.jsp" />
	<!-- conteiner principal -->
	<div class="container">


		<fmt:setLocale value="pt_BR" />
		<div class="row">
			<div class="col-12 mb-5">
				<h1 class="float-left">Lista de Colaboradores</h1>
				<button type="button" class="btn btn-success float-right"
					data-toggle="modal" data-target="#cadastrarProjetoModal">
					Cadastrar colaborador</button>
			</div>
		</div>
		<form action="colaboradores.do" method="GET">
			<div class="row">
				<div class="col-12">
					<table class="table table-striped table-hover">
						<tr>
						<th>Nome</th><th>Matrícula</th><th>E-mail</th><th>Opções</th>
						</tr>
						<c:forEach var="colaborador" items="${colaboradores}" >
							<tr>
							<td>${colaborador.nome}</td> 
							<td>${colaborador.matricula}</td>
							<td>${colaborador.email}</td>
							<td><button type="submit" id="btnVisualizar" name="acao"
											value="visualizar_projeto" class="btn btn-primary">Visualizar</button></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</form>
	</div>
	
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>