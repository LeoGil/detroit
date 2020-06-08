<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="modal fade" id="cadastrarTarefaModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Nova tarefa</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form action="projetos.do" method="POST">
				<div class="modal-body">
					<div class="form-row">
						<input type="hidden" class="form-control" name="id_projeto"
							value="${projeto.id }" />
						<div class="form-group col-md-6">
							<label for="input_titulo">Título</label> <input type="text"
								class="form-control" name="titulo" id="input_titulo" required>
						</div>
						<div class="form-group col-md-6">
							<label for="inputState">Colaborador</label> <select id="objetivo"
								name="contribuinte" class="form-control" required>
								<option value="" selected>Selecione um...</option>
								<c:forEach var="contribuinte" items="${contribuintes}">
									<option value="${contribuinte.id}">${contribuinte.colaborador.nome}</option>
								</c:forEach>
							</select>
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
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-success" name="acao"
						value="inserir_tarefa">Cadastrar</button>
				</div>
			</form>
		</div>
	</div>
</div>