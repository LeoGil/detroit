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

<title>Lista de Filmes</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="modal fade" id="cadastrarProjetoModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Novo Projeto</h5>
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
				<h1 class="float-left">Lista de Projetos</h1>
				<button type="button" class="btn btn-success float-right"
					data-toggle="modal" data-target="#cadastrarProjetoModal">
					Cadastrar projeto</button>
			</div>
		</div>
		<div class="row">
			<form action="projetos.do" method="GET">
				<c:forEach var="projeto" items="${projetos}">
					<div class="col-4 mb-5">
						<div
							class="card shadow-sm border-${projeto.situacaoProjeto.classe}">
							<div class="card-body">
								<h5 class="card-title">
									<b>${projeto.nome }</b>
								</h5>
								<hr>
								<ul class="list-unstyled">
									<li><b>Responsável: </b>${projeto.colaborador.nome }</li>
									<li><b>Objetivo: </b>${projeto.objetivo.descricao }</li>
									<li><b>Situação do projeto: </b>${projeto.situacaoProjeto.situacao }</li>
								</ul>
								<button type="submit" id="btnVisualizar" name="acao"
									value="visualizar_projeto" class="btn btn-primary">Visualizar</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</form>
		</div>
	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->
	<!-- <script> 
		window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script> -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
		/* var employees = {
			<c:forEach items="${projetos}" var="projeto">
			"${projeto.id}": {
				situacao:"${projeto.situacaoProjeto.situacao}"
			},
			</c:forEach>
		}

		// let projetos = []
		// projetos = "${projetos}"
		
		console.log(employees) */
	</script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- <script src="js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>