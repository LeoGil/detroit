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
	<c:import url="AdicionarTarefaModal.jsp" />
	<c:import url="AdicionarContribuinteModal.jsp" />
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
				<div class="col-12 mb-1">
					<h3 class="mb-4 float-left">Tarefas</h3>
					<button type="button" class="btn btn-success float-right"
						data-toggle="modal" data-target="#cadastrarTarefaModal">
						Nova tarefa</button>
				</div>
				<div class="col-4">
					<h5>ToDo</h5>
					<hr>
					<nav>
						<div class="nav nav-tabs" id="nav-tab-todo" role="tablist">
							<c:forEach var="tarefa" items="${tarefasTodo}">
								<a class="nav-item nav-link" data-toggle="tab"
									id="nav-${tarefa.id }-tab" href="#nav-tarefa-${tarefa.id }"
									role="tab" aria-controls="nav-tarefa-${tarefa.id }"
									aria-selected="true">${tarefa.titulo }</a>
							</c:forEach>
						</div>
					</nav>
					<div class="tab-content" id="nav-tabContent-todo">
						<c:forEach var="tarefa" items="${tarefasTodo}">
							<div class="tab-pane fade mt-2 text-justify"
								id="nav-tarefa-${tarefa.id }" role="tabpanel"
								aria-labelledby="nav-${tarefa.id }-tab">${tarefa.descricao }
								<br> <b class="mb-3">Responsável: </b>${tarefa.colaborador.nome }

								<form action="projetos.do" method="GET">
									<input type="hidden" class="form-control" name="id_projeto"
										value="${projeto.id }" /> <input type="hidden"
										class="form-control" name="id_tarefa" value="${tarefa.id }" />
									<button type="submit" name="acao" value="avancar_tarefa_doing"
										class="btn btn-success btn-sm">Avançar para Doing</button>
								</form>
								<hr>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-4">
					<h5>Doing</h5>
					<hr>
					<nav>
						<div class="nav nav-tabs" id="nav-tab-doing" role="tablist">
							<c:forEach var="tarefa" items="${tarefasDoing}">
								<a class="nav-item nav-link" data-toggle="tab"
									id="nav-${tarefa.id }-tab" href="#nav-tarefa-${tarefa.id }"
									role="tab" aria-controls="nav-tarefa-${tarefa.id }"
									aria-selected="true">${tarefa.titulo }</a>
							</c:forEach>
						</div>
					</nav>
					<div class="tab-content" id="nav-tabContent-doing">
						<c:forEach var="tarefa" items="${tarefasDoing}">
							<div class="tab-pane fade mt-2 text-justify"
								id="nav-tarefa-${tarefa.id }" role="tabpanel"
								aria-labelledby="nav-${tarefa.id }-tab">${tarefa.descricao }
								<br> <b class="mb-3">Responsável: </b>${tarefa.colaborador.nome }
								<form action="projetos.do" method="GET">
									<input type="hidden" class="form-control" name="id_projeto"
										value="${projeto.id }" /> <input type="hidden"
										class="form-control" name="id_tarefa" value="${tarefa.id }" />
									<button type="submit" name="acao"
										value="retroceder_tarefa_todo"
										class="btn btn-warning btn-sm mb-2">Retroceder para
										ToDo</button>
								</form>
								<form action="projetos.do" method="GET">
									<input type="hidden" class="form-control" name="id_projeto"
										value="${projeto.id }" /> <input type="hidden"
										class="form-control" name="id_tarefa" value="${tarefa.id }" />
									<button type="submit" name="acao" value="avancar_tarefa_done"
										class="btn btn-success btn-sm">Avançar para Done</button>
								</form>
								<hr>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-4">
					<h5>Done</h5>
					<hr>
					<nav>
						<div class="nav nav-tabs" id="nav-tab-done" role="tablist">
							<c:forEach var="tarefa" items="${tarefasDone}">
								<a class="nav-item nav-link" data-toggle="tab"
									id="nav-${tarefa.id }-tab" href="#nav-tarefa-${tarefa.id }"
									role="tab" aria-controls="nav-tarefa-${tarefa.id }"
									aria-selected="true">${tarefa.titulo }</a>
							</c:forEach>
						</div>
					</nav>
					<div class="tab-content" id="nav-tabContent-done">
						<c:forEach var="tarefa" items="${tarefasDone}">
							<div class="tab-pane fade mt-2 text-justify"
								id="nav-tarefa-${tarefa.id }" role="tabpanel"
								aria-labelledby="nav-${tarefa.id }-tab">${tarefa.descricao }
								<br> <b class="mb-3">Responsável: </b>${tarefa.colaborador.nome }
								<hr>
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
							<form action="projetos.do" method="POST">
								<input type="hidden" class="form-control" name="id_projeto"
									value="${projeto.id }" />
								<textarea name="comentario" class="form-control mb-3"
									placeholder="Escreva aqui seu comentário..." rows="3" required></textarea>
								<input type="hidden" class="form-control" name="id_colaborador"
									value="${usuarioLogado.id }" />
								<button type="submit" class="btn btn-success float-right"
									name="acao" value="inserir_comentario">Publicar
									comentário</button>
							</form>
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
										<div class="comment-footer mb-4">
											<span class="text-muted float-right"><fmt:formatDate
													value="${comentario.dataCadastro}" dateStyle="SHORT" /></span>
											<form action="projetos.do" method="GET">
												<input type="hidden" class="form-control" name="id_projeto"
													value="${projeto.id }" /> <input type="hidden"
													class="form-control" name="id_comentario"
													value="${comentario.id }" />
												<button type="submit" name="acao" value="excluir_comentario"
													class="btn btn-danger btn-sm">Excluir</button>
											</form>
										</div>
									</c:forEach>
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
								<li><b>Estimativa: </b> <fmt:formatDate
										value="${projeto.estimativa}" dateStyle="SHORT" /></li>
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
								<div id="progress_bar"
									class="progress-bar progress-bar-striped progress-bar-animated"
									role="progressbar" aria-valuenow="0" aria-valuemin="0"
									aria-valuemax="100" style="width: 0%"></div>
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
							<button type="button" class="btn btn-success float-right btn-sm"
								data-toggle="modal" data-target="#cadastrarContribuinteModal">
								Novo colaborador</button>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<ul class="list-group list-group-flush">
								<c:forEach var="contribuinte" items="${contribuintes}">
									<li class="list-group-item pl-0">${contribuinte.colaborador.nome }<span
										class="text-muted"> (${contribuinte.papel.papel })</span>
										<form action="projetos.do" method="GET">
											<input type="hidden" class="form-control" name="id_projeto"
												value="${projeto.id }" /> <input type="hidden"
												class="form-control" name="id_comentario"
												value="${contribuinte.id }" />
											<button type="submit" name="acao"
												value="excluir_contribuinte" class="btn btn-danger btn-sm">Remover</button>
										</form>
									</li>
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
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script>
		let total = ${totalTarefas }
		let totalFeitas = ${totalTarefasFeitas }

		let porcentagem = (totalFeitas * 100) / total

		$('#progress_bar').css("width", porcentagem + '%').prop(
				'aria-valuenow', porcentagem)
	</script>
</body>
</html>