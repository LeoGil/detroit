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

<title>Lista de Usuários</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="modal fade" id="cadastrarColaboradorModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cadastro
						Usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="colaboradores.do" method="POST">
					<div class="modal-body">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="input_nome">Nome</label> <input type="text"
									class="form-control" name="nome" id="input_nome" required>
							</div>
							<div class="form-group col-md-6">
								<label for="input_email">E-mail</label> <input type="email"
									class="form-control" name="email" id="input_email" required>
							</div>

						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="input_matricula">Matrícula</label> <input
									type="text" class="form-control" name="matricula"
									id="input_matricula" required>
							</div>
							<div class="form-group col-md-6">
								<label for="input_senha">Senha</label> <input type="password"
									class="form-control" name="senha" id="input_senha" required>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success" name="acao"
							value="inserir_colaborador">Cadastrar</button>
					</div>
				</form>
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
				<h1 class="float-left">Lista de Usuários</h1>
				<button type="button" class="btn btn-success float-right"
					data-toggle="modal" data-target="#cadastrarColaboradorModal">
					Cadastrar usuário</button>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<table class="table table-striped table-hover">
					<tr>
						<th>Nome</th>
						<th>Matrícula</th>
						<th>E-mail</th>
						<th>Opções</th>
					</tr>
					<c:forEach var="colaborador" items="${colaboradores}">

						<tr>
							<td>${colaborador.nome}</td>
							<td>${colaborador.matricula}</td>
							<td>${colaborador.email}</td>
							<td>
								<form action="colaboradores.do" method="GET">
									<input type="hidden" class="form-control" name="id_colaborador"
										value="${colaborador.id }" />
									<button type="submit" id="btnVisualizar" name="acao"
										value="visualizar_colaborador" class="btn btn-primary">Visualizar</button>
								</form>
								<form action="colaboradores.do" method="GET">
									<input type="hidden" class="form-control" name="id_colaborador"
										value="${colaborador.id }" />
									<button type="submit" id="btnEditar" name="acao"
										value="editar_colaborador" class="btn btn-warning">Editar</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>