<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu">
	<ul>
		<li><a href="turma?action=cadastrar">Cadastrar nova turma</a></li>
	</ul>
</div>

<div class="container">
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td>Turma</td>
			<td>Ação</td>
			<td>Ação</td>
		</tr>
		<c:forEach items="${turmas}" var="t">
			<tr>
				<td>${t.descricao}</td>
				<td><a href="turma?action=cadastrar&id=${t.id}">Alterar</a></td>
				<td><a href="turma?action=excluir&id=${t.id}">Excluir</a></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
