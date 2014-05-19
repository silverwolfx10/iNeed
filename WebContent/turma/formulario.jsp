<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<h1>Turma </h1>
	<div class="container">
		<form action="turma?action=cadastrar" method="POST" enctype="application/x-www-form-urlencoded">
		<!--  COPIAR DAQUI-->
			<div class="linha">
				<div class="label">Descrição:</div>
				<div class="campo">
					<input type="text" name="descricao" value="${turma.descricao}" />
				</div>
			</div>
		<!-- ATE AQUI -->
			<div class="submit">
					<input type="submit" name="enviar" value="Salvar" />
				</div>
			
			
			<input type="hidden" name="id" value="${turma.id}" />
		</form>
	</div>
	<div class="voltar">
		<a href="turma">Voltar</a>
	</div>


