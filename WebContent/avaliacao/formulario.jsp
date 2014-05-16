<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<h1>Tipos de Avaliação</h1>
	<div class="container">
		<form action="avaliacao?action=cadastrar" method="POST" enctype="application/x-www-form-urlencoded">
		<!--  COPIAR DAQUI-->
			<div class="linha">
				<div class="label">Descrição:</div>
				<div class="campo">
					<input type="text" name="descricao" value="${avaliacao.descricao}" />
				</div>
			</div>
			<div class="linha">
				<div class="label">Peso:</div>
				<div class="campo">
					<input type="text" name="peso" value="${avaliacao.peso}" />
				</div>
			</div>
		<!-- ATE AQUI -->
			<div class="submit">
					<input type="submit" name="enviar" value="Salvar" />
				</div>
			
			
			<input type="hidden" name="id" value="${avaliacao.id}" />
		</form>
	</div>
	<div class="voltar">
		<a href="avaliacao">Voltar</a>
	</div>


