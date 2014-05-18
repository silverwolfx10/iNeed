<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<h1>Notas</h1>
	<div class="container">
		<form action="nota?action=cadastrar" method="POST" enctype="application/x-www-form-urlencoded">
		<!--  COPIAR DAQUI-->
			<div class="linha">
				<div class="label">Materia:</div>
				<div class="campo">
					<select name="materia_id">
						<c:forEach items="${materias}" var="item">
						 	 <option <c:if test="${nota.materiaId.id == item.id}"> selected="selected"</c:if> value="<c:out value="${item.id}"/>">
						 	 	<c:out value="${item.descricao}"/>
					 	 	</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="linha">
				<div class="label">Avaliação:</div>
				<div class="campo">
					<select name="avaliacao_id">
						<c:forEach items="${avaliacoes}" var="item">
						 	 <option <c:if test="${nota.avaliacaoId.id == item.id}"> selected="selected"</c:if> value="<c:out value="${item.id}"/>">
						 	 	<c:out value="${item.descricao}"/>
					 	 	</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="linha">
				<div class="label">Nota:</div>
				<div class="campo">
					<input type="text" name="nota" value="${nota.nota}"/>
				</div>
			</div>
			<div class="linha">
				<div class="label">Semestre:</div>
				<div class="campo">
					<input type="radio" name="semestre" <c:if test="${nota.semestre == 1 || nota == null}"> checked="checked"</c:if> value="1"> 1º
					<input type="radio" name="semestre" <c:if test="${nota.semestre == 2}"> checked="checked"</c:if> value="2">2º
				</div>
			</div>
		<!-- ATE AQUI -->
			<div class="submit">
					<input type="submit" name="enviar" value="Salvar" />
				</div>
			
			
			<input type="hidden" name="id" value="${nota.id}" />
		</form>
	</div>
	<div class="voltar">
		<a href="nota">Voltar</a>
	</div>


