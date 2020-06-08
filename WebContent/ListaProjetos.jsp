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

<title>Lista de Projetos</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="modal fade" id="cadastrarProjetoModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Novo Projeto</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="projetos.do" method="POST">
					<div class="modal-body">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="input_nome">Nome</label> <input type="text"
									class="form-control" name="nome" id="input_nome" required>
							</div>
							<div class="form-group col-md-3">
								<label for="input_estimativa">Estimativa </label><input
									id="input_estimativa" type="date" class="form-control"
									name="estimativa" required />
							</div>
							<div class="form-group col-md-3">
								<label for="inputState">Objetivo</label> <select id="objetivo"
									name="objetivo" class="form-control" required>
									<option value="" selected>Selecione um...</option>
									<c:forEach var="objetivo" items="${objetivos}">
										<option value="${objetivo.id}">${objetivo.descricao}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="div_complemento_objetivo">
							<div class="form-row">
								<div class="form-group col-md-4" id="departamento">
									<label for="input_departamento">Departamento</label> <input
										type="text" class="form-control" name="departamento"
										id="input_departamento">
								</div>
								<div class="form-group col-md-4" id="resultado_esperado">
									<label for="input_resultado_esperado">Resultado
										esperado</label> <input type="text" class="form-control"
										name="resultado_esperado" id="input_resultado_esperado">
								</div>
								<div class="form-group col-md-4" id="publico_beneficiario">
									<label for="input_publico_beneficiario">Público
										beneficiário</label> <input type="text" class="form-control"
										name="publico_beneficiario" id="input_publico_beneficiario">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="input_descricao">Descrição</label>
								<textarea class="form-control" name="descricao"
									id="input_descricao" rows="4" required></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" class="form-control" name="id_colaborador"
							value="${usuarioLogado.id }" />
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success" name="acao"
							value="inserir_projeto">Cadastrar</button>
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
				<h1 class="float-left">Lista de Projetos</h1>
				<button type="button" class="btn btn-success float-right"
					data-toggle="modal" data-target="#cadastrarProjetoModal">
					Cadastrar projeto</button>
			</div>
		</div>
		<div class="row">
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
								<li><b>Product Owner: </b>${projeto.colaborador.nome }</li>
								<li><b>Objetivo: </b>${projeto.objetivo.descricao }</li>
								<li><b>Situação do projeto: </b>${projeto.situacaoProjeto.situacao }</li>
							</ul>
							<form action="projetos.do" method="GET">
								<input type="hidden" class="form-control" name="id_projeto"
									value="${projeto.id }" />
								<button type="submit" id="btnVisualizar" name="acao"
									value="visualizar_projeto" class="btn btn-primary">Visualizar</button>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
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
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script>
		$('#departamento').hide()
		$('#resultado_esperado').hide()
		$('#publico_beneficiario').hide()

		//Busca o valor selecionado do select de objetivo
		$("select#objetivo").change(
				function() {
					var objetivoSelecionado = $(this).children(
							"option:selected").html();

					if (objetivoSelecionado == "Melhoria de Processo") {
						//Esconde o campo do publico beneficiario e mostra os departamento e resultado esperado
						$('#departamento').fadeIn(200)
						$('#departamento input').attr('required', 'required')

						$('#resultado_esperado').fadeIn(200)
						$('#resultado_esperado input').attr('required',
								'required')

						$('#publico_beneficiario input').removeAttr('required')
						$('#publico_beneficiario').fadeOut(200)
					} else if (objetivoSelecionado == "Social") {
						//Esconde os campos departamento e resultado esperado e mostra publico beneficiario 
						$('#departamento input').removeAttr('required')
						$('#departamento').fadeOut(200)

						$('#resultado_esperado input').removeAttr('required')
						$('#resultado_esperado').fadeOut(200)

						$('#publico_beneficiario').fadeIn(200)
						$('#publico_beneficiario input').attr('required',
								'required')
					}
				});
	</script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- <script src="js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>