<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!docytpe html>
<html>
	<thead>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastro - JogadorUOL</title>
	</thead>
	<tbody>
        <div class="container" align="center">
            <div class="row w-50">
                <h3>Cadastro de jogador - Universo Online(UOL)</h3>
            </div>
            <c:if test="${sucesso}">
                <div class="row w-50">
                    <div class="alert alert-success alert-dismissible fade show w-100" role="alert">
                        <strong>Cadastro efetuado com sucesso!</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </c:if>
            <c:if test="${erroCodinome}">
                <div class="row w-50">
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>Não existe mais codinomes disponível para o grupo ${jogador.grupo.descricao}!</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </c:if>
            <form method="post" action="/app/salvar">
                <div class="input-group mb-3 w-50">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Nome</span>
                    </div>
                    <input type="text" name="nome" class="form-control" placeholder="Digite aqui seu nome.."
                           aria-label="nome" aria-describedby="basic-addon1" required value="${jogador.nome}">
                </div>
                <div class="input-group mb-3 w-50">
                    <input type="email" name="email" class="form-control" placeholder="Digite aqui seu e-mail.."
                           aria-label="e-mail" aria-describedby="basic-addon2" required>
                    <div class="input-group-append">
                        <span class="input-group-text" id="basic-addon2">@exemplo.com</span>
                    </div>
                </div>
                <div class="input-group mb-3 w-50">
                    <div class="input-group-append">
                        <span class="input-group-text" id="basic-addon3">Telefone</span>
                    </div>
                    <input type="text" name="telefone" class="form-control" id="telefone"
                           aria-label="telefone" aria-describedby="basic-addon3">
                </div>
                <div class="input-group mb-3 w-50">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="selectGrupo">Quero ser do grupo:</label>
                    </div>
                    <select name="grupo" class="custom-select" value="${jogador.grupo}" id="selectGrupo">
                        <c:forEach items="${grupos}" var="grupo">
                            <option value="${grupo}">${grupo.descricao}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row w-50">
                    <div class="col">
                        <a href="/app/jogadores" class="btn btn-primary">Voltar</a>
                    </div>
                    <div class="col">
                        <input type="submit" value="Cadastrar" class="btn btn-success">
                    </div>
                </div>
            </form>

        </div>
	</tbody>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
</html>
<script type="text/javascript">
    $("#telefone")
        .mask("(99) 9999-9999?9")
        .focusout(function (event) {
            var target, phone, element;
            target = (event.currentTarget) ? event.currentTarget : event.srcElement;
            phone = target.value.replace(/\D/g, '');
            element = $(target);
            element.unmask();
            if(phone.length > 10) {
                element.mask("(99) 99999-999?9");
            } else {
                element.mask("(99) 9999-9999?9");
            }
        });
</script>