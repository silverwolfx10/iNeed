<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<h1>Matérias</h1>
	<div class="container">
		<form action="materia?action=cadastrar" method="POST" enctype="application/x-www-form-urlencoded">
		<!--  COPIAR DAQUI-->
			<div class="linha">
				<div class="label">Descrição:</div>
				<div class="campo">
					<input type="text" name="descricao" value="${materia.descricao}" />
				</div>
			</div>
			<div class="linha">
				<div class="label">Turma:</div>
				<div class="campo">
					<select name="turma_id">
						<c:forEach items="${turmas}" var="item">
						 	 <option <c:if test="${materia.turmaId.id == item.id}"> selected="selected"</c:if> value="<c:out value="${item.id}"/>">
						 	 	<c:out value="${item.descricao}"/>
					 	 	</option>
						</c:forEach>
					</select>
				</div>
			</div>
		<!-- ATE AQUI -->
			<div class="submit">
					<input type="submit" name="enviar" value="Salvar" />
				</div>
			
			
			<input type="hidden" name="id" value="${materia.id}" />
		</form>
	</div>
	<div class="voltar">
		<a href="materia">Voltar</a>
	</div>


