<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav
	class="navbar navbar-expand-lg bg-white border-bottom shadow-sm mb-5 p-3">
	<a class="my-0 mr-md-auto navbar-brand font-weight-bolder" href="#">
		<!-- <img
		src="https://getbootstrap.com/docs/4.5/assets/brand/bootstrap-solid.svg"
		width="30" height="30" class="d-inline-block align-top" alt=""
		loading="lazy"> -->	
		 Detroit
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

		</ul>
		<div class="my-2 my-lg-0">
			<nav class="my-2 my-md-0 mr-md-3">
				<a class="p-2 text-dark font-weight-bolder"
					href="projetos.do?acao=listar">Projetos</a> <a
					class="p-2 text-dark font-weight-bolder"
					href="colaboradores.do?acao=listar">Usuários</a>
				<div class="btn-group dropleft">
					<button class="btn btn-primary btn-sm dropdown-toggle"
						type="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">${usuarioLogado.nome }</button>
					<div class="dropdown-menu">
						<form action="colaboradores.do" method="GET">
							<input type="hidden" class="form-control" name="id_colaborador"
								value="${usuarioLogado.id }" />
							<button type="submit" id="btnEditar" name="acao"
								value="editar_colaborador" class="btn btn-link text-secondary">Editar
								Usuário</button>
						</form>
						<form action="colaboradores.do" method="GET">
							<button type="submit" id="btnEditar" name="acao"
								value="deslogar" class="btn btn-link text-danger">Deslogar</button>
						</form>
					</div>
				</div>
			</nav>
		</div>
	</div>
</nav>