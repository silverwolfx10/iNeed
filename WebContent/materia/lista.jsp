<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu">
	<ul>
		<li><a href="materia?action=cadastrar">Cadastrar Materia</a></li>
	</ul>
</div>

<div class="container">
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td>Descrição</td>
			<td>Turma</td>
			<td>Ação</td>
			<td>Ação</td>
		</tr>
		<c:forEach items="${materias}" var="mt">
			<tr>
				<td>${mt.descricao}</td>
				<td>${mt.turmaId.descricao}</td>
				<td><a href="materia?action=cadastrar&id=${mt.id}">Alterar</a></td>
				<td><a href="materia?action=excluir&id=${mt.id}">Excluir</a></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
