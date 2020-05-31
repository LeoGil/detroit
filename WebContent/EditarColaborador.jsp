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

<title>Visualizar Usuário</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<!-- importa o menu do sistema -->
	<c:import url="Menu.jsp" />
	<!-- conteiner principal -->
	<div class="container">


		<fmt:setLocale value="pt_BR" />
		<div class="row">
			<div class="col-12 mb-5">
				<h1 class="float-left">Alterar dados de ${colaborador.nome }</h1>
			</div>
		</div>
		<form action="colaboradores.do" method="GET">
			<div class="row">
				<div class="col-12">
				<input type="hidden" class="form-control" name="id_colaborador"
										value="${colaborador.id }" />
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="input_nome">Nome</label> <input type="text"
								value="${colaborador.nome }" class="form-control" name="nome"
								id="input_nome" required>
						</div>
						<div class="form-group col-md-6">
							<label for="input_email">E-mail</label> <input type="email"
								value="${colaborador.email }" class="form-control" name="email"
								id="input_email" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="input_matricula">Matrícula</label> <input type="text"
								value="${colaborador.matricula }" readonly="readonly"
								class="form-control" name="matricula" id="input_matricula"
								required>
						</div>
						<div class="form-group col-md-6">
							<label for="input_senha">Senha</label> <input type="text"
								value="${colaborador.senha }" class="form-control" name="senha"
								id="input_senha" required>
						</div>
					</div>
				</div>
				<div id="actions" class="row">
					<div class="col-md-12">
						<button type="submit" class="btn btn-primary" name="acao"
							value="salvar_colaborador">Atualizar</button>
						<a href="ListaColaboradores.jsp" class="btn btn-default">Voltar</a>
					</div>
				</div>
			</div>
		</form>
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>