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

<title>Visualizar Projeto</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<!-- importa o menu do sistema -->
	<c:import url="Menu.jsp" />
	<!-- conteiner principal -->
	<div class="container-fluid">
		<div class="col-9 float-left">
			<fmt:setLocale value="pt_BR" />
			<div class="row mb-5">
				<div class="col-12 mb-5">
					<h1 class="float-left">${projeto.nome }</h1>
					<!-- <button type="button" class="btn btn-danger float-right">
						Finalizar Projeto</button> -->
				</div>
				<div class="col-12">
					<p class="text-justify">${projeto.descricao }</p>
				</div>
			</div>
			<div class="row mb-5">
				<div class="col-12">
					<h3 class="mb-4">Tarefas</h3>
					<nav>
						<div class="nav nav-tabs" id="nav-tab" role="tablist">
							<c:forEach var="tarefa" items="${tarefas}">
								<a class="nav-item nav-link" data-toggle="tab"
									id="nav-${tarefa.id }-tab" href="#nav-tarefa-${tarefa.id }"
									role="tab" aria-controls="nav-tarefa-${tarefa.id }"
									aria-selected="true">${tarefa.titulo }</a>
							</c:forEach>
						</div>
					</nav>
					<div class="tab-content" id="nav-tabContent">
						<c:forEach var="tarefa" items="${tarefas}">
							<div class="tab-pane fade mt-2 text-justify"
								id="nav-tarefa-${tarefa.id }" role="tabpanel"
								aria-labelledby="nav-${tarefa.id }-tab">${tarefa.descricao }<hr>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row mb-5">
				<div class="col-12">
					<h3 class="mb-4">Comentários do projeto</h3>
					<div class="row">
						<div class="col-12">
							<textarea class="form-control mb-3"
								placeholder="Escreva aqui seu comentário..." rows="3"></textarea>
							<button type="button" class="btn btn-success float-right">Publicar
								comentário</button>
						</div>
					</div>

					<div class="row">
						<div class="col-12">
							<hr>
							<!-- Comment Row -->
							<div class="row">
								<div class="col-12 mb-4">
									<c:forEach var="comentario" items="${comentarios}">
										<h6 class="font-weight-bold">${comentario.colaborador.nome }</h6>
										<span class="m-b-15 d-block">${comentario.comentario }</span>
										<div class="comment-footer">
										<span class="text-muted float-right"><fmt:formatDate value="${comentario.dataCadastro}" dateStyle="SHORT"/></span>
											<button type="button" class="btn btn-danger btn-sm">Excluir</button>
										</div>
									</c:forEach>
								</div>
							</div>
							<!-- Comment Row -->
							<div class="row">
								<div class="col-12 mb-4">
									<h6 class="font-weight-bold">Michael Hussey</h6>
									<span class="m-b-15 d-block">Thanks bbbootstrap.com for
										providing such useful snippets. </span>
									<div class="comment-footer">
										<span class="text-muted float-right">August 1, 2019</span>
										<button type="button" class="btn btn-danger btn-sm">Excluir</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-3 float-left">
			<div class="card mb-5">
				<div class="card-body">
					<div class="row">
						<div class="col-12 mb-4">
							<h5 class="float-left font-weight-bold">Dados gerais do
								projeto</h5>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<ul class="list-unstyled">
								<li><b>Product Owner: </b>${projeto.colaborador.nome }</li>
								<li><b>Objetivo: </b>${projeto.objetivo.descricao }</li>
								<li><b>Situação do projeto: </b>${projeto.situacaoProjeto.situacao }</li>
								<li><b>Estimativa: </b><fmt:formatDate value="${projeto.estimativa}" dateStyle="SHORT"/></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="card mb-5">
				<div class="card-body">
					<div class="row">
						<div class="col-12 mb-4">
							<h5 class="float-left font-weight-bold">Progressão do
								projeto</h5>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<div class="progress">
								<div
									class="progress-bar progress-bar-striped progress-bar-animated"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card mb-5">
				<div class="card-body">
					<div class="row">
						<div class="col-12 mb-4">
							<h5 class="float-left font-weight-bold">Colaboradores</h5>
							<button type="button" class="btn btn-success float-right btn-sm">
								Novo colaborador</button>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<ul class="list-group list-group-flush">
								<c:forEach var="contribuinte" items="${contribuintes}">
									<li class="list-group-item pl-0">${contribuinte.colaborador.nome }<span
										class="text-muted"> (${contribuinte.papel.papel })</span>
										<button type="button"
											class="btn btn-danger float-right btn-sm">Remover</button></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="card mb-5 border-danger">
				<div class="card-body">
					<div class="row">
						<div class="col-12 mb-4">
							<h5 class="float-left font-weight-bold">Configuração do
								projeto</h5>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<button type="button" class="btn btn-success">Finalizar
								Projeto</button>
							<button type="button" class="btn btn-danger">Inativar
								Projeto</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>