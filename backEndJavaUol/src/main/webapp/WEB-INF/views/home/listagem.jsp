<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<thead>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Listagem - JogadorUOL</title>
	</thead>

	<tbody>
	<div class="container">

		<c:if test="${cadastro}">
			<div class="row">
				<div class="alert alert-success alert-dismissible fade show w-100" role="alert">
					<strong>Cadastro efetuado com sucesso!</strong>
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</div>
		</c:if>

		<div class="row">
				<table class="table table-striped border">
				<tr>
					<th></th>
					<th scope="col">Nome</th>
					<th scope="col">Email</th>
					<th scope="col">Telefone</th>
					<th scope="col">Codinome</th>
					<th scope="col">Grupo</th>
					<th scope="col">Excluir</th>
				</tr>
					<c:forEach items="${jogadores}" var="jogador">
						<tr>
							<input type="hidden" class="id" value="${jogador.id}"/>
							<td></td>
							<td>${jogador.nome}</td>
							<td>${jogador.email}</td>
							<td>${jogador.telefone}</td>
							<td>${jogador.codinome}</td>
							<td>${jogador.grupo.descricao}</td>
							<td><button type="button" class="btn btn-danger btnExcluirTr" data-toggle="modal" data-target="#exclusaoModal">
								<i class="fas fa-trash-alt"/></button></td>
						</tr>
					</c:forEach>
				</table>
		</div>
		<div class="row float-right">
			<a href="/app/jogadores/novo" class="btn btn-primary">Novo jogador</a>
		</div>
	</div>

	<div class="modal fade" id="exclusaoModal" tabindex="-1" role="dialog" aria-labelledby="exclusaoModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exclusaoModalLabel">Excluir jogador</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Deseja realmente excluir o jogador?
					<input type="hidden" id="idExclusao"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-danger" id="btnExcluir">Excluir</button>
				</div>
			</div>
		</div>
	</div>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	</tbody>
	<script type="text/javascript">
		$(".btnExcluirTr").on('click', function () {
            console.log('clicou');
            var idExclusao = $(this).parents('tr').find("input[type='hidden']").val();
            $("#idExclusao").val(idExclusao);
		});

		$("#btnExcluir").on('click', function () {
			var idExclusao = $("#idExclusao").val();
			console.log('excluir: '+idExclusao);
			$.ajax({
				url: "/jogadores/"+idExclusao,
				type: "DELETE",
				success: function () {
					window.location = "/app/jogadores/lista";
				},
				error: function (xhr, textStatus, thrownError) {
					console.log(xhr);
					console.log(textStatus);
					console.log(thrownError);
				}
			});
		})
	</script>
</html>