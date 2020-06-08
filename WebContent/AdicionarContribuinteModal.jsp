<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="modal fade" id="cadastrarContribuinteModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Novo contribuinte</h5>
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
						<div class="form-group col-6">
							<label for="inputState">Contribuinte</label> <select id="objetivo"
								name="colaborador" class="form-control" required>
								<option value="" selected>Selecione um...</option>
								<c:forEach var="liberado" items="${colaboradoresLiberados}">
									<option value="${liberado.id}">${liberado.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-6">
							<label for="papel">Perfil</label> <select id="papel"
								name="papel" class="form-control" required>
								<option value="" selected>Selecione um...</option>
								<c:forEach var="papel" items="${papeis}">
									<option value="${papel.id}">${papel.papel}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-success" name="acao"
						value="inserir_contribuinte">Cadastrar</button>
				</div>
			</form>
		</div>
	</div>
</div>