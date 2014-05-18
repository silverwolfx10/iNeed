<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<h1>Usuario </h1>
	<div class="container">
		<form action="usuario?action=cadastrar" method="POST" enctype="application/x-www-form-urlencoded">
		<!--  COPIAR DAQUI-->
			<div class="linha">
				<div class="label">RM:</div>
				<div class="campo">
					<input type="text" name="rm" value="${usuario.rm}" />
				</div>
			</div>
		<!-- ATE AQUI -->
			<div class="linha">
				<div class="label">Nome:</div>
				<div class="campo">
					<input type="text" name="nome" value="${usuario.nome}" />
				</div>
			</div>
			<div class="linha">
				<div class="label">Senha:</div>
				<div class="campo">
					<input type="password" name="senha" value="${usuario.senha}" />
				</div>
			</div>
			<div class="linha">
				<div class="label">Turma:</div>
				<div class="campo">
					<select name="turma_id">
						<c:forEach items="${turmas}" var="item">
						 	 <option <c:if test="${usuario.turmaId.id == item.id}"> selected="selected"</c:if> value="<c:out value="${item.id}"/>">
						 	 	<c:out value="${item.descricao}"/>
					 	 	</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="linha">
				<div class="label">Admin:</div>
				<div class="campo">
				<select name="is_admin">
				<option <c:if test="${usuario.is_admin == 0}"> selected="selected" value="0"</c:if>/> Nao </option>

					 <option <c:if test="${usuario.is_admin == 1}"> selected="selected" value="1"</c:if>/>
					Sim
					 </option>
					</select>
				</div>
			</div>
			
			
			<div class="submit">
					<input type="submit" name="enviar" value="Salvar" />
				</div>
			
			
			<input type="hidden" name="id" value="${turma.id}" />
		</form>
	</div>
	<div class="voltar">
		<a href="usuario">Voltar</a>
	</div>


