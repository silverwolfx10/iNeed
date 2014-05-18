<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu">
	<ul>
		<li><a href="usuario?action=cadastrar">Cadastrar novo Usuario</a></li>
	</ul>
</div>

<div class="container">
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td>Rm</td>
			<td>Nome</td>
			<td>Ação</td>
			<td>Ação</td>
		</tr>
		<c:forEach items="${xxx}" var="u">
			<tr>
				<td>${u.rm}</td>
				<td>${u.nome}</td>
				<td><a href="usuario?action=cadastrar&id=${u.id}">Alterar</a></td>
				<td><a href="usuario?action=excluir&id=${u.id}">Excluir</a></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
